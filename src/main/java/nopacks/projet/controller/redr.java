/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class redr {
        @RequestMapping("/")
    public String rdr(){
        return "redirect:/site/index.html";
    }
      @RequestMapping("/index.html")
    public String rdr2(){
        return "redirect:/site/index.html";
    }
}
