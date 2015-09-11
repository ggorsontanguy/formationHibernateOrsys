/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.exemple.bibliotheque.dao;

import fr.exemple.bibliotheque.Media;
import java.util.List;

/**
 *
 * @author houahidi
 */
public interface MediaDao {
    
    void ajouter(Media m);
    void modifer(Media m);
    void supprimer(long identifiant);
    Media recupererParId(long identifiant);
    List<Media> recupererMedias(int debut, int nombre);
}
