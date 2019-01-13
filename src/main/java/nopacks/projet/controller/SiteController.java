/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import nopacks.projet.modeles.BaseModele;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.Client;
import nopacks.projet.modeles.ResultatPagination;
import nopacks.projet.modeles.Playlist;
import nopacks.projet.modeles.PlaylistDetails;
import nopacks.projet.services.ChansonService;
import nopacks.projet.services.ClientService;
import nopacks.projet.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private PlaylistService playlistService;
    @Autowired
    ServletContext context;

    @Autowired(required = true)
    @Qualifier(value = "chansonServiceHibernate")
    public void setChansonService(ChansonService ps) {
        this.chansonService = ps;
    }

    
    @Autowired(required = true)
    @Qualifier(value = "playlistServiceHibernate")
    public void setPlaylistService(PlaylistService ps) {
        this.playlistService = ps;
    }
    @Autowired(required = true)
    @Qualifier(value = "clientServiceHibernate")
    public void setClientService(ClientService ps) {
        this.clientService = ps;
    }

    public boolean testLogged(HttpServletRequest req) {
        return req.getSession().getAttribute("clientObjet") != null;
    }

    @RequestMapping("/index.html")
    public ModelAndView accueil(HttpServletRequest req, Model md) {

        Client ct = null;
        if (testLogged(req)) {
            ct = (Client) req.getAttribute("adminObject");
        }
        if (md.asMap().containsKey("adminObjet")) {
            ct = (Client) md.asMap().get("adminObjet");
        }
        ModelAndView rt = new ModelAndView("accueilfront");
        rt.addObject("plusEcoutees", this.chansonService.findChansonsPlusEcoutees(0, 10));
        rt.addObject("derniersAjoutes", this.chansonService.findChansonsLast(0, 10));
        //rt.addObject("listeChansons",this.chansonService.listChansonsPage(0, 10).getResultats());
        rt.addObject("lien", "accueil");
        return rt;
    }

    @RequestMapping("/single/{id}")
    public ModelAndView anakray(@PathVariable(value = "id") int idhira, HttpServletRequest req) {
        ModelAndView md = new ModelAndView("f_prod");
        Chanson hira = this.chansonService.findChansonById(idhira);
        this.chansonService.counterPlusChanson(hira);
        ResultatPagination rp1=this.chansonService.rechercheSimpleChanson(hira.getAuteur(), 0, 5);
        ResultatPagination rp2=this.chansonService.rechercheSimpleChanson(hira.getGenre(), 0, 5);
        md.addObject("hira", hira);
        md.addObject("memeAuteur",rp1);
        md.addObject("memeGenre",rp2);
        md.addObject("lien", "single");
        return md;
    }

    @RequestMapping("/singlepl/{id}/{rang}")
    public ModelAndView anakraypl(@PathVariable(value = "id") int idhira, @PathVariable(value = "rang") int rang, HttpServletRequest req) {
        ModelAndView md = new ModelAndView("g_prod");
        Playlist pl=this.playlistService.getPlaylistById(idhira);
        List<PlaylistDetails> pdt=(List<PlaylistDetails>)(Object)this.playlistService.getDetails(pl); 
        ArrayList<Chanson> lc=new ArrayList<>();
        for(PlaylistDetails ray:pdt){
            lc.add(this.chansonService.findChansonById(ray.getIdchanson()));
            
        }
        System.out.println("taille hira lpaylist "+lc.size());
        Chanson hira = this.chansonService.findChansonById(pdt.get(rang).getIdchanson());
        this.chansonService.counterPlusChanson(hira);
        md.addObject("hira", hira);
        md.addObject("listeHira", lc);
        md.addObject("playlist", pl);
        md.addObject("idsd", idhira);
        if(rang<lc.size()-1) md.addObject("suivant",rang+1);
        else md.addObject("suivant", null);

        if(rang>0) md.addObject("precedent",rang-1);
                else md.addObject("precedent", null);
        
        md.addObject("lien", "singlepl");
        return md;
    }
    
    @RequestMapping("/playlist")
    public ModelAndView playl( HttpServletRequest req) {
        if (!testLogged(req)) return new ModelAndView("redirect:/site/loginclient?e=2");
        ModelAndView md = new ModelAndView("listpl");

        Client ct=(Client)req.getSession().getAttribute("clientObjet");
        System.out.println("anarana "+ct.getNom());
        List<Playlist> lp=this.playlistService.findPlaylist(ct);
        System.out.println("taille "+lp.size());
        md.addObject("plusEcoutees", this.chansonService.findChansonsPlusEcoutees(0, 10));
        md.addObject("derniersAjoutes", lp);
        md.addObject("lien", "playlist");
        return md;
    }
    
    @RequestMapping("/resultat")
    public ModelAndView rech(@RequestParam(value = "rech", required = false) String succ, HttpServletRequest req) {
        ModelAndView rt = new ModelAndView("resultat");
        ResultatPagination rp = this.chansonService.rechercheSimpleChanson(succ, 0, 10);
        int parpage = rp.getParPage();
        int nombre = (int) rp.getTailleTotale();
        int page = nombre / parpage;
        rt.addObject("page", page);
        rt.addObject("resultat", this.chansonService.rechercheSimpleChanson(succ, 0, 10));
        rt.addObject("all", this.chansonService.findChansonsLast(0, 10));

        //rt.addObject("listeChansons",this.chansonService.listChansonsPage(0, 10).getResultats());
        rt.addObject("lien", "resultat");
        return rt;
    }

    @RequestMapping(value = "/loginclient")
    public ModelAndView loginAccueil(@RequestParam(value = "e", required = false) String err) {

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
            redirectAttrs.addFlashAttribute("clientObjet", rt);
            return "redirect:/site/index.html";
        }
    }
}
