/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.exemple.bibliotheque.EtatExemplaire;
import fr.exemple.bibliotheque.Exemplaire;
import fr.exemple.bibliotheque.Livre;
import fr.exemple.bibliotheque.Media;

/**
 *
 * @author houahidi
 */
public class MediasTests {
    
    public static void main(String[] args) {
        
        //Création d'un media
        Media m1 = new Livre(0, "Hibernate1", "Orsys","989-909-90",292);
        //Création des exemplaires
        Exemplaire e1 =new Exemplaire(0, "TROIS",EtatExemplaire.Disponible);
        Exemplaire e2 =new Exemplaire(0, "QUATRE", EtatExemplaire.Disponible);
        //Associer les exemplaires au media
        m1.addExemplaire(e1);
        m1.addExemplaire(e2);
        //Sauvegarder le media et les exemplaires en cascade
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uni_library_db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(m1);
        em.getTransaction().commit();
        //Associer un exemplaire supplementaire au media
        Exemplaire e3 =new Exemplaire(5, "CINQ",EtatExemplaire.Disponible);
        Media mExistant = em.getReference(Media.class, m1.getId());
        e3.setMedia(mExistant);
        em.getTransaction().begin();
        em.persist(e3);
        em.getTransaction().commit(); 
        //suppression en cascade
        em.getTransaction().begin();
        em.remove(m1);
        em.getTransaction().commit();
        
        
        
        
    }
    
}
