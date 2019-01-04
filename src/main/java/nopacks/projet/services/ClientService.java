/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.services;

import nopacks.projet.modeles.Client;

/**
 *
 * @author ASUS
 */
public interface ClientService {
    public Client testLogin(Client c);
    public Client inscrireClient(Client c);
    public Client updateClient(Client c);
}
