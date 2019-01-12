/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import nopacks.projet.modeles.Chanson;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 *
 * @author ASUS
 */

public class WatcherService extends Thread {
    private WatchService watcherService;
    private Path path;
    private ChansonService chansonService;
    String chemin ;
    public void setChansonService(ChansonService vao){
        this.chansonService=vao;
    }
    public WatcherService(){
        this("E:/mp3/");
    }
    public WatcherService(String chemin){
        try {
            this.chemin=chemin;
            watcherService
                    = FileSystems.getDefault().newWatchService();
            
            Path path = Paths.get(this.chemin);
            
            path.register(
                    watcherService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE);
            
          
        } catch (Exception ex) {
            Logger.getLogger(WatcherService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Scheduled(fixedDelay = 1000)
    public void actual(){
        try {
            WatchKey key;
            System.out.println("Niantso test fixhier");
            while ((key = watcherService.take()) != null) {
                int i=0;
                System.out.println(this);
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println(i+
                            ". Event kind:" + event.kind()
                                    + ". File affected: " + event.context() + ".");
                    if(event.context().toString().endsWith(".mp3")){
                    if(event.kind()==StandardWatchEventKinds.ENTRY_DELETE){
                        chansonService.deleteChanson(event.context().toString());
                    } else if(event.kind()==StandardWatchEventKinds.ENTRY_CREATE){
                        Chanson ct=chansonService.addChanson(event.context().toString());
                        chansonService.pend(ct);
                        ct=null;
                    }
                    }
                    
                    i++;
                    
                }
                key.reset();
            }
            actual();
        } catch (Exception ex) {
            Logger.getLogger(WatcherService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
