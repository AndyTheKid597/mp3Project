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
@nopacks.projet.DAO.annotations.Table(nom = "details_pl")
@Entity
@Table(name = "details_pl")
public class PlaylistDetails extends BaseModele{
    private Integer idchanson,idplaylist;

    public PlaylistDetails(){
        idchanson=null;
        idplaylist=null;
    }
    
    public Integer getIdchanson() {
        return idchanson;
    }

    public void setIdchanson(Integer idchanson) {
        this.idchanson = idchanson;
    }

    public Integer getIdplaylist() {
        return idplaylist;
    }

    public void setIdplaylist(Integer playlist) {
        this.idplaylist = playlist;
    }
    
}
