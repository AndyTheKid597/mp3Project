/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO.criteres;

import java.util.ArrayList;
import nopacks.projet.modeles.BaseModele;

/**
 *
 * @author ASUS
 */
public class Requete {
    private BaseModele bm;
    private Critere critere;
    public Requete(){
        this(null);
    }
    
    public String contenu() throws Exception{ //iverifiena anle requete hoe normal tsara
        return critere.convertir();
    }
    
    public String where(){
        return critere.where();
    }
    
    public ArrayList<Object> mifanaraka(){
        ArrayList<Object> retour=new ArrayList<>();
        critere.mifanaraka(retour);
        return retour;
    }
    
    public Requete(BaseModele bm){
        this.bm=bm;
        critere= null;
    }
    public BaseModele getBm() {
        return bm;
    }

    public void setBm(BaseModele bm) {
        this.bm = bm;
    }
    
    public void setCritere(Critere ct){
        critere=ct;
    }
    
}
