package nopacks.projet.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Autowired
        ServletContext context;

    @Autowired(required = true)
    @Qualifier(value = "chansonService")
    public void setChansonService(ChansonService ps) {
        this.personService = ps;
    }

    @RequestMapping(value = "/chansons", method = RequestMethod.GET) //ny ray amin ireto no mande selon anle spring
    public String listChansons(Model model) {
        model.addAttribute("listChansons", this.personService.listChansons());
        return "index";
    }

    @RequestMapping(value = "/chansons2", method = RequestMethod.GET)
    public ModelAndView listChansons() {
        ModelAndView model = new ModelAndView("index");

        model.addObject("listChansons", this.personService.listChansons());
        model.addObject("hira", new Chanson());
//model.addAttribute("listChansons", this.personService.listChansons());
        return model;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editChanson(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView("index");
        model.addObject("hira", this.personService.findChansonById(id));
        model.addObject("listChansons", this.personService.listChansons());
        return model;
    }

    @RequestMapping(value = "download/{nom}", method = RequestMethod.GET)

    public void downloadTest(HttpServletRequest request, HttpServletResponse response, @PathVariable("nom") String nom) throws IOException {

        // get absolute path of the application
       // ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);
 
        // construct the complete absolute path of the file
        String fullPath = appPath +"/"+ nom;      
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[512];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
    }

    @RequestMapping("/delete/{id}")
    public String deleteChanson(@PathVariable("id") int id) {

        this.personService.deleteChanson(id);
        return "redirect:/chansons2";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addChanson(@ModelAttribute("hira") Chanson p) {

        if (p.getId() == 0) {
            this.personService.addChanson(p);
        } else {

            this.personService.updateChanson(p);
        }

        return "redirect:/chansons2";

    }

}
