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
        Query query = em.createNamedQuery("TPersonne.findAll");        
        return RemplirInfoPersonne(query.getResultList());
    }
    
    @WebMethod(operationName = "RecherchePersonnes")
    public List<InfoPersonne> RecherchePersonnes(InfoPersonne ip, boolean externe)
    {
        List<InfoPersonne> ListIP = new ArrayList<InfoPersonne>();        
        String squery = "SELECT t FROM TPersonne t";        
        String where = "";
        List<Object[]> params = new ArrayList<Object[]>();
        if(!ip.getNom().equals(""))
        {
            if(!where.equals(""))
            {
                where += " AND";
            }
            where += " t.nom = :nom";            
            params.add(new Object[]{"nom", ip.getNom()});
        }
        if(!ip.getPrenom().equals(""))
        {
            if(!where.equals(""))
            {
                where += " AND";
            }
            where += " t.prenom = :prenom";
            params.add(new Object[]{"prenom", ip.getPrenom()});
        }
        if(!where.equals(""))
        {
            squery += " WHERE" + where;
        }
        Query query = em.createQuery(squery);
        for(Object[] o : params)
        {
            query.setParameter((String)o[0], o[1]);
        }
        return RemplirInfoPersonne(query.getResultList());
    }
    
    public List<InfoPersonne> RemplirInfoPersonne(List<TPersonne> list)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<InfoPersonne> ListIP = new ArrayList<InfoPersonne>();
        for (TPersonne p : list)
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
