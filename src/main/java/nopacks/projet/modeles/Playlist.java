/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.modeles;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@nopacks.projet.DAO.annotations.Table(nom = "playlist")
@Entity
@Table(name = "playlist")
public class Playlist extends BaseModele {

    private String nom;
    private Integer idclient;

    public Playlist(){
        idclient=null;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

}
