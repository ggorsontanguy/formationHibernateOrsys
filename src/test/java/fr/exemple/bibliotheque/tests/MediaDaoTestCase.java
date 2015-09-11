/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque.tests;

import fr.exemple.bibliotheque.Media;
import fr.exemple.bibliotheque.dao.MediaDao;
import fr.exemple.bibliotheque.dao.jpa.MediaDaoImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author houahidi
 */
public class MediaDaoTestCase {
    
    private static Logger logger = Logger.getLogger(MediaDaoTestCase.class);
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static MediaDao mediaDao;
    private static long identifiant;
   
    public MediaDaoTestCase() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        logger.info("Initialisation des tests");
        emf = Persistence.createEntityManagerFactory("uni_library_db");
        em = emf.createEntityManager();
        mediaDao = new MediaDaoImpl(em);
    }
    
    @AfterClass
    public static void tearDownClass() {
        logger.info("Libération des ressources");
        mediaDao = null;
        em.close(); em = null;
        emf.close(); emf = null;
    }
    @Test
    public void ajouter() {
        Media m1 = new Media(0, "UML","AGROSUP");
        Media m2 = new Media(0, "XML","AGROSUP");
        //int nombreMediaAvant = mediaDao.recupererMedias(0, 10).size();
        em.getTransaction().begin();
        mediaDao.ajouter(m1);
        mediaDao.ajouter(m2);
        em.getTransaction().commit();
        identifiant = m1.getId();
        logger.info("Identifiant : "+identifiant );
        int nombreMediaApres = mediaDao.recupererMedias(0, 10).size();
        assertEquals(2 , nombreMediaApres);
       
    }
    
    @Test
     public void recupererListe1() {
         logger.info("recupererListe1---------------------------------------");
         List<Media> medias = mediaDao.recupererMedias(0, 10);
         for(Media m : medias){
             logger.info("id : " +m.getId() + "  titre: "+ m.getTitre());
         }
     }
   
    
    @Test
     public void recupererListe2() {
        logger.info("recupererListe2---------------------------------------debut");
        em.close();
        em = emf.createEntityManager();
        mediaDao = new MediaDaoImpl(em);
        List<Media> medias = mediaDao.recupererMedias(0, 10);
        for(Media m : medias){
             logger.info("id : " +m.getId() + "  titre: "+ m.getTitre());
        }
        logger.info("recupererListe2---------------------------------------fin");
     }
    
    
     @Test
     public void modifier() {
        Media m = mediaDao.recupererParId(identifiant);
        m.setAuteur("New auteur");
        em.getTransaction().begin();
        mediaDao.modifer(m);
        em.getTransaction().commit();
        Media mUpdate = mediaDao.recupererParId(identifiant);
        assertEquals(m.getTitre() , mUpdate.getTitre());
    }
     @Test
    public void supprimer() {
        em.getTransaction().begin();
        mediaDao.supprimer(1);
        mediaDao.supprimer(2);
        em.getTransaction().commit();
        int nombreMediaApres = mediaDao.recupererMedias(0, 10).size();
        assertEquals(0 , nombreMediaApres);
    }
}
