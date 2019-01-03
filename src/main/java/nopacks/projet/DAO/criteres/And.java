/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO.criteres;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class And  extends Critere{
    Critere prem,sec;

    public Critere getPrem() {
        return prem;
    }

    public Critere getSec() {
        return sec;
    }
    
    public And setCritere(Critere a, Critere b){
        this.prem=a;
        this.sec=b;
        return this;
    }
    @Override
    public String where(){
        return " ( "+prem.where()+prop()+sec.where()+" ) ";
    }
    @Override
     public And setCritere(String nom, Object valeur) throws Exception{
         throw new IllegalArgumentException();
     }
    @Override
    public String convertir() throws Exception{
        return " ( "+prem.convertir()+prop()+sec.convertir()+" ) ";
    }
    @Override
    protected String prop(){
        return " AND ";
    }

    @Override
    public void mifanaraka( ArrayList<Object> liste) {
        prem.mifanaraka(liste);
        sec.mifanaraka(liste);
    }
}
