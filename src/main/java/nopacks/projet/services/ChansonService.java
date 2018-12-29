/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;
import java.util.List;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.ResultatPagination;
/**
 *
 * @author ASUS
 */
public interface ChansonService {
    public ResultatPagination listChansonsPage(int page, int parpage);
    public void addChanson(Chanson p);
    public void updateChanson(Chanson p);
    public Chanson findChansonById(int id);
    public List<Chanson> listChansons();
    public void deleteChanson(int id);
}
