/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;

import java.util.ArrayList;
import java.util.List;
import nopacks.projet.modeles.BaseModele;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.Client;
import nopacks.projet.modeles.Playlist;
import nopacks.projet.modeles.PlaylistDetails;

/**
 *
 * @author ASUS
 */
public interface PlaylistService {
    public Playlist createPlaylist(Client ct, String nom);
    public List<PlaylistDetails> getDetails(Playlist p);
    public List<Playlist> findPlaylist(Client ct);
    public void addChansonToPlaylist(Playlist p, Chanson ct);
    public void deleteDetail(PlaylistDetails pdt);
    public void deletePlaylist(Playlist p);
    public void majPlaylist(Playlist p);
    public Playlist getPlaylistById(int id);
}
