/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

/**
 *
 * @author houahidi
 */
@Entity
@Table(name="t_exemplaire")
public class Exemplaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="identifiant")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NaturalId
    @Column(length=20)
    private String reference;
    
    @Enumerated(EnumType.STRING)
    private EtatExemplaire etat;
    
    @ManyToOne
    @JoinColumn(name="media_id")
    private Media media;
    
    public Exemplaire() {
    	this.etat = EtatExemplaire.Disponible;
    }

    public Exemplaire(long id, String reference, EtatExemplaire etat) {
        this.id = id;
        this.reference =reference;
        this.etat = etat;
    }
    
    public Exemplaire(long id, String reference, EtatExemplaire etat, Media media) {
        this(id,reference, etat);
        setMedia(media);
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EtatExemplaire getEtat() {
        return etat;
    }

    public void setEtat(EtatExemplaire etat) {
        this.etat = etat;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        
        if(this.media != null && this.media != media)
            this.media.removeExemplaire(this);
        
        this.media = media;
                
        if(media != null)
            media.addExemplaire(this);
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exemplaire)) {
            return false;
        }
        Exemplaire other = (Exemplaire) object;
        if (this.id != other.id) {
            return false;
        }
        if (!this.reference.equals(other.reference)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.exemple.bibliotheque.Exemplaire[ id=" + id + " ]";
    }
    
}
