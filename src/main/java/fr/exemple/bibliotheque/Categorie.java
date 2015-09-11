/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author houahidi
 */
@Entity
@Table(name="t_categorie")
public class Categorie {
    @Id
    @Column(name="identifiant", length=6)
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "categorie_sequence" )
//    @SequenceGenerator(name="categorie_sequence",sequenceName ="categorie_sequence" ,allocationSize = 1)
    private long id;
    
    @Column(name="libelle",unique = true,length = 100)
    private String libelle;
    
    @ManyToMany(mappedBy = "categories") 
    private List<Media> medias;

    
    public Categorie() {
        medias = new ArrayList<Media>();
    }

    public Categorie(long id, String libelle) {
        this();
        this.id = id;
        this.libelle = libelle;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setMedias(List<Media> medias) {
    	
    	// supprimer les anciens médias
        for(Media oldM : this.medias){
            removeMedia(oldM);
        }
        
        // ajouter les nouveaux médias
         for(Media newM : medias){
            addMedia(newM);
        }
    }

    public List<Media> getMedias() {
        return medias;
    }
    
    
     public void addMedia(Media m) {
    	// Loi de Demeter + mapping bi-directionnel
        if(!medias.contains(m)){
            medias.add(m);
            m.addCategorie(this);
        }
    }
      public void removeMedia(Media m) {
    	// Loi de Demeter + mapping bi-directionnel
        if(medias.contains(m)){
            medias.remove(m);
            m.removeCategorie(this);
        }
    }
}
