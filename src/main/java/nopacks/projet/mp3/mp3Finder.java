/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.mp3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class mp3Finder {

    public List<String> findAllInFolder(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        List<String> liste=new ArrayList<String>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String nf=listOfFiles[i].getName();
                //System.out.println(nf);
                if(nf.endsWith(".mp3")) liste.add(nf);
                nf=null; //gb col idina (miasa refa be lotra le ira feno le ram )
            } else if (listOfFiles[i].isDirectory()) {
                //System.out.println("Directory " + listOfFiles[i].getName());
                //skip!
            }
        }
        
        folder=null; //gb collector idina
        return liste;
    }
}
