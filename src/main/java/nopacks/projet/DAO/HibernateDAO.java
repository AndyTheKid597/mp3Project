/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import nopacks.projet.DAO.Cacher.Cacher;
import nopacks.projet.DAO.criteres.CritereGenerator;
import nopacks.projet.DAO.criteres.Requete;
import nopacks.projet.modeles.BaseModele;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.ResultatPagination;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

/**
 *
 * @author ASUS
 */
public class HibernateDAO implements InterfaceDAO {

    private SessionFactory sessionFactory;
    private Transaction tx;
    private Session tempsession;
    private Cacher cacher;

    public void setCacher(Cacher cacher) {
        this.cacher = cacher;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(BaseModele p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BaseModele> findAll(BaseModele p) {
        Session session = this.sessionFactory.openSession();
        //////System.out.println(p.getClass());
        //////System.out.println(p.getClass().getSuperclass());
        List<BaseModele> liste = session.createCriteria(p.getClass()).list();
        session.close();
        //////System.out.println("taille " + liste.size());
        return liste;
    }

    @Override
    public BaseModele findById(BaseModele p) {
        Session session = this.sessionFactory.openSession();
        BaseModele rt = (BaseModele) session.get(p.getClass(), p.getId());
        session.close();
        return rt;
    }

    @Override
    public ResultatPagination findAllPage(BaseModele p, int page, int parpage) {
        ResultatPagination rt = new ResultatPagination();
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(p.getClass());
                Criteria criteriaCount = session.createCriteria(p.getClass());
        criteriaCount.setProjection(Projections.rowCount());
        Long count = (Long) criteriaCount.uniqueResult();
        rt.setTailleTotale(count);
        if(parpage==-1) parpage=count.intValue();
        criteria.setFirstResult(page * parpage);
        criteria.setMaxResults(parpage);
        List<BaseModele> respage = criteria.list();
        rt.setResultats(respage);
        rt.setNumPage(page);
        rt.setParPage(parpage);

        session.close();
        return rt;
    }

    @Override
    public void update(BaseModele p) {
        //  Session session=this.sessionFactory.getCurrentSession();
        //session.update(p);
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //////System.out.println(p.getId());
        session.update(p);
        tx.commit();
        session.close();

    }

    @Override
    public void delete(BaseModele p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //////System.out.println(p.getId());
        BaseModele anatybase = (BaseModele) session.load(p.getClass(), p.getId());

        if (anatybase != null) {
            session.delete(anatybase);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void beginTransaction() { //not yet supproted ito zavatra ito ngamba ra ny tena marina
        tempsession = sessionFactory.openSession();
        tx = tempsession.beginTransaction();
    }

    @Override
    public void endTransaction() {
        tx.commit();
    }

    public void tester() {
        try {
            //////System.out.println("begin 22");
            Chanson testchan = new Chanson();
            Requete rq = new Requete(testchan);
            rq.setCritere(CritereGenerator.or(CritereGenerator.like("nomfichier", "a"), CritereGenerator.gteq("id", new Integer(5))));
            //////System.out.println(rq.contenu());
            //////System.out.println(rq.where());
            ResultatPagination liste2 = this.findAllPage(rq, 0, 10);
            //////System.out.println("tonga farany22");
            for (BaseModele bm : liste2.getResultats()) {
                Chanson tpchan = ((Chanson) bm);
                //////System.out.println(tpchan.getId() + " " + tpchan.getNomfichier());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //////System.out.println(ex.getMessage());
        }
    }

    @Override
    public ResultatPagination findAllPage(Requete rq, int page, int parpage) {
        ResultatPagination rt = new ResultatPagination();
        Session session = this.sessionFactory.openSession();
        String nt = rq.getBm().getClass().getSimpleName();
        String whcon = rq.where();
        String where = " where ";
        String od= rq.orderby();
        if (whcon == null) {
            where = "";
        } else {
            where = where + whcon;
        }
        ArrayList<Object> conds=null;
        Query qrct = session.createQuery("select count(*) from " + nt + where);
        if (whcon != null) {
             conds = rq.mifanaraka();
            int t = conds.size();

            for (int i = 0; i < t; i++) {
                //////System.out.println(conds.get(i));
                qrct.setParameter(i, conds.get(i));
            }
        }
        long count = ((Long) qrct.uniqueResult()).longValue();
        if(parpage==-1) parpage=(int)count;
        Query qr = session.createQuery(" from " + nt + where+ od);
        if (conds != null) {
            int t = conds.size();
            for (int i = 0; i < t; i++) {
                qr.setParameter(i, conds.get(i));
            }
        }
        qr.setMaxResults(parpage);
        qr.setFirstResult(page * parpage);
        List<BaseModele> list = qr.list();
        rt.setResultats(list);
        rt.setNumPage(page);
        rt.setParPage(parpage);
        rt.setTailleTotale(count);
        session.close();
        return rt;
    }

    @Override
    public BaseModele findBy(Requete rq) { //retourne unique
        Object rttest = this.cacher.get(rq);
        if (rttest != null) {
            return (BaseModele) rttest;
        }
        //////System.out.println("skip code return");
        Session session = this.sessionFactory.openSession();
        String nt = rq.getBm().getClass().getSimpleName();
        String whcon = rq.where();
        String where = " where ";
                String od= rq.orderby();
        if (whcon == null) {
            where = "";
        } else {
            where = where + whcon;
        }
        Query qrct = session.createQuery(" from " + nt + where+ od);

        if (whcon != null) {
            ArrayList<Object> conds = rq.mifanaraka();
            int t = conds.size();

            for (int i = 0; i < t; i++) {
                qrct.setParameter(i, conds.get(i));
            }
        }
        List<BaseModele> res = qrct.list();
        if (res.size() < 1) {
            return null;
        }

        BaseModele rt = res.get(0);
        this.cacher.add(rq, rt);
        session.close();
        return rt;
    }

    @Override
    public void deleteAll(BaseModele p) {
        sessionFactory.getCurrentSession().createQuery("delete from " + p.getClass().getSimpleName()).executeUpdate();
    }

    @Override
    public int maxID(BaseModele p) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Query qrct = session.createQuery("select max(id) from " + p.getClass().getSimpleName());
            int rt = ((Integer) qrct.uniqueResult()).intValue();
            session.close();
            return rt;
        } catch (Exception ex) {
            ex.printStackTrace();
            //////System.out.println(ex.getMessage());
            if (session != null) {
                session.close();
            }
            return -1;
        }

    }

}
