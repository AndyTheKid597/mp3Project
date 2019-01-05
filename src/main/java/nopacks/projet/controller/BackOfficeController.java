/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.Client;
import nopacks.projet.services.ChansonService;
import nopacks.projet.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes("adminObjet")
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

    @RequestMapping("/accueil")
    public ModelAndView accueil(HttpServletRequest req, Model md) {
        if(!testLogged(req) && !md.asMap().containsKey("adminObjet")) return new ModelAndView("redirect:/admin/login?e=2");
        Client ct=null;
        if(testLogged(req)) ct=(Client)req.getAttribute("adminObject");
        if(md.asMap().containsKey("adminObjet")) ct=(Client)md.asMap().get("adminObjet");
        ModelAndView rt = new ModelAndView("accueilback");
        rt.addObject("adminObjet",ct);
        rt.addObject("lien","accueil");
        return rt;
    }

    public boolean testLogged(HttpServletRequest req){
       return req.getParameterMap().containsKey("adminObjet");
    }
    
    @RequestMapping("/")
    public String redirAccueil() {
        return "redirect:/admin/login";
    }

    @RequestMapping(value="/login",params = {"e"})
    public ModelAndView loginAccueil(        @RequestParam(value="e",required=false) String err) {
        
        ModelAndView rt = new ModelAndView("login");
        rt.addObject("client", new Client());
         rt.addObject("erreur",err);
        return rt;
    }

    @RequestMapping(value = "/tester", method = RequestMethod.POST)
    public String testerLogin(@ModelAttribute("client") Client p, RedirectAttributes redirectAttrs) {
        Client rt = this.clientService.testLoginAdmin(p);
        System.out.println("tonfa testerlogin");
        if (rt == null) {
            System.out.println("niredirect erreur");
            return "redirect:/admin/login?e=1";
        } else {
            System.out.println("niredirect flash");
            redirectAttrs.addFlashAttribute("adminObjet", rt);
            return "redirect:/admin/accueil";
        }
    }
    

    @RequestMapping("/logout")
    public ModelAndView deconnecter(SessionStatus stt) {
        stt.setComplete();
        return new ModelAndView("login");
    }

    private void adika(Client src, Client dest) {
        dest.setId(src.getId());
        dest.setNom(src.getNom());
        dest.setEmail(src.getEmail());
    }

}
