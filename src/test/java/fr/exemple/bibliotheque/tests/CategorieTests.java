/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.exemple.bibliotheque.Categorie;

/**
 *
 * @author houahidi
 */
public class CategorieTests {
    
    public static void main(String[] args) {
          
        
        //Gestion de cycle de vie
        //Cas 1 :  creation d'une nouvelle categorie
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uni_library_db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Categorie c1 = new Categorie(0, "Arts");
        em.persist(c1);
        //Categorie c2 = new Categorie(0, "Arts");
        //em.persist(c2);
        //Recupération d'un élément
        //c1 = em.find(Categorie.class, 1l);
        System.out.println("id:"+c1.getId() +"libelle"+c1.getLibelle());
        //c2 = em.find(Categorie.class, 2l);
        //System.out.println("id:"+c2.getId() +"libelle"+c2.getLibelle()); 
        //mise à jour automatique d'un objet attaché
        //c1.setLibelle("new Musique");
        //Usage de remove
        //em.remove(c1);
        //em.remove(c2);
        em.getTransaction().commit();
    }
    
}
