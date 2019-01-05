/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.Client;
import nopacks.projet.modeles.actualisationStatut;
import nopacks.projet.services.ChansonService;
import nopacks.projet.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
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

    private actualisationStatut sync_status;
    
    
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
        rt.addObject("valiny",this.chansonService.listChansonsPage(0,10));
        //rt.addObject("listeChansons",this.chansonService.listChansonsPage(0, 10).getResultats());
        rt.addObject("adminObjet",ct);
        rt.addObject("lien","accueil");
        return rt;
    }
    
     @RequestMapping("/accueil/{page}/{parpage}")
    public ModelAndView accueil(HttpServletRequest req, @RequestParam(value="page") int page, @RequestParam(value="parpage") int parpage) {
        if(!testLogged(req)) return new ModelAndView("redirect:/admin/login?e=2");
        ModelAndView rt = new ModelAndView("accueilback");
        rt.addObject("valiny",this.chansonService.listChansonsPage(page,parpage));
        //rt.addObject("listeChansons",this.chansonService.listChansonsPage(0, 10).getResultats());
        rt.addObject("lien","accueil");
        return rt;
    }

    public boolean testLogged(HttpServletRequest req){
       return req.getSession().getAttribute("adminObjet")!=null;
    }
    
    @RequestMapping("syncstat")
    public ModelAndView statutDeLaSynchro(){
        ModelAndView md= new ModelAndView("syncstat");
        return md;
    }
    
    @RequestMapping(value = "ajouterChanson", method = RequestMethod.POST)
    public String saveChanson(@RequestParam CommonsMultipartFile file) throws Exception {

        String path = context.getRealPath("");
        String filename = file.getOriginalFilename();

        System.out.println(path + this.chansonService.getUploadDir() + " " + filename);

        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                new File(this.chansonService.getUploadDir() + File.separator + filename)));
        stream.write(bytes);
        stream.flush();
        stream.close();
        Chanson ct=this.chansonService.fromFile(filename);
        return "redirect:/admin/modifier/"+ct.getId();
    }
    
    @RequestMapping("/supprimer/{id}")
    public String supprimerChanson(@PathVariable("id") int id, HttpServletRequest req){
         if(!testLogged(req) ) return "redirect:/admin/login?e=2";
        this.chansonService.deleteChanson(id);
        return "redirect:../accueil";
    }
    
     @RequestMapping(value="/modifier/{id}", method=RequestMethod.GET)
     public ModelAndView modifierUneChanson(@PathVariable("id") int id, @RequestParam(value="s",required=false) String succ, HttpServletRequest req){
         if(!testLogged(req) ) return new ModelAndView("redirect:/admin/login?e=2");
         ModelAndView md = new ModelAndView("modifier");
         md.addObject("chanson",this.chansonService.findChansonById(id));
         md.addObject("action","../modifier");
         md.addObject("succ",succ);
         return md;
     }
     
     @RequestMapping(value="/modifier", method=RequestMethod.POST)
     public String modif(@ModelAttribute("chanson") Chanson ray, HttpServletRequest req){
         if(!testLogged(req) ) return "redirect:/admin/login?e=2";
                 System.out.println(" vomodif "+ray.getId());
         this.chansonService.updateChanson(ray);

         return "redirect:modifier/"+ray.getId()+"?s=1";
     }
    @RequestMapping("/beginSynchro")    
     public @ResponseBody String beginSynchro(){
         System.out.println(" Mega actualisation de la base");
         Syncer();
         return "{succes}";
     }
    @Async
    public void Syncer(){
        if(sync_status==null || sync_status.isEnCours()==false){
             sync_status=new actualisationStatut();
         }
         this.chansonService.initialiserBF(sync_status);
    }
     @RequestMapping("/statutSynchro")
     public @ResponseBody actualisationStatut getStatutSynchro(){
        if(sync_status!=null) return sync_status;
        actualisationStatut ast=new actualisationStatut();
        ast.getLastMessage();
        ast.setLastMessage("Pas de synchronisation en cours");
        ast.setEnCours(false);
        return ast;
     }
    
    @RequestMapping("/")
    public String redirAccueil() {
        return "redirect:/admin/login";
    }

    @RequestMapping(value="/login")
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
