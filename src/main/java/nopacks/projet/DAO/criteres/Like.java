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
public class Like extends Critere{

    @Override
    protected String prop(){
        return " LIKE ";
    }
    @Override
    protected String valeurReelle() throws Exception{
        return "'"+valeur.toString()+"'";
    }
    
    @Override
    public String where(){
        return nom+prop()+" ? ";
    }
        @Override
    public void mifanaraka(ArrayList<Object> liste) {
        liste.add("%"+valeur+"%");
    }
}
