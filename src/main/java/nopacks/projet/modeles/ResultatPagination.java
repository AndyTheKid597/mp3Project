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

    public void setResultats(List<BaseModele> resultats) {
        this.resultats = resultats;
    }

    public long getTailleTotale() {
        return tailleTotale;
    }

    public void setTailleTotale(long tailleTotale) {
        this.tailleTotale = tailleTotale;
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
}
