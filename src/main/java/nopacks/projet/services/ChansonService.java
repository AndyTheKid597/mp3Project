/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;
import java.util.List;
import nopacks.projet.DAO.InterfaceDAO;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.Config;
import nopacks.projet.modeles.ResultatPagination;
import nopacks.projet.modeles.actualisationStatut;
import nopacks.projet.mp3.mp3Finder;
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
    public void setUploadDir(String upd);
    public String getUploadDir();
    public List<String> findAllMp3InFolder();
    public void setChansonDAO(InterfaceDAO chansonDAO);
    public void setFinder(mp3Finder finder);
    public void initialiserBF(actualisationStatut sync_stat);
    public Config getLastDate();
    public Chanson fromFile(String nomfichier);
    public ResultatPagination rechercheSimpleChanson(String q, int page, int parpage);
    public ResultatPagination findChansonsPlusEcoutees(int page, int parpage);
    public void counterPlusChanson(Chanson p);
}
