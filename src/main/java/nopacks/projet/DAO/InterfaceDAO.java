/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO;

import java.util.List;
import nopacks.projet.modeles.BaseModele;

/**
 *
 * @author ASUS
 */
public interface InterfaceDAO {
    public void beginTransaction();
    public void endTransaction();
    public void save(BaseModele p);
    public BaseModele findById(BaseModele p);
    public List<BaseModele> findAllPage(BaseModele p,int page,int parpage);
    public void update(BaseModele p);
    public void delete(BaseModele p);
    public List<BaseModele> findAll(BaseModele p);
    //mbola banga be fa vo amboarina io 
}
