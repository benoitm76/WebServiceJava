/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objects.externe;

/**
 *
 * @author Benoît
 */
public class InfoEmploi {
    private int ID;
    private String Nom_emploi;  
    private InfoType Type;      
    private InfoPersonne Personne;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom_emploi() {
        return Nom_emploi;
    }

    public void setNom_emploi(String Nom_emploi) {
        this.Nom_emploi = Nom_emploi;
    }
    
    public InfoType getType() {
        return Type;
    }

    public void setType(InfoType Type) {
        this.Type = Type;
    }

    public InfoPersonne getPersonne() {
        return Personne;
    }

    public void setPersonne(InfoPersonne Personne) {
        this.Personne = Personne;
    }
    
}
