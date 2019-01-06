/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO.Cacher;

/**
 *
 * @author ASUS
 */
public class Stockage {
    private Object rt;
    private long date;
    public Stockage(Object vao){

        System.out.println("stocke :"+vao.toString());
        this.rt=vao;
        this.date=System.currentTimeMillis();
    }
    public Object getValue(){
                System.out.println("nalaina :"+rt.toString());
        return rt;
    }
    public boolean estExpire(String expiration){
        long exp=Long.parseLong(expiration);
        if(exp==0) return false;
        return System.currentTimeMillis()> date+exp;
    }
}
