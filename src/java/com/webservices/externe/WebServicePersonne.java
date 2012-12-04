/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservices.externe;

import com.jpa.TPersonne;
import com.objects.externe.InfoPersonne;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Benoit
 */
@WebService(serviceName = "WebServicePersonne")
@Stateless()
public class WebServicePersonne {
    
    @PersistenceContext(unitName = "WebServiceJavaPU")
    private EntityManager em;
    
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
    
    @WebMethod(operationName = "GetAllPersonnes")
    public List<InfoPersonne> GetAllPersonnes()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<InfoPersonne> ListIP = new ArrayList<InfoPersonne>();
        Query query = em.createNamedQuery("TPersonne.findAll");
        for (TPersonne p : (List<TPersonne>)query.getResultList())
        {
            InfoPersonne ip = new InfoPersonne();
            ip.setNom(p.getNom());
            ip.setPrenom(p.getPrenom());
            if(p.getDateDeNaissance() != null) {
                ip.setDateNaissance(dateFormat.format(p.getDateDeNaissance()));
            }
            if(p.getSexe() == '0') {
                ip.setSexe(false);
            }
            else {
                ip.setSexe(true);
            }            
            ListIP.add(ip);
        }
        return ListIP;
    }
}
