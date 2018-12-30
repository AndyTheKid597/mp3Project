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
import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 *
 * @author Andriamanday
 */
public class mp3Util {

    String nomfichier;
    List<Byte> vao;
    List<Byte> vao2;
    AudioFileFormat baseFileFormat;

    public void processFile(String nomdefichier) throws Exception {
        nomfichier = nomdefichier;
        vao = null; //size 200
        vao2 = null; //size 200
        baseFileFormat = null;
        File file = new File(nomfichier);
        AudioInputStream in = AudioSystem.getAudioInputStream(file);
        AudioInputStream din = null;
        AudioFormat baseFormat = in.getFormat();
        AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(),
                false);
        din = AudioSystem.getAudioInputStream(decodedFormat, in);
        // Play now. 
        rawplay(decodedFormat, din);

        in.close();
        baseFileFormat = AudioSystem.getAudioFileFormat(file);

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
        List<Byte> tableau = new ArrayList<Byte>();
        byte[] data = new byte[4096];

        int nBytesRead = 0, nBytesWritten = 0, ttlp = 0;
        while (nBytesRead != -1) {
            int ttl = 0;

            nBytesRead = din.read(data);
            if (nBytesRead != -1) {

                ttlp++;
            }

            for (int i = 0; i < 4096; i++) {
                //tableau.add((byte)Math.abs(data[i]));
                tableau.add((byte) (data[i]));
            }

        }
        // System.out.println("total "+tableau.size());
        int part = tableau.size() / 200;
        int max = 0;
        int min = 1000;
        vao = new ArrayList<Byte>();
        vao2 = new ArrayList<Byte>();
        int t = tableau.size();
        for (int i = 0; i < 200; i++) {
            int ttl = 0;
            int j = 0;
            for (j = 0; j < part && j + (i * part) < t; j++) {

                byte tp = tableau.get(j + (i * part));
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

                byte tp = tableau.get(j + (i * part));
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

    public List<Byte> getMD2() {
        return vao2;
    }
}
