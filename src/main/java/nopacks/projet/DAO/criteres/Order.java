/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO.criteres;

/**
 *
 * @author ASUS
 */
public class Order {
    private String nom;
    private String type;
    public Order(String nom, String type){
        this.nom=nom;
        this.type=type;
    }
    public String getRay(){
        return " order by "+nom+" "+type+" ";
    }
}
