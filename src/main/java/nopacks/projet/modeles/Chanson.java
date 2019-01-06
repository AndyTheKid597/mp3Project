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
@nopacks.projet.DAO.annotations.Table(nom="bibliotheque")
@Entity
@Table(name="bibliotheque")
public class Chanson extends BaseModele{
    private String nomfichier,titre,d_up,d_down,auteur,album,date,commentaire,genre,track,image;
    private Integer counter;

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    private Long duration;
    public Chanson(){
        duration=new Long(0);
        counter=new Integer(0);
    }
    public String getNomfichier() {
        return nomfichier;
    }

    public void setNomfichier(String nomfichier) {
        this.nomfichier = nomfichier;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getD_up() {
        return d_up;
    }

    public void setD_up(String d_up) {
        this.d_up = d_up;
    }

    public String getD_down() {
        return d_down;
    }

    public void setD_down(String d_down) {
        this.d_down = d_down;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public long getDuration() { //boxing
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
    
    
    @Override
    public String toString(){
        return nomfichier;
    }
}
