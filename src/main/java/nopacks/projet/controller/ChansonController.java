package nopacks.projet.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.mp3.mp3Finder;
import nopacks.projet.services.ChansonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

@Controller
public class ChansonController {

    private ChansonService chansonService;
    @Autowired
    ServletContext context;

    //String UPLOAD_DIRECTORY="/upl";
    @Autowired(required = true)
    @Qualifier(value = "chansonService")
    public void setChansonService(ChansonService ps) {
        this.chansonService = ps;
    }

    @RequestMapping(value = "/chansons", method = RequestMethod.GET) //ny ray amin ireto no mande selon anle spring
    public String listChansons(Model model) {
        model.addAttribute("listChansons", this.chansonService.listChansons());
        return "index";
    }

    @RequestMapping(value = "/fichiers", method = RequestMethod.GET)
    public ModelAndView listChansonsDossier() {
        ModelAndView model = new ModelAndView("index2");
        List<String> valiny = this.chansonService.findAllMp3InFolder();
//        System.out.println(valiny.size());
        model.addObject("liste", valiny);
        model.addObject("path", this.chansonService.getUploadDir());
        return model;
    }

    @RequestMapping(value = "/chansons2", method = RequestMethod.GET)
    public ModelAndView listChansons() {
        ModelAndView model = new ModelAndView("index");

        model.addObject("listChansons", this.chansonService.listChansons());
        model.addObject("hira", new Chanson());
//model.addAttribute("listChansons", this.chansonService.listChansons());
        return model;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editChanson(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView("index");
        model.addObject("hira", this.chansonService.findChansonById(id));
        model.addObject("listChansons", this.chansonService.listChansons());
        return model;
    }

    @RequestMapping("/testObjet")
    public ModelAndView creerObjetForm(){
        ModelAndView md=new ModelAndView("testcobj");
        return md;
    }
    
    @RequestMapping(value="/cObjet", method = RequestMethod.POST)
    public ModelAndView creerObjet(HttpServletRequest req, HttpServletResponse res){
        ModelAndView md=new ModelAndView("testcobj");
        Chanson valiny;
        try{
            valiny=(Chanson)getObject(req,"nopacks.projet.modeles.Chanson");
        } catch(Exception e){
            e.printStackTrace();
            valiny=null;
        }
        System.out.println(valiny.getNomfichier());
        md.addObject("resultat",valiny);
        return md;
    }
    
    @RequestMapping(value = "download/{nom}", method = RequestMethod.GET)

    public void downloadTest(HttpServletRequest request, HttpServletResponse response, @PathVariable("nom") String nom) throws IOException {

        // get absolute path of the application
        // ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath = appPath + "/" + nom;
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

        this.chansonService.deleteChanson(id);
        return "redirect:/chansons2";
    }

    @RequestMapping("/findall/{page}/{parpage}")
    public ModelAndView findAllPage(@PathVariable("page") int page, @PathVariable("parpage") int parpage) {
        if (parpage <= 0) {
            parpage = 10;
        }
        if (page < 0) {
            page = 0;
        }
        ModelAndView md = new ModelAndView("testpagin");
        md.addObject("resultat", this.chansonService.listChansonsPage(page, parpage));
        return md;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addChanson(@ModelAttribute("hira") Chanson p) {

        if (p.getId() == 0) {
            this.chansonService.addChanson(p);
        } else {

            this.chansonService.updateChanson(p);
        }

        return "redirect:/chansons2";

    }

    @RequestMapping("uploadform")   //itestena anle upload
    public ModelAndView uploadForm() {
        return new ModelAndView("uploadform");
    }

    @RequestMapping(value = "savefile", method = RequestMethod.POST)
    public ModelAndView saveimage(@RequestParam CommonsMultipartFile file) throws Exception {

        String path = context.getRealPath("");
        String filename = file.getOriginalFilename();

        System.out.println(path + this.chansonService.getUploadDir() + " " + filename);

        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                new File(path + this.chansonService.getUploadDir() + File.separator + filename)));
        stream.write(bytes);
        stream.flush();
        stream.close();

        return new ModelAndView("uploadform", "filesuccess", "File successfully saved!");
    }

    private Method getSetter(Object cible, String nom) throws Exception{
        String charac = nom.substring(0, 1).toUpperCase();
        String realattrib = charac + nom.substring(1);
        Method[] liste=cible.getClass().getDeclaredMethods();
        for(Method ray:liste){
            if(ray.getName().equals("set"+realattrib)) return ray;
        }
        throw new NoSuchMethodException("set"+realattrib);
    }
    
    private void callSetter(Object cible, String attribut, String valeur) throws Exception {

        Method antsoina = getSetter(cible,attribut);
        Class[] cl = antsoina.getParameterTypes();
        if (cl.length > 1) {
            throw new Exception("methode setter erronee pour la classe " + cible.getClass() + " attribut: " + attribut);
        }
        Class typeArgument = cl[0];
        if (typeArgument == String.class) {
            antsoina.invoke(cible, valeur);
        } else if (typeArgument == Integer.class) {
            antsoina.invoke(cible, Integer.parseInt(valeur));
        } else if (typeArgument == Float.class) {
            antsoina.invoke(cible, Float.parseFloat(valeur));
        } else if (typeArgument == Double.class) {
            antsoina.invoke(cible, Double.parseDouble(valeur));
        } else if (typeArgument == Long.class) {
            antsoina.invoke(cible, Long.parseLong(valeur));
        }
    }

    public Object getObject(HttpServletRequest req, Object izy) throws Exception {
        Object rt = null;
        if (izy.getClass() == String.class) {
            rt = loadClassByName((String) izy);
        } else {
            rt = loadClassByInstance(izy);
        }
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = req.getParameterValues(paramName);
            if (paramValues.length > 1) {
                throw new Exception("trop de valeurs pour un attribut");
            }
            String param = paramValues[0];
            callSetter(rt, paramName, param);
            System.out.println(paramName + " set to " + param);
        }
        return rt;
    }

    private Object loadClassByName(String nom) throws Exception {
        return loadClassByClass(Class.forName(nom));
    }

    private Object loadClassByInstance(Object efaloade) throws Exception {
        return loadClassByClass(efaloade.getClass());

    }

    private Object loadClassByClass(Class classe) throws Exception {
        try {
            Constructor constructor = classe.getConstructor();
            Object rt = constructor.newInstance();
            return rt;
        } catch (Exception e) {
            e.printStackTrace();
            //return null; //to do
            throw e;
        }
    }
}
