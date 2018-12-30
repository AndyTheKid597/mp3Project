/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;

import java.util.List;
import javax.transaction.Transactional;
import nopacks.projet.DAO.HibernateDAO;
import nopacks.projet.DAO.InterfaceDAO;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.ResultatPagination;
import nopacks.projet.mp3.mp3Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ChansonServiceImpl implements ChansonService {

    private InterfaceDAO chansonDAO;
     private   String UPLOAD_DIRECTORY="E:/mp3/";
     private mp3Finder finder;

     public void setFinder(mp3Finder finder){
         this.finder=finder;
     }
     
    public void setChansonDAO(InterfaceDAO chansonDAO) {
        this.chansonDAO = chansonDAO;
    }
    
    @Override
    @Transactional
    public void addChanson(Chanson p) {
        this.chansonDAO.beginTransaction();
        this.chansonDAO.save(p);
        this.chansonDAO.endTransaction();
    }

    @Override
    public List<Chanson> listChansons() {
        Chanson ct=new Chanson();
        return (List<Chanson>)(Object) this.chansonDAO.findAll(ct);
    }

    @Override
    public Chanson findChansonById(int id) {
        Chanson ct=new Chanson();
        ct.setId(id);
        return (Chanson) this.chansonDAO.findById(ct);
    }

    @Override
    @Transactional
    public void updateChanson(Chanson p) {
        this.chansonDAO.update(p);
    }    

    @Override
    public void deleteChanson(int id) {
        Chanson hira=new Chanson();
        hira.setId(id);
        this.chansonDAO.delete(hira);
    }

    @Override
    public ResultatPagination listChansonsPage(int page, int parpage) {
       return this.chansonDAO.findAllPage(new Chanson(), page, parpage);
    }

    @Override
    public void setUploadDir(String upd) {
           UPLOAD_DIRECTORY=upd;
    }

    @Override
    public String getUploadDir() {
            return UPLOAD_DIRECTORY;
    }

    @Override
    public List<String> findAllMp3InFolder() {
     List<String> rt= this.finder.findAllInFolder(UPLOAD_DIRECTORY);
     //System.out.println(rt.size());
     return rt;
    }
}
