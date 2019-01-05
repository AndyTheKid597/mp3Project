/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.modeles;

/**
 *
 * @author ASUS
 */
public class actualisationStatut {
    private boolean enCours;
    private String lastMessage;
    private int total;
    private int termine;

    public actualisationStatut(){
        enCours=true;
        lastMessage="Debut de la synchronisation";
        total=0;
        termine=0;
    }
    
    public boolean isEnCours() {
        return enCours;
    }

    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

    public String getLastMessage() {
        String rt=lastMessage;
        lastMessage="";
        return rt;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = this.lastMessage+" ; "+lastMessage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTermine() {
        return termine;
    }

    public void setTermine(int termine) {
        this.termine = termine;
    }
    
}
