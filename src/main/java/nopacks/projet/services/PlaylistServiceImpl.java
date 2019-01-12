/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nopacks.projet.DAO.InterfaceDAO;
import nopacks.projet.DAO.criteres.CritereGenerator;
import nopacks.projet.DAO.criteres.Requete;
import nopacks.projet.modeles.BaseModele;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.Client;
import nopacks.projet.modeles.Playlist;
import nopacks.projet.modeles.PlaylistDetails;
import nopacks.projet.modeles.ResultatPagination;

/**
 *
 * @author ASUS
 */
public class PlaylistServiceImpl implements PlaylistService {

    private InterfaceDAO playlistDAO;
    public void setPlaylistDAO(InterfaceDAO playlistDAO){
        this.playlistDAO=playlistDAO;
    }
    
    @Override
    public Playlist createPlaylist(Client ct, String nom) {
        Playlist pl=new Playlist();
        pl.setIdclient(ct.getId());
        pl.setNom(nom);
        this.playlistDAO.save(pl);
        pl.setId(this.playlistDAO.maxID(pl));
        return pl;
    }

    @Override
    public List<BaseModele> getDetails(Playlist p) {
        try {
            Requete rq=new Requete(new PlaylistDetails());
            rq.setCritere(CritereGenerator.eq("idplaylist", p.getId()));
            rq.setOrder(CritereGenerator.desc("id"));
            ResultatPagination rp= this.playlistDAO.findAllPage(rq, 0, -1);
            return rp.getResultats();
        } catch (Exception ex) {
            Logger.getLogger(PlaylistServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return null;
        }
        
    }

    @Override
    public List<BaseModele> findPlaylist(Client ct) {
        try {
            Requete rq=new Requete(new Playlist());
            rq.setCritere(CritereGenerator.eq("idclient", ct.getId()));
            ResultatPagination rp= this.playlistDAO.findAllPage(rq, 0, -1);
            return rp.getResultats();
        } catch (Exception ex) {
            Logger.getLogger(PlaylistServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addChansonToPlaylist(Playlist p, Chanson ct) {
        PlaylistDetails pdt=new PlaylistDetails();
        pdt.setIdchanson(ct.getId());
        pdt.setIdplaylist(p.getId());
        this.playlistDAO.save(pdt);
    }

    @Override
    public void deleteDetail(PlaylistDetails pdt) {
        this.playlistDAO.delete(pdt);
    }

    @Override
    public void deletePlaylist(Playlist p) {
        this.playlistDAO.delete(p);
    }

    @Override
    public void majPlaylist(Playlist p) {
        this.playlistDAO.update(p);
    }
    
}
