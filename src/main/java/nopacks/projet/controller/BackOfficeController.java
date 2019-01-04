/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.controller;

import javax.servlet.ServletContext;
import nopacks.projet.services.ChansonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/admin")
public class BackOfficeController {
     private ChansonService chansonService;
    @Autowired
    ServletContext context;

    @Autowired(required = true)
    @Qualifier(value = "chansonService")
    public void setChansonService(ChansonService ps) {
        this.chansonService = ps;
    }
    
    @RequestMapping("/")
    public ModelAndView loginAccueil(){
        ModelAndView rt=new ModelAndView("login");
        return rt;
    }
}
