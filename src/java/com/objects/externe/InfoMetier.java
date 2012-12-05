/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objects.externe;

/**
 *
 * @author Benoît
 */
public class InfoMetier {
    private int ID;
    private InfoType Type;
    private String Nom;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public InfoType getType() {
        return Type;
    }

    public void setType(InfoType Type) {
        this.Type = Type;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }
}
