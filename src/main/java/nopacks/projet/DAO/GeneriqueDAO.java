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
import nopacks.projet.DAO.Cacher.Cacher;
import nopacks.projet.DAO.annotations.Colonne;
import nopacks.projet.DAO.annotations.Table;
import nopacks.projet.DAO.annotations.Tsizy;
import nopacks.projet.DAO.criteres.CritereGenerator;
import nopacks.projet.DAO.criteres.Requete;
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
    private Cacher cacher;
    
    public void setCacher(Cacher cacher){
        this.cacher=cacher;
    }
    
    
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
        try {
            Connection ct = connexion.getConnection();
            ArrayList<String[]> attribs = this.getAttributsBaseModele(p);
            String[] liste = getCol(p, attribs);
            String rqt = "insert into " + this.getNomTable(p);
            String ctrqt = "(" + liste[0] + ")";
            String interrogation = "(" + liste[1] + ")";
            rqt = rqt + ctrqt + " values " + interrogation;
            System.out.println(rqt);
            PreparedStatement ps = ct.prepareStatement(rqt);
            int i = 1;
            for (String[] att : attribs) {
                if (att[1].equals("id")) {
                    continue;
                }
                ps.setObject(i, this.callGetter(p, att[0]));
                i++;
            }
            System.out.println(ps);
            // ps.executeUpdate();
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    @PostConstruct
    public void testatge(){
        HashMap<String,HashMap<String,String>> config=this.cacher.getcf();
        System.out.println("postiniiiiiiittt");
        System.out.println(config.size());
        String ind=config.keySet().iterator().next();
        System.out.println(ind);
        System.out.println(config.get(ind).get("duree"));
    }
    
    @Override
    public BaseModele findById(BaseModele p) {
        try {
            PreparedStatement ps = connexion.getConnection().prepareStatement("select * from " + this.getNomTable(p) + " where id=?");
            ps.setInt(1, p.getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<String[]> attribs = this.getAttributsBaseModele(p);
            if (!rs.next()) {
                return null;
            }
            BaseModele resultat = rsToObject(p, rs, attribs);
            return resultat;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultatPagination findAllPage(BaseModele p, int page, int parpage) {
        ResultatPagination rp = new ResultatPagination();
        List<BaseModele> rt = null;
        Connection cx = null;
        int count = 0;
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
            Statement ps1 = cx.createStatement();
            ResultSet rs2 = ps1.executeQuery("select count(*) from " + n_table);
            rs2.next();
            count = rs2.getInt(1);
            rs2.close();
            ps1.close();
            Statement ps = cx.createStatement();

            ResultSet rs = ps.executeQuery("select * from " + n_table + " limit " + parpage + " offset " + (page * parpage));
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
            rp.setResultats(rt);
            rp.setNumPage(page);
            rp.setParPage(parpage);
            rp.setTailleTotale(count);
            return rp;
        }
    }

    @Override
    public void update(BaseModele p) {
        try {
            Connection ct = connexion.getConnection();
            ArrayList<String[]> attribs = this.getAttributsBaseModele(p);
            HashMap<String, Object> cv = this.getColAndVal(p);
            String rqt = " update " + this.getNomTable(p) + " set ";
            String where = " where id=" + p.getId();
            
            StringBuilder setena = new StringBuilder();
            boolean vol = false;
            for (String[] att : attribs) {
                if (att[1].equals("id")) {
                    continue;
                }
                if (vol) {
                    setena.append(" , ");
                }
                setena.append(att[0]);
                setena.append(" = ? ");
                vol = true;
            }
            String st = setena.toString();
            rqt = rqt + st + where;
            System.out.println("update request");
            System.out.println(rqt);
            PreparedStatement ps = ct.prepareStatement(rqt);
            int i = 1;
            for (String[] att : attribs) {
                if (att[1].equals("id")) {
                    continue;
                }
                ps.setObject(i, cv.get(att[0]));
                i++;
            }
            System.out.println(ps);
            //ps.executeUpdate();
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    @Override
    public void delete(BaseModele p) {
        try {
            String rqt = "delete from " + this.getNomTable(p) + " where id=" + p.getId();
            Statement st = connexion.getConnection().createStatement();
            st.executeUpdate(rqt);
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        System.out.println(attribs.size());
        for (String[] ray : attribs) {
            System.out.println(ray[1]);
            Object averiny = rs.getObject(ray[1]);
            if (averiny == null) {
                continue;
            }
            System.out.println(ray[1]+" "+averiny);
            callSetter(rt, ray[0], averiny);
            System.out.println(" manaraka amzay ");
        }
        System.out.println("on retourne");
        return rt;
    }

    private String getNomTable(BaseModele p) {
        Table at = p.getClass().getAnnotation(Table.class);
        if (at != null) {
            return at.nom();
        }
        return p.getClass().getSimpleName();
    }


    public void tester() {
        try {
            Chanson testchan = new Chanson();
            testchan.setAuteur("itestena anle reflection");
            List<BaseModele> liste = findAll(testchan);
            for (BaseModele bm : liste) {
                Chanson tpchan = ((Chanson) bm);
                System.out.println(tpchan.getId() + " " + tpchan.getNomfichier());
            }
            Requete rq = new Requete(testchan);
            rq.setCritere(CritereGenerator.or(CritereGenerator.like("nomfichier", "a"), CritereGenerator.gteq("id", new Integer(5))));
            System.out.println(rq.contenu());
            System.out.println(rq.where());
           ResultatPagination liste2 = this.findAllPage(rq, 0, 10);
           System.out.println("tonga farany");
                      for (BaseModele bm : liste2.getResultats()) {
                Chanson tpchan = ((Chanson) bm);
                System.out.println(tpchan.getId() + " " + tpchan.getNomfichier());
            }
            // this.update(testchan);
            // this.save(liste.get(0)); //efa mandeha
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw (ex);
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

    private String[] getCol(BaseModele cible) throws Exception {
        return getCol(cible, getAttributsBaseModele(cible));
    }

    private String[] getCol(BaseModele cible, ArrayList<String[]> attributs) throws Exception {
        String rt;
        String rt2;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        boolean fst = true;
        for (String[] att : attributs) {
            if (att[1].equals("id")) {
                continue;
            }
            if (!fst) {
                sb1.append(" , ");
                sb2.append(" , ");
            }
            sb1.append(att[1]);
            sb2.append("?");
            fst = false;
        }
        rt = sb1.toString();
        rt2 = sb2.toString();
        sb1 = null;
        return new String[]{rt, rt2};
    }

    private HashMap<String, Object> getColAndVal(BaseModele cible, ArrayList<String[]> attributs) throws Exception {
        HashMap<String, Object> rt = new HashMap<>();
        for (String[] att : attributs) {
            System.out.println(cible);
            System.out.println(att[0]);
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
        System.out.println(antsoina);
        return antsoina.invoke(cible, null);
    }

    private void callSetter(Object cible, String attribut, Object arg) throws Exception {
        try {
            Method antsoina = getSetter(cible, attribut);
            Class rttype = antsoina.getParameterTypes()[0];
            System.out.println("rttype "+rttype);
            System.out.println("izy "+arg.getClass());
            if(arg.getClass()==Long.class && rttype==Integer.class){
                arg= new Integer(((Long)arg).intValue());
                System.out.println("vocatst "+arg.getClass());
            }
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
            System.out.println(ex.getMessage());
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

    @Override
    public ResultatPagination findAllPage(Requete rq, int page, int parpage) {
        ResultatPagination rp = new ResultatPagination();
        BaseModele p = rq.getBm();
        List<BaseModele> rt = null;
        Connection cx = null;
        int count = 0;
        try {
            //System.out.println("findAll ");
            //System.out.println();
            //System.out.println();
            //System.out.println();
            cx = connexion.getConnection();
            String n_table = getNomTable(p);
            //String whcon=rq.where();
            //String where=" where ";
            String whcon = rq.where();
        String where = " where ";
        String od= rq.orderby();
        if (whcon == null) {
            where = "";
        } else {
            where = where + whcon;
        }
          //  if(whcon!=null) where= where+rq.where();
         //   else where="";
                        ArrayList<String[]> attr = this.getAttributsBaseModele(p);
            //System.out.println(attr.size());
            //System.out.println("select * from "+n_table);
            PreparedStatement ps1 = cx.prepareStatement("select count(*) from " + n_table+where);
            ArrayList<Object> conds=null;
            int t=0;
            if(whcon!=null){
             conds=rq.mifanaraka();
            t=conds.size();

             System.out.println("select count(*) from " + n_table+where+od);
             
            for(int i=0;i<t;i++){
            System.out.println(conds.get(i));
                ps1.setObject(i+1, conds.get(i));
            }
            }
            ResultSet rs2 = ps1.executeQuery();
            rs2.next();
            count = rs2.getInt(1);
            rs2.close();
            ps1.close();
            PreparedStatement ps = cx.prepareStatement("select * from " + n_table +where+od+ " limit ? offset ? ");
          if(whcon!=null){
            for(int i=0;i<t;i++){
                ps.setObject(i+1, conds.get(i));
            }
          }
            ps.setInt(t+1,parpage);
            ps.setInt(t+2,(page * parpage));
            
            ResultSet rs = ps.executeQuery();
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
            rp.setResultats(rt);
            rp.setNumPage(page);
            rp.setParPage(parpage);
            rp.setTailleTotale(count);
            return rp;
        }
    }

    @Override
    public BaseModele findBy(Requete rq) {
        
    BaseModele p = rq.getBm();
        BaseModele retour = null;
        Connection cx = null;
        int count = 0;
        try {
            //System.out.println("findAll ");
            //System.out.println();
            //System.out.println();
            //System.out.println();
            Object rttest=this.cacher.get(rq);
        if(rttest!=null) retour=(BaseModele)rttest;
            cx = connexion.getConnection();
            String n_table = getNomTable(p);
            String whcon=rq.where();
            String where=" where ";
            if(whcon==null) where="";
            else where=where+whcon;
            ArrayList<Object> conds=null;
            String od=rq.orderby();
             PreparedStatement ps = cx.prepareStatement("select * from " + n_table +where+od);
            ArrayList<String[]> attr = this.getAttributsBaseModele(p);
             if(whcon!=null){
                
            conds=rq.mifanaraka();
            int t=conds.size();

           
          System.out.println("select * from " + n_table +where);
          
            for(int i=0;i<t;i++){
                System.out.println(conds.get(i));
                ps.setObject(i+1, conds.get(i));
            }
             }
            ResultSet rs = ps.executeQuery();
            if(!rs.next()) return null;
                System.out.println(" next ");
                retour= (BaseModele) rsToObject(p, rs, attr);
            System.out.println(retour.getId());
            rs.close();
            ps.close();
                  this.cacher.add(rq, retour);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            throw (ex);
        } finally {
            try {
                if (cx != null) {
                    cx.close();
                }
            } catch (Exception ex) {

            }
            System.out.println("add depuis generique dao");
       
            return retour;
        }   
    }

    @Override
    public void deleteAll(BaseModele p) {
        try {
            String rqt = "delete from " + this.getNomTable(p);
            Statement st = connexion.getConnection().createStatement();
            st.executeUpdate(rqt);
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int maxID(BaseModele p) {
         try {
            PreparedStatement ps = connexion.getConnection().prepareStatement("select max(id) from " + this.getNomTable(p));
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
