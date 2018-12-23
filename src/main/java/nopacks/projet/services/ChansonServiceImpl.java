/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;

import java.util.List;
import javax.transaction.Transactional;
import nopacks.projet.DAO.HibernateDAO;
import nopacks.projet.modeles.Chanson;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ChansonServiceImpl implements ChansonService {

    private HibernateDAO chansonDAO;

    public void setChansonDAO(HibernateDAO chansonDAO) {
        this.chansonDAO = chansonDAO;
    }
    
    @Override
    @Transactional
    public void addChanson(Chanson p) {
        this.chansonDAO.save(p);
    }

    @Override
    public List<Chanson> listChansons() {
        return (List<Chanson>)(Object) this.chansonDAO.findAll();
    }
    
}
