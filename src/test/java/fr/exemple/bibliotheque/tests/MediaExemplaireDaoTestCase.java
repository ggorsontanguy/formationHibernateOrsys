/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque.tests;

import fr.exemple.bibliotheque.EtatExemplaire;
import fr.exemple.bibliotheque.Exemplaire;
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
public class MediaExemplaireDaoTestCase {
    
    private static Logger logger = Logger.getLogger(MediaExemplaireDaoTestCase.class);
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static MediaDao mediaDao;
    private static long identifiant;
   
    public MediaExemplaireDaoTestCase() {
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
        Exemplaire e1 = new Exemplaire(1, EtatExemplaire.Perdu);
        Exemplaire e2 = new Exemplaire(2, EtatExemplaire.Disponible);
        //associer les exemplaires au media
        m1.addExemplaire(e1);
        m1.addExemplaire(e2);
        em.getTransaction().begin();
        mediaDao.ajouter(m1);
        em.getTransaction().commit();
        logger.info("Identifiant : "+ m1.getId() );
        int nombreMediaApres = mediaDao.recupererMedias(0, 10).size();
        assertEquals(1 , nombreMediaApres);
       // em.close();
    }
     @Test
    public void recupererParId() {
       em = emf.createEntityManager();
       mediaDao = new MediaDaoImpl(em);
       Media m1 = mediaDao.recupererParId(1);
        //lister les exemplaires
       // Media m1Clone = new Media(m1.getId(), m1.getTitre(), m1.getAuteur());
        //m1Clone.setExemplaires(m1.getExemplaires());
        //m1.addExemplaire(new Exemplaire(3,EtatExemplaire.Disponible));
       logger.info("---------------------------"); 
        m1.getExemplaires().size();
        logger.info("---------------------------");
        for(Exemplaire e : m1.getExemplaires()){
            logger.info("Exemplaire  Id : "+e.getId()+ " etat :"+e.getEtat());
        }
    }
    
    
   
}