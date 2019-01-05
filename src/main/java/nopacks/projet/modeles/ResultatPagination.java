/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.modeles;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class ResultatPagination {
    
    public List<BaseModele> getResultats() {
        return resultats;
    }

    public boolean hasPrevious(){
        if(resultats==null) return false;
        return numPage>0;
    }
    public boolean hasNext(){
        if(resultats==null) return false;
        return (numPage+1)*parPage<tailleTotale;
    }
    public int getPreviousp(){
                if(resultats==null) return -1;
        return numPage-1;
    }
    public int getNextp(){
                if(resultats==null) return -1;
        return numPage+1;
    }
    
    public void setResultats(List<BaseModele> resultats) {
        this.resultats = resultats;
    }

    public long getTailleTotale() {
        return tailleTotale;
    }

    public void setTailleTotale(long tailleTotale) {
        this.tailleTotale = tailleTotale;
        hasNext=hasNext();
        hasPrevious=hasPrevious();
        hasPrevious=hasPrevious();
        previous=getPreviousp();
        next=getNextp();
    }

    public int getParPage() {
        return parPage;
    }

    public void setParPage(int parPage) {
        this.parPage = parPage;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }
    
    private List<BaseModele> resultats;
    private long tailleTotale;
    private int parPage,numPage;
    private boolean hasPrevious,hasNext;
    private int previous,next;

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
