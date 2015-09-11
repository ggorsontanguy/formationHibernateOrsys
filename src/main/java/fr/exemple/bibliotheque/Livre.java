/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author houahidi
 */
@Entity
@Table(name="t_livre")
@DiscriminatorValue("livre")
public class Livre extends Media implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1366542537890126306L;
	@Column(unique= true, length=12, nullable= false)
    private String isbn;
    private int  pages;

    public Livre() {
        super();
    }

    public Livre(long id ,String titre, String auteur,String isbn, int pages) {
        super(id, titre, auteur);
        this.isbn = isbn;
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
 
    @Override
    public String toString() {
        return "fr.exemple.bibliotheque.Livre[ id=" + getId() + " ]";
    }
    
}
