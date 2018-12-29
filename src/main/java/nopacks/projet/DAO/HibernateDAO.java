/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nopacks.projet.DAO;

import java.util.List;
import nopacks.projet.modeles.BaseModele;
import nopacks.projet.modeles.Chanson;
import nopacks.projet.modeles.ResultatPagination;
import org.hibernate.Criteria;
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
        System.out.println(p.getClass());
        System.out.println(p.getClass().getSuperclass());
        List<BaseModele> liste = session.createCriteria(p.getClass()).list();
        session.close();
        System.out.println("taille " + liste.size());
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
        criteria.setFirstResult(page * parpage);
        criteria.setMaxResults(parpage);
        List<BaseModele> respage = criteria.list();
        rt.setResultats(respage);
        rt.setNumPage(page);
        rt.setParPage(parpage);
        Criteria criteriaCount = session.createCriteria(p.getClass());
        criteriaCount.setProjection(Projections.rowCount());
        Long count = (Long) criteriaCount.uniqueResult();
        rt.setTailleTotale(count);
        session.close();
        return rt;
    }

    @Override
    public void update(BaseModele p) {
        //  Session session=this.sessionFactory.getCurrentSession();
        //session.update(p);
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(p);
        tx.commit();
        session.close();

    }

    @Override
    public void delete(BaseModele p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
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

}
