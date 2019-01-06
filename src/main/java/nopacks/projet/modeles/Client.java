/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.modeles;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ASUS
 */
@Entity
public class Client extends BaseModele implements Serializable{
    private String nom,login,mdp,email;
    private Boolean est_admin;
    public Client(){
        est_admin=false;
    }
    public Boolean getEst_admin() {
        return est_admin;
    }
    public String hasher(){
        String rt="";
        if(nom!=null) rt+=nom;
        if(mdp!=null) rt+=mdp;
        if(login!=null) rt+=login;
        if(email!=null) rt+=email;
        if(est_admin!=null) rt+=est_admin;
        return rt.hashCode()+"";
    }
    public void setEst_admin(Boolean est_admin) {
        this.est_admin = est_admin;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
