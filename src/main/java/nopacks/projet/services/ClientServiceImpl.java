/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import nopacks.projet.DAO.InterfaceDAO;
import nopacks.projet.DAO.criteres.CritereGenerator;
import nopacks.projet.DAO.criteres.Requete;
import nopacks.projet.modeles.Client;

/**
 *
 * @author ASUS
 */
public class ClientServiceImpl implements ClientService {

    private InterfaceDAO clientDAO;

    public void setClientDAO(InterfaceDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client testLogin(Client c) {
        try {
            Requete rq = new Requete(c);
            System.out.println(c.getLogin()+" "+c.getMdp());
            rq.setCritere(CritereGenerator.and(CritereGenerator.eq("login", c.getLogin()), CritereGenerator.eq("mdp", c.getMdp())));
            System.out.println(rq.contenu());
            Client retour = (Client) this.clientDAO.findBy(rq);
           if(retour!=null) retour.setMdp("");
            return retour;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Client inscrireClient(Client c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client updateClient(Client c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
