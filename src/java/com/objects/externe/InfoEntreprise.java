/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objects.externe;

/**
 *
 * @author Benoît
 */
public class InfoEntreprise {
    private int ID;
    private String nom;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public InfoEntreprise() {
    }

    public InfoEntreprise(int ID, String nom) {
        this.ID = ID;
        this.nom = nom;
    }
    
}
