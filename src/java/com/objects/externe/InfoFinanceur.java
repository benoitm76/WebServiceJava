/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objects.externe;

/**
 *
 * @author Benoît
 */
public class InfoFinanceur {
    private int ID;
    private String Nom;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public InfoFinanceur() {
    }

    public InfoFinanceur(int ID, String Nom) {
        this.ID = ID;
        this.Nom = Nom;
    }
}
