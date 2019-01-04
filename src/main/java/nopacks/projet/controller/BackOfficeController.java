/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.controller;

import javax.servlet.ServletContext;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.Client;
import nopacks.projet.services.ChansonService;
import nopacks.projet.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/admin")
public class BackOfficeController {
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
    
    
    @RequestMapping("/")
    public ModelAndView loginAccueil(){
        ModelAndView rt=new ModelAndView("login");
        rt.addObject("client", new Client());
        return rt;
    }
    @RequestMapping(value="/tester", method = RequestMethod.POST)
    public String testerLogin(@ModelAttribute("client") Client p){
        Client rt=this.clientService.testLogin(p);
        if(rt==null) return "redirect:/?e=1";
        else return "redirect:/?id="+rt.getId();
    }
}
