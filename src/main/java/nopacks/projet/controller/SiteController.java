/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import nopacks.projet.services.ChansonService;
import nopacks.projet.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/site")
@SessionAttributes("clientObjet")
public class SiteController {
        private ChansonService chansonService;
    private ClientService clientService;
    @Autowired
    ServletContext context;

    @Autowired(required = true)
    @Qualifier(value = "chansonService")
    public void setChansonService(ChansonService ps) {
        this.chansonService = ps;
    }

    @Autowired(required = true)
    @Qualifier(value = "clientService")
    public void setClientService(ClientService ps) {
        this.clientService = ps;
    }
    
    @RequestMapping("/index.html")
    public ModelAndView accueil() {
        ModelAndView rt = new ModelAndView("accueilfront");
        rt.addObject("plusEcoutees",this.chansonService.findChansonsPlusEcoutees(0,10));
        //rt.addObject("listeChansons",this.chansonService.listChansonsPage(0, 10).getResultats());
        rt.addObject("lien","accueil");
        return rt;
    }
}