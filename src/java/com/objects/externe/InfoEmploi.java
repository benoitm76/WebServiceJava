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
    private String DateDebut;
    private String DateFin;    

    public String getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(String DateDebut) {
        this.DateDebut = DateDebut;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setDateFin(String DateFin) {
        this.DateFin = DateFin;
    }

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

    public InfoEmploi() {
    }
    
    public InfoEmploi(int ID, String Nom_emploi, InfoType Type, String DateDebut, String DateFin) {
        this.ID = ID;
        this.Nom_emploi = Nom_emploi;
        this.Type = Type;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }
}
