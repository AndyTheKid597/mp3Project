/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO.Cacher;

import java.util.HashMap;


/**
 *
 * @author ASUS
 */
public class Cacher {
private HashMap<String,HashMap<String,String>> config;
private HashMap<String,HashMap<String,Stockage>> memory;

public void setConfig(HashMap<String,HashMap<String,String>> config){
    this.config=config;
}
public HashMap<String,HashMap<String,String>> getcf(){
    return config;
}
    public Cacher(){
        memory=new HashMap<>();
    }
    public void add(Object izy, Object valeur){
        try{
       String nom=izy.getClass().getName();
       System.out.println("add to cache "+nom);
       if(config.containsKey(nom)){
           initMemory(nom);
           HashMap<String,Stockage> tp=memory.get(nom);
           tp.put(getCle(izy),new Stockage(valeur));
           System.out.println("Tafiditra");
       } else {
           System.out.println("Tsy ilaina");
       }
        } catch(Exception ex){
            System.out.println("Cacher exception");
            System.out.println(ex.getMessage());
            
        }
    }
    
    public Object get(Object izy) {
                try{
        String nom=izy.getClass().getName();
                System.out.println("trying get from cache "+nom);
        if(!config.containsKey(nom)) return null;
        System.out.println("misy anaty config");
        if(!memory.containsKey(nom)) return null;
                System.out.println("misy anaty memoire le classe");
           HashMap<String,Stockage> tp=memory.get(nom);
           String cle=getCle(izy);
           if(!tp.containsKey(cle)) return null;
                   System.out.println("misy anaty memoire le condition");
           Stockage sg=tp.get(cle);
           if(sg.estExpire(config.get(nom).get("duree"))){
                       System.out.println("cache expire");
                tp.remove(cle);
                return null;
           }
           Object rt=sg.getValue();
                   System.out.println("azo avy any anaty cache");
                   System.out.println(rt.toString());
           return rt;
                   } catch(Exception ex){
            System.out.println("Cacher exception tatou");
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    private String getCle(Object izy) throws Exception{
        String nomMethode=config.get(izy.getClass().getName()).get("nom");
        return izy.getClass().getMethod(nomMethode).invoke(izy).toString();
    }
    
    private void initMemory(String nom){
        if(!memory.containsKey(nom)){
            memory.put(nom, new HashMap<String,Stockage>());
        }
    }
}
