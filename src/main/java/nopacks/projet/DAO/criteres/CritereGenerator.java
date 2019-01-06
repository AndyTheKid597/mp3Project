/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO.criteres;

public class CritereGenerator {

    public static And and(Critere c1, Critere c2) throws Exception{
        return new And().setCritere(c1,c2);
    }
    public static Or or(Critere c1, Critere c2) throws Exception{
        return new Or().setCritere(c1,c2);
    }
    public static Order asc(String nom){
        return new Order(nom,"asc");
    }
        public static Order desc(String nom){
        return new Order(nom,"desc");
    }
    public static Egal eq(String nom, Object valeur) throws Exception{
        return (Egal)new Egal().setCritere(nom,valeur);
    }
    public static Egal eq(String nom, int valeur) throws Exception{
        return (Egal)new Egal().setCritere(nom,new Integer(valeur));
    }
    public static Like like(String nom, Object valeur) throws Exception{
        return (Like)new Like().setCritere(nom,valeur);
    }
    public static Different neq(String nom, Object valeur) throws Exception{
        return (Different)new Different().setCritere(nom,valeur);
    }
        public static Different neq(String nom, int valeur) throws Exception{
        return (Different) new Different().setCritere(nom,new Integer(valeur));
    }
    public static Inferieur lt(String nom, Object valeur) throws Exception{
        return (Inferieur)new Inferieur().setCritere(nom,valeur);
    }
    public static Superieur gt(String nom, Object valeur) throws Exception{
        return (Superieur)new Superieur().setCritere(nom,valeur);
    }
    public static InferieurOuEgal lteq(String nom, Object valeur) throws Exception{
        return (InferieurOuEgal)new InferieurOuEgal().setCritere(nom,valeur);
    }
    public static SuperieurOuEgal gteq(String nom, Object valeur) throws Exception{
        return (SuperieurOuEgal)new SuperieurOuEgal().setCritere(nom,valeur);
    }
    
    
    public static Inferieur lt(String nom, int valeur) throws Exception{
        return (Inferieur)new Inferieur().setCritere(nom,new Integer(valeur));
    }
    public static Superieur gt(String nom, int valeur) throws Exception{
        return (Superieur)new Superieur().setCritere(nom,new Integer(valeur));
    }
    public static InferieurOuEgal lteq(String nom, int valeur) throws Exception{
        return (InferieurOuEgal)new InferieurOuEgal().setCritere(nom,new Integer(valeur));
    }
    public static SuperieurOuEgal gteq(String nom, int valeur) throws Exception{
        return (SuperieurOuEgal)new SuperieurOuEgal().setCritere(nom,new Integer(valeur));
    }
    
}
