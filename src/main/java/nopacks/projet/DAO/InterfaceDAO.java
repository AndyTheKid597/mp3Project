/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO;

import java.util.List;
import nopacks.projet.DAO.criteres.Requete;
import nopacks.projet.modeles.BaseModele;
import nopacks.projet.modeles.ResultatPagination;

/**
 *
 * @author ASUS
 */
public interface InterfaceDAO {
    public void beginTransaction();
    public void endTransaction();
    public void save(BaseModele p);
    public BaseModele findById(BaseModele p);
    public ResultatPagination findAllPage(BaseModele p,int page,int parpage); //list =>resultats
    public ResultatPagination findAllPage(Requete rq,int page,int parpage); //list =>resultats
    public void update(BaseModele p);
    public void delete(BaseModele p);
    public List<BaseModele> findAll(BaseModele p);
    public BaseModele findBy(Requete rq); //resultat unique
    public void deleteAll(BaseModele p);
}
