/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import fr.exemple.bibliotheque.Media;
import fr.exemple.bibliotheque.dao.MediaDao;

/**
 *
 * @author houahidi
 */
public class MediaDaoImpl implements  MediaDao{
    private EntityManager em;
    private static Logger logger = Logger.getLogger(MediaDaoImpl.class);
    
    public MediaDaoImpl(EntityManager em) {
        this.em = em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }
    
    @Override
    public void ajouter(Media m) {
        em.persist(m);
    }

    @Override
    public void modifer(Media m) {
         em.merge(m);
    }
    @Override
    public void supprimer(long identifiant) {
      Media m = recupererParId(identifiant);
      em.remove(m);
    }

    @Override
    public Media recupererParId(long identifiant) {
       return  em.find(Media.class, identifiant);
    }

    @Override
    public List<Media> recupererMedias(int debut, int nombre) {
        Query query = em.createQuery("select m from Media m");
        query.setHint("org.hibernate.cacheable", "true");
        query.setFirstResult(debut);
        query.setMaxResults(nombre);
        
        Session hSession = (Session) em.getDelegate() ;
        SessionFactory sessionFactory = hSession.getSessionFactory();
         //sessionFactory.getCache().evictEntityRegion("media");
        //sessionFactory.getCache().evictDefaultQueryRegion();
        Statistics statistics = sessionFactory.getStatistics();
        logger.info("____________Statistiques " + statistics.getEntityLoadCount());
        logger.info("____________Statistiques " + statistics.getEntityFetchCount());
        
        return query.getResultList();
    }
    
}
