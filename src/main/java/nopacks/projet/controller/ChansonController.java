package nopacks.projet.controller;


import nopacks.projet.modeles.Chanson;
import nopacks.projet.services.ChansonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ChansonController {
	
	private ChansonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="chansonService")
	public void setChansonService(ChansonService ps){
		this.personService = ps;
	}
	
	@RequestMapping(value = "/chansons", method = RequestMethod.GET)
	public String listChansons(Model model) {
		model.addAttribute("listChansons", this.personService.listChansons());
		return "indexs";
	}
        @RequestMapping(value = "/chansons2", method = RequestMethod.GET)
	public ModelAndView listChansons() {
                ModelAndView model=new ModelAndView("index");
                model.addObject("listChansons", this.personService.listChansons());
//		model.addAttribute("listChansons", this.personService.listChansons());
		return model;
	}
	
	
}