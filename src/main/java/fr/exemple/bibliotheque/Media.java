/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

/**
 *
 * @author houahidi
 */

@Entity
@Table(name="t_media")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminant", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("media")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Media {
    @Id
    @Column(name="identifiant")
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "media_sequence")
//    @SequenceGenerator(name = "media_sequence",sequenceName ="media_sequence",allocationSize = 1 )
    private long id;
    
    @Column(unique = true, length=100)
    @NaturalId
    private String titre;
    
    @Column(length=100)
    private String auteur;
    
    @OneToMany(mappedBy = "media",cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Exemplaire> exemplaires;
    
    @ManyToMany
    @JoinTable(name="r_media_categorie",
               joinColumns = @JoinColumn(name="media_id"),
               inverseJoinColumns = @JoinColumn(name="categorie_id")
              )
    
    private List<Categorie> categories;

    public Media() {
        exemplaires = new ArrayList<Exemplaire>();
        categories = new ArrayList<Categorie>();
    }

    public Media(long id, String titre, String auteur) {
        this();
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        //this.exemplaires = exemplaires;
    	// supprimer les anciens
        for(Exemplaire oldE : this.exemplaires){
            removeExemplaire(oldE);
        }
     // ajouter les nouveaux
        for(Exemplaire newE : exemplaires){
            addExemplaire(newE);
        }
    }
    
    public void addExemplaire(Exemplaire e) {
        if(!exemplaires.contains(e)){
            exemplaires.add(e);
            e.setMedia(this);
        }
    }
     public void removeExemplaire(Exemplaire e) {
        if(exemplaires.contains(e)){
            exemplaires.remove(e);
            e.setMedia(null);
        } 
    }
    
     public void addCategorie(Categorie c) {
        if(!categories.contains(c)){
            categories.add(c);
            c.addMedia(this);
        }
    }
     
    public void removeCategorie(Categorie c) {
        if(categories.contains(c)){
            categories.remove(c);
            c.removeMedia(this);
        }
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
         for(Categorie oldC : this.categories){
            removeCategorie(oldC);
        }
         for(Categorie newC : categories){
            addCategorie(newC);
        }
    }
    
    
}
