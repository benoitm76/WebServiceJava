/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservices.externe;

import com.objects.externe.InfoPersonne;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Benoit
 */
@WebService(serviceName = "WebServicePersonne")
@Stateless()
public class WebServicePersonne {
    
    @WebMethod(operationName = "test")
    public InfoPersonne test(String nom, String prenom, String dateNaissance) {
        InfoPersonne ip = new InfoPersonne();
        ip.setNom(nom);
        ip.setPrenom(prenom);
        ip.setDateNaissance(dateNaissance);
        return ip;
    }
    
    @WebMethod(operationName = "hello")
    public String hello() {
        return "Cool";
    }
    
    @WebMethod(operationName = "RecherchePersonne")
    public InfoPersonne[] RecherchePersonne(String nom, String prenom, boolean sexe, String dateNaissance)
    {
        InfoPersonne[] ip = new InfoPersonne[0];
        return ip;
    }
    
    @WebMethod(operationName = "GetPersonne")
    public InfoPersonne GetPersonne(int id)
    {
        InfoPersonne ip = new InfoPersonne();
        return ip;
    }
}
