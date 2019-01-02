/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import nopacks.projet.DAO.annotations.Colonne;
import nopacks.projet.DAO.annotations.Table;
import nopacks.projet.DAO.annotations.Tsizy;
import nopacks.projet.modeles.BaseModele;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.ResultatPagination;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author ASUS
 */
public class GeneriqueDAO implements InterfaceDAO {

    private BasicDataSource connexion;

    public void setConnexion(BasicDataSource ds) {
        this.connexion = ds;
    }

    @Override
    public void beginTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(BaseModele p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseModele findById(BaseModele p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultatPagination findAllPage(BaseModele p, int page, int parpage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(BaseModele p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(BaseModele p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BaseModele> findAll(BaseModele p) {
        List<BaseModele> rt = null;
        Connection cx = null;
        try {
            //System.out.println("findAll ");
            //System.out.println();
            //System.out.println();
            //System.out.println();
            cx = connexion.getConnection();
            String n_table = getNomTable(p);
            ArrayList<String[]> attr = this.getAttributsBaseModele(p);
            //System.out.println(attr.size());
            //System.out.println("select * from "+n_table);
            Statement ps = cx.createStatement();
            ResultSet rs = ps.executeQuery("select * from " + n_table);
            rt = new ArrayList<BaseModele>();
            while (rs.next()) {
                //System.out.println(" next ");
                rt.add(rsToObject(p, rs, attr));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw (ex);
        } finally {
            try {
                if (cx != null) {
                    cx.close();
                }
            } catch (Exception ex) {

            }
            //System.out.println();
            //System.out.println();
            //System.out.println();
            //System.out.println("fin find");
            return rt;
        }
    }

    private BaseModele rsToObject(BaseModele p, ResultSet rs, ArrayList<String[]> attribs) throws Exception {
        BaseModele rt = p.getClass().newInstance();
        //System.out.println(attribs.size());
        for (String[] ray : attribs) {
            //System.out.println(ray[1]);
            Object averiny = rs.getObject(ray[1]);
            if (averiny == null) {
                continue;
            }
            //System.out.println(ray[1]+" "+averiny);
            callSetter(rt, ray[0], averiny);
            //System.out.println(" manaraka amzay ");
        }
        return rt;
    }

    private String getNomTable(BaseModele p) {
        Table at = p.getClass().getAnnotation(Table.class);
        if (at != null) {
            return at.nom();
        }
        return p.getClass().getSimpleName();
    }

    @PostConstruct
    public void tester() {
        try {
            Chanson testchan = new Chanson();
            testchan.setAuteur("itestena anle reflection");
            List<BaseModele> liste = findAll(testchan);
            for (BaseModele bm : liste) {
                Chanson tpchan = ((Chanson) bm);
                System.out.println(tpchan.getId() + " " + tpchan.getNomfichier());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw (ex);
        }
    }

    private Method getGetter(Object cible, String nom) throws Exception { //atribut : nomChanson => getNomChanson
        String charac = nom.substring(0, 1).toUpperCase();
        String realattrib = charac + nom.substring(1);
        Method rt = cible.getClass().getMethod("get" + realattrib, null);
        return rt;
    }

    private Method getSetter(Object cible, String nom) throws Exception {
        return getSetter(cible, nom, getGetter(cible, nom).getReturnType());
    }

    private Method getSetter(Object cible, String nom, Class classe) throws Exception { //atribut : nomChanson => getNomChanson
        String charac = nom.substring(0, 1).toUpperCase();
        String realattrib = charac + nom.substring(1);
        Method rt = cible.getClass().getMethod("set" + realattrib, classe);
        return rt;
    }

    private HashMap<String, Object> getColAndVal(BaseModele cible) throws Exception {
        return getColAndVal(cible, getAttributsBaseModele(cible));
    }

    private String getCol(BaseModele cible) throws Exception {
        return getCol(cible, getAttributsBaseModele(cible));
    }

    private String getCol(BaseModele cible, ArrayList<String[]> attributs) throws Exception {
        String rt;
        StringBuilder sb1 = new StringBuilder();
        boolean fst = true;
        for (String[] att : attributs) {
            if (!fst) {
                sb1.append(" , ");

            }
            sb1.append(att[1]);
            fst = false;
        }
        rt = sb1.toString();
        sb1 = null;
        return rt;
    }

    private HashMap<String, Object> getColAndVal(BaseModele cible, ArrayList<String[]> attributs) throws Exception {
        HashMap<String, Object> rt = new HashMap<>();
        for (String[] att : attributs) {

            rt.put(att[1], callGetter(cible, att[0]));
        }
        return rt;
    }

    private String toAttribRequete(Object valiny) {
        if (valiny == null) {
            return "null";
        } else if (valiny.getClass() == Integer.class) {
            return ((Integer) valiny).toString();
        } else if (valiny.getClass() == String.class) {
            return "'" + valiny + "'";
        }

        return null;
    }

    private Object callGetter(Object cible, String attribut) throws Exception {
        Method antsoina = getGetter(cible, attribut);
        return antsoina.invoke(cible, null);
    }

    private void callSetter(Object cible, String attribut, Object arg) throws Exception {
        try {
            Method antsoina = getSetter(cible, attribut);
            Class rttype = antsoina.getParameterTypes()[0];
            //System.out.println(arg.getClass());
            //System.out.println(rttype);
            //System.out.println(antsoina);
//            if (rttype == Integer.TYPE) {
//
//                if (arg.getClass() == Integer.class) {
//                    //System.out.println("niditra teto");
//                    antsoina.invoke(cible, ((Integer) arg).intValue());
//                } else if (arg.getClass() == Float.class) {
//                    //System.out.println("niditra teto");
//                    antsoina.invoke(cible, ((Float) arg).intValue());
//                } else if (arg.getClass() == Double.class) {
//                    //System.out.println("niditra teto");
//                    antsoina.invoke(cible, ((Double) arg).intValue());
//                } else if (arg.getClass() == Long.class) {
//                    //System.out.println("niditra teto");
//                    antsoina.invoke(cible, ((Long) arg).intValue());
//                } else {
//                    antsoina.invoke(cible, ((Integer) arg).intValue());
//                }
//            } else {
                antsoina.invoke(cible, arg);
 //           }
            //System.out.println("tody");
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
            throw (ex);
        }
    }

    private ArrayList<String[]> getAttributsBaseModele(BaseModele p) throws Exception {
        //BaseModele efa misy id
        ArrayList<String[]> rt = null;
        try {
            rt = new ArrayList<>();
            rt.add(new String[]{"id", "id"});
            Field[] fields = p.getClass().getDeclaredFields();
            for (Field fl : fields) {

                if (fl.getAnnotation(Tsizy.class) != null) {
                    continue;
                }
                String nom = fl.getName();
                String nomTenaIzy = nom;
                Colonne cl = fl.getAnnotation(Colonne.class);
                if (cl != null) {
                    nomTenaIzy = cl.nom();
                }
                rt.add(new String[]{nom, nomTenaIzy});
                nom = null;
                nomTenaIzy = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw (ex);
        }
        return rt;
    }
}
