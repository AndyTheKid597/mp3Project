/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import nopacks.projet.DAO.annotations.Colonne;
import nopacks.projet.DAO.annotations.Tsizy;
import nopacks.projet.modeles.BaseModele;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.ResultatPagination;

/**
 *
 * @author ASUS
 */
public class GeneriqueDAO implements InterfaceDAO {

    private Connexion connexion;

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @PostConstruct
    public void tester() {
        try {
            Chanson testchan = new Chanson();
            testchan.setAuteur("itestena anle reflection");
            ArrayList<String[]> rtt = getAttributsBaseModele(testchan);
            for (String[] tp : rtt) {
                System.out.println(tp[0] + "   " + tp[1]);
            }
            System.out.println(" valeur auteru : " + callGetter(testchan, "auteur"));
            System.out.println(" classe : " + callGetter(testchan, "id").getClass()); // bon ny int sy ny otranzan zany anaty objet Integer
            HashMap<String,Object> vc= getColAndVal(testchan,rtt);
            System.out.println("fin");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Method getGetter(Object cible, String nom) throws Exception { //atribut : nomChanson => getNomChanson
        String charac = nom.substring(0, 1).toUpperCase();
        String realattrib = charac + nom.substring(1);
        Method rt = cible.getClass().getMethod("get" + realattrib, null);
        return rt;
    }

    private HashMap<String,Object> getColAndVal(BaseModele cible) throws Exception {
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

    private HashMap<String,Object> getColAndVal(BaseModele cible, ArrayList<String[]> attributs) throws Exception {
        HashMap<String,Object> rt = new HashMap<>();
        for (String[] att : attributs) {
            
            rt.put(att[1], callGetter(cible, att[0]));
        }
        return rt;
    }

    private String toAttribRequete(Object valiny) {
        if (valiny==null){
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
