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
    public void save(BaseModele p);
    public List<BaseModele> findAll();
    //mbola banga be fa vo amboarina io 
}
