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
import nopacks.projet.modeles.ResultatPagination;
import nopacks.projet.services.ChansonService;
import nopacks.projet.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Qualifier(value = "chansonServiceHibernate")
    public void setChansonService(ChansonService ps) {
        this.chansonService = ps;
    }

    @Autowired(required = true)
    @Qualifier(value = "clientServiceHibernate")
    public void setClientService(ClientService ps) {
        this.clientService = ps;
    }
    
    @RequestMapping("/index.html")
    public ModelAndView accueil() {
        ModelAndView rt = new ModelAndView("accueilfront");
        rt.addObject("plusEcoutees",this.chansonService.findChansonsPlusEcoutees(0,10));
        rt.addObject("derniersAjoutes",this.chansonService.findChansonsLast(0,10));
        //rt.addObject("listeChansons",this.chansonService.listChansonsPage(0, 10).getResultats());
        rt.addObject("lien","accueil");
        return rt;
    }
    @RequestMapping("/single/{id}")
    public ModelAndView anakray(@PathVariable(value="id") int idhira){
        ModelAndView md= new ModelAndView("f_prod");
        Chanson hira=this.chansonService.findChansonById(idhira);
        this.chansonService.counterPlusChanson(hira);
          md.addObject("hira",hira);
          md.addObject("lien","single");
        return md;
    }
    @RequestMapping("/resultat")
    public ModelAndView rech(@RequestParam(value="rech",required=false) String succ) {
        ModelAndView rt = new ModelAndView("resultat");
        ResultatPagination rp=this.chansonService.rechercheSimpleChanson(succ, 0, 10);
        int parpage=rp.getParPage();
        int nombre=(int)rp.getTailleTotale();
        int page=nombre/parpage;
        rt.addObject("page",page);
        rt.addObject("resultat",this.chansonService.rechercheSimpleChanson(succ, 0, 10));
        rt.addObject("all",this.chansonService.findChansonsLast(0,10));
        
        //rt.addObject("listeChansons",this.chansonService.listChansonsPage(0, 10).getResultats());
        rt.addObject("lien","resultat");
        return rt;
    }
   @RequestMapping(value="/loginclient")
    public ModelAndView loginAccueil(        @RequestParam(value="e",required=false) String err) {
        

         ModelAndView rt = new ModelAndView("loginpage");
        rt.addObject("client", new Client());
        rt.addObject("erreur", err);
        return rt;
    }
     @RequestMapping(value = "/testconnection", method = RequestMethod.POST)
    public String testerlog(@ModelAttribute("client") Client p, RedirectAttributes redirectAttrs) {
        Client rt = this.clientService.testLogin(p);
        System.out.println("tonfa testerlogin");
        if (rt == null) {
            System.out.println("niredirect erreur");
            return "redirect:/loginclient?e=1";
        } else {
            System.out.println("niredirect flash");
            redirectAttrs.addFlashAttribute("adminObjet", rt);
            return "redirect:/site/index.html";
        }
    }
}
