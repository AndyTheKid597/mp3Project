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
public abstract class Critere {
    protected Object valeur;
    protected String nom;
    protected int offset;
    public Critere(){
        offset=0;
    }
    public int getOffset(){
        return offset;
    }
    public Object getValeur() {
        return valeur;
    }

    public String getNom() {
        return nom;
    }
    public abstract String where();
    public abstract void mifanaraka( ArrayList<Object> liste);
    public Critere setCritere(String nom, Object valeur) throws Exception{
        this.nom=nom;
        this.valeur=valeur;
        if(this instanceof Like){
            valeur="%"+valeur+"%";
        }
        return this;
    }
    protected abstract String prop();
    public String convertir() throws Exception{
        return nom+prop()+valeurReelle();
    }
    protected String valeurReelle() throws Exception{
        if(valeur instanceof Number){
            if(valeur instanceof Integer){
                return ((Integer)valeur).toString();
            } //else if bedabe
            else if(valeur instanceof Float){
                return ((Float)valeur).toString();
            }
            else {
                return valeur.toString(); //default
            }
        } //else if bedabe
        else if(valeur.getClass()==String.class){
            return "'"+valeur+"'";
        }else
        {
            return valeur.toString();
        }
    }
}
