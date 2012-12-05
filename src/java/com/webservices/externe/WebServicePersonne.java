/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservices.externe;

import com.jpa.TEmploi;
import com.jpa.TPersonne;
import com.objects.externe.InfoEmploi;
import com.objects.externe.InfoPersonne;
import com.objects.externe.InfoType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateParse = new SimpleDateFormat("dd/MM/yyyy");
        String squery = "SELECT t FROM TPersonne t";        
        String where = "";
        List<Object[]> params = new ArrayList<Object[]>();
        if(!ip.getNom().equals(""))
        {
            if(!where.equals(""))
            {
                where += " AND";
            }
            where += " LOWER(t.nom) = LOWER(:nom)";            
            params.add(new Object[]{"nom", ip.getNom()});
        }
        if(!ip.getPrenom().equals(""))
        {
            if(!where.equals(""))
            {
                where += " AND";
            }
            where += " LOWER(t.prenom) = LOWER(:prenom)";
            params.add(new Object[]{"prenom", ip.getPrenom()});
        }
        if(!ip.getDateNaissance().equals(""))
        {
            //where += " t.dateDeNaissance = {d':dateDeNaissance'}";            
            try {
                //params.add(new Object[]{"dateDeNaissance", dateFormat.format(dateParse.parse(ip.getDateNaissance()))});
                String date = dateFormat.format(dateParse.parse(ip.getDateNaissance()));
                if(!where.equals(""))
                {
                    where += " AND";
                }
                where += " t.dateDeNaissance = '" + date + "'";
            } catch (ParseException ex) {
                Logger.getLogger(WebServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ip.isSexe())
        {
            if(!where.equals(""))
            {
                where += " AND";
            }
            where += " t.sexe = :sexe";
            params.add(new Object[]{"sexe", '1'});
        }
        else
        {
            if(!where.equals(""))
            {
                where += " AND";
            }
            where += " t.sexe = :sexe";
            params.add(new Object[]{"sexe", '0'});
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
            ip.setEmplois(new ArrayList<InfoEmploi>());
            for (TEmploi e : p.getTEmploiList())
            {                            
                ip.getEmplois().add(new InfoEmploi(e.getIdMetier(), e.getIntituleMetier(), new InfoType(e.getIdTypeEmploi().getIdTypeEmploi(), e.getIdTypeEmploi().getTypeEmploi()), dateFormat.format(e.getDtDebut()), dateFormat.format(e.getDtFin())));
            }
            ListIP.add(ip);
        }
        return ListIP;
    }
}
