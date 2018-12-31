/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO;

import java.sql.Connection;
import javax.annotation.PostConstruct;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author ASUS
 */
public class Connexion {

    private BasicDataSource dataSource;

    public void setDataSource(BasicDataSource ds) {
        this.dataSource = ds;
    }

    public Connection getConnex() throws Exception {
        return dataSource.getConnection();
    }
}
