/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.mp3;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 *
 * @author Andriamanday
 */
public class mp3Util {

    String nomfichier;
    private String repertoire;
    List<Byte> vao;
    List<Byte> vao2;
    AudioFileFormat baseFileFormat;
    private File file;
    private AudioInputStream in,din;
    private AudioFormat baseFormat,decodedFormat;
    public String getRepertoire() {
        return repertoire;
    }

    public void setRepertoire(String repertoire) {
        this.repertoire = repertoire;
    }

    public void processFile(String nomdefichier) throws Exception {
        System.out.println("tafiditra ato");
        nomfichier = repertoire+nomdefichier;
        vao = null; //size 200
        vao2 = null; //size 200
        baseFileFormat = null;
        din=null;
        in=null;
        file=null;
        System.out.println("trying to open file");
         file = new File(nomfichier);
         System.out.println("fichier ouvert");
         int sleepTime = 1000; // Sleep 1 second

//         in = AudioSystem.getAudioInputStream(file);
         din = null;
//         System.out.println("inputstream azo");
 //        baseFormat = in.getFormat();
  //       decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
   //             baseFormat.getSampleRate(),
    //            16,
     //           baseFormat.getChannels(),
      //          baseFormat.getChannels() * 2,
       //         baseFormat.getSampleRate(),
        //        false);
        // System.out.println("format azo");
  //      din = AudioSystem.getAudioInputStream(decodedFormat, in);
//System.out.println("azo input stream anle audio format");
// Play now. 
     //   rawplay(decodedFormat, din);

  //      in.close();
  boolean opened=false;
  System.out.println("opening");
  while(!opened){
      try{
                  baseFileFormat = AudioSystem.getAudioFileFormat(file);
                  opened=true;
                  System.out.println("opened ");
      } catch (Exception ex){
          System.out.println(ex.getMessage());
    TimeUnit.SECONDS.sleep(1);
      }
}


    }

    private String getAttribut(String nom) { //attribut Sting only
        Map properties = ((TAudioFileFormat) baseFileFormat).properties();
        String key = nom;
        String val = (String) properties.get(key);
        //System.out.println("auteur "+val);
        return val;
    }

    public String getAuteur() {
        return getAttribut("author");
    }

    public String getTitre() {
        return getAttribut("title");
    }

    public String getAlbum() {
        return getAttribut("album");
    }

    public String getDate() {
        return getAttribut("date");
    }

    public String getCopyright() {
        return getAttribut("copyright");
    }

    public long getDuration() {
        Map properties = ((TAudioFileFormat) baseFileFormat).properties();
        return (long) properties.get("duration");

    }

    public String getCommentaire() {
        return getAttribut("comment");
    }

    public String getGenre() {
        return getAttribut("mp3.id3tag.genre");
    }

    public String getTrack() {
        return getAttribut("mp3.id3tag.track");
    }

    private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException {
        System.out.println("tonga eto am rawplay");
       // List<Byte> tableau = new ArrayList<Byte>(100000000);
       ByteArrayOutputStream bos=new ByteArrayOutputStream(); 
       byte[] data = new byte[4096];
        int nBytesRead = 0, nBytesWritten = 0, ttlp = 0, nbtr=0;
        System.out.println("begin read");
        while (nBytesRead != -1) {
            
            int ttl = 0;
            nBytesRead = din.read(data);
            if (nBytesRead != -1) {
                  nbtr+=nBytesRead;
                ttlp++;
            }

            //for (int i = 0; i < 4096; i++) {
                //tableau.add((byte)Math.abs(data[i]));
                //tableau.add((byte) (data[i]));
                bos.write(data, 0, 4096);
           // }
            //System.out.println("vita "+ nbtr);

        }
        byte[] tableau=bos.toByteArray();
        bos.close();
        System.out.println("fin read");
        // System.out.println("total "+tableau.size());
        int part = tableau.length/ 200;
        int max = 0;
        int min = 1000;
        vao = new ArrayList<Byte>();
        vao2 = new ArrayList<Byte>();
        int t = tableau.length;
        for (int i = 0; i < 200; i++) {
            int ttl = 0;
            int j = 0;
            for (j = 0; j < part && j + (i * part) < t; j++) {

                byte tp = tableau[j + (i * part)];
                if (tp < 0) {
                    tp = 0;
                }
                ttl += tp;
                //    ttl+=tableau.get(j+(i*part));
            }
            ttl = ttl / (j + 1);
            if (ttl < min) {
                min = ttl;
            }
            if (ttl > max) {
                max = ttl;
            }
            vao.add((byte) ttl);
            // System.out.println(vao.get(i));
        }
        for (int i = 0; i < 200; i++) {
            int ttl = 0;
            int j = 0;
            for (j = 0; j < part && j + (i * part) < t; j++) {

                byte tp = tableau[j + (i * part)];
                if (tp > 0) {
                    tp = 0;
                }
                ttl += tp;
                //    ttl+=tableau.get(j+(i*part));
            }
            ttl = ttl / (j + 1);
            if (ttl < min) {
                min = ttl;
            }
            if (ttl > max) {
                max = ttl;
            }
            vao2.add((byte) ttl);
            // System.out.println(vao.get(i));
        }
        // System.out.println("range "+min+" "+max);
        // ArrayList<Byte> izy=map(vao,max,min);
        // int tt=izy.size();
        // System.out.println("sortie");
        //for(int i=0;i<tt;i++){
        //    System.out.println(izy.get(i));
        //}
        // Stop
        //line.drain();
        //line.stop();
        //line.close();
        din.close();

    }

    public List<Byte> getMD() {
        return vao;
    }
    
    public String getMDString(){
        StringBuilder rt= new StringBuilder();
        boolean vol=false;
        rt.append(" [ ");
        
        if(vao!=null)
        for(Byte ray : vao){
            if(vol){
                rt.append(" , ");
            }
            rt.append(ray.toString());
            vol=true;
        }
        rt.append(" ] ");
        return rt.toString();
    }

    public String getMD2String(){
        StringBuilder rt= new StringBuilder();
        boolean vol=false;
        rt.append(" [ ");
        if(vao2!=null)
        for(Byte ray : vao2){
            if(vol){
                rt.append(" , ");
            }
            rt.append(ray.toString());
            vol=true;
        }
        rt.append(" ] ");
        return rt.toString();
    }
    
    public List<Byte> getMD2() {
        return vao2;
    }
}
