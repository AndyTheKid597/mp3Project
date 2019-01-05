/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import nopacks.projet.DAO.HibernateDAO;
import nopacks.projet.DAO.InterfaceDAO;
import nopacks.projet.DAO.criteres.CritereGenerator;
import nopacks.projet.DAO.criteres.Requete;
import nopacks.projet.modeles.BaseModele;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.Config;
import nopacks.projet.modeles.ResultatPagination;
import nopacks.projet.modeles.actualisationStatut;
import nopacks.projet.mp3.mp3Finder;
import nopacks.projet.mp3.mp3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ChansonServiceImpl implements ChansonService {

    private InterfaceDAO chansonDAO;
     private   String UPLOAD_DIRECTORY="E:/mp3/";
     private mp3Finder finder;

     public void setFinder(mp3Finder finder){
         this.finder=finder;
     }
     
    public void setChansonDAO(InterfaceDAO chansonDAO) {
        this.chansonDAO = chansonDAO;
    }
    
    @Override
    @Transactional
    public void addChanson(Chanson p) {
        this.chansonDAO.beginTransaction();
        this.chansonDAO.save(p);
        this.chansonDAO.endTransaction();
    }

    @Override
    public List<Chanson> listChansons() {
        Chanson ct=new Chanson();
        return (List<Chanson>)(Object) this.chansonDAO.findAll(ct);
    }

    @Override
    public Chanson findChansonById(int id) {
        Chanson ct=new Chanson();
        ct.setId(id);
        return (Chanson) this.chansonDAO.findById(ct);
    }

    @Override
    @Transactional
    public void updateChanson(Chanson p) {
        this.chansonDAO.update(p);
    }    

    @Override
    public void deleteChanson(int id) {
        Chanson hira=new Chanson();
        hira.setId(id);
        this.chansonDAO.delete(hira);
    }

    @Override
    public ResultatPagination listChansonsPage(int page, int parpage) {
       return this.chansonDAO.findAllPage(new Chanson(), page, parpage);
    }

    @Override
    public void setUploadDir(String upd) {
           UPLOAD_DIRECTORY=upd;
    }

    @Override
    public String getUploadDir() {
            return UPLOAD_DIRECTORY;
    }

    @Override
    public List<String> findAllMp3InFolder() {
     List<String> rt= this.finder.findAllInFolder(UPLOAD_DIRECTORY);
     //System.out.println(rt.size());
     return rt;
    }
    

    public void tester(){
        System.out.println("intiialisation");
        //initialiser();
        System.out.println("initialisation terminee");
    }

    
    @Override
    public void initialiserBF(actualisationStatut sync_stat){
        try {
            Config cfg=new Config();
            Requete rq=new Requete(cfg);
            Requete rq2=new Requete(cfg);
            boolean mitovy=false;
            rq.setCritere(CritereGenerator.eq("cle", "last_hash"));
            rq2.setCritere(CritereGenerator.eq("cle", "last_date"));
            Config cfgvao=(Config)this.chansonDAO.findBy(rq);
            Config cfgvao2=(Config)this.chansonDAO.findBy(rq2);
            List<String> liste=this.finder.findAllInFolder(UPLOAD_DIRECTORY);
            sync_stat.setTotal(liste.size());
            String hash=this.finder.hash(liste);
            System.out.println("ita doly n tanaty dossier)");
            if(cfgvao==null){ //non existant
                            System.out.println("begin save config)");
                cfgvao=new Config();
                cfgvao.setCle("last_hash");
                cfgvao.setValeur(hash);
                this.chansonDAO.save(cfgvao);
                            System.out.println("end save config");
            } else{
                mitovy=cfgvao.getValeur().equals(hash);
            }
            if(cfgvao2==null){ //non existant
                            System.out.println("new config last date");
                cfgvao=new Config();
                cfgvao.setCle("last_date");

                cfgvao.setValeur( new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())));
                sync_stat.setLastMessage("Initialisation: "+cfgvao.getValeur());
                this.chansonDAO.save(cfgvao);
                            System.out.println("end new config last date");
            }
            if(mitovy){
                System.out.println("synchro skip");
            }
            if(!mitovy){
                            System.out.println("begin synchro");
                List<BaseModele> listeChansons=this.chansonDAO.findAll(new Chanson());
                ArrayList<String> listeNFBase=new ArrayList<String>(listeChansons.size());
                ArrayList<Integer> listeID=new ArrayList<Integer>(listeChansons.size());
                ArrayList<String> listeFichiersCopie=new ArrayList<String>(liste);
                for(BaseModele ray : listeChansons){
                    listeNFBase.add(((Chanson)ray).getNomfichier());
                    listeID.add(new Integer(ray.getId()));
                }
                int i_stat=0;
                for(String ray: listeNFBase){
                    sync_stat.setLastMessage("Verification de : "+ray);
                    System.out.println("fichier : " +ray);
                    int ind=listeFichiersCopie.indexOf(ray);
                    System.out.println(ind);
                    if(ind!=-1){
                        System.out.println("remove from list fichier"+ray);
                        listeFichiersCopie.remove(ray);
                        sync_stat.setLastMessage("Fichier existant, fichier suivant");
                    } else {
                        System.out.println("niditra ato "+ind);
                        System.out.println("taille "+listeID.size());
                        Chanson ch=new Chanson();

                      Integer val= listeID.get(listeNFBase.indexOf(ray));
                                              System.out.println("ave eto ");
                      ch.setId(val.intValue());
                                  System.out.println("delete "+val);
                      this.chansonDAO.delete(ch);
                                      sync_stat.setLastMessage(" Suppression du fichier dans la base");
                      ch=null;
                    }
                }
                            System.out.println("fin delete");
                mp3Util m_util=new mp3Util();
                m_util.setRepertoire(UPLOAD_DIRECTORY);
                            System.out.println("begin insert");
                            sync_stat.setLastMessage("debut de l'insertion");
                for(String ray: listeFichiersCopie){
                    sync_stat.setLastMessage("lecture de "+ray);
                    System.out.println(UPLOAD_DIRECTORY+ray);
                    m_util.processFile(ray);
                    System.out.println("vo process");
                    Chanson ch= new Chanson();
                    ch.setNomfichier(ray);
                    ch.setD_down(m_util.getMD2String());
                    ch.setD_up(m_util.getMDString());
                    ch.setAlbum(m_util.getAlbum());
                    ch.setAuteur(m_util.getAuteur());
                    ch.setCommentaire(m_util.getCommentaire());
                    ch.setDuration(m_util.getDuration());
                    ch.setDate(m_util.getDate()); //annee fotsiny
                    ch.setGenre(m_util.getGenre());
                    ch.setTitre(m_util.getTitre());
                    ch.setTrack(m_util.getTrack());
                                System.out.println("insert "+ch.getNomfichier());
                                    sync_stat.setLastMessage(" insertion..");
                                this.chansonDAO.save(ch);
                                    sync_stat.setLastMessage(" insertion terminee, suivant");
                    ch=null;
                    System.out.println("fin insert ray");
                    sync_stat.setTermine(i_stat);
                    i_stat++;
                }

                            System.out.println("fin insert");
            }
        } catch (Exception ex) {
           System.out.println(" EXCEPETION :" +ex.getMessage());
        }
                    finally{
                            sync_stat.setEnCours(false);
        }
    }

    @Override
    public Config getLastDate() {
        try {
            Requete rq=new Requete(new Config());
            rq.setCritere(CritereGenerator.eq("cle", "last_sync"));
            return (Config)this.chansonDAO.findBy(rq);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
