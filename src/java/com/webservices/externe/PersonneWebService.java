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
 * @author Benoît
 */
@WebService(serviceName = "PersonneWebService")
@Stateless()
public class PersonneWebService {
    
    @PersistenceContext(unitName = "WebServiceJavaPU")
    private EntityManager em;

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
        
        /*
         * 
         * Recherche Par Nom
         * 
         */
        
        if(!ip.getNom().equals(""))
        {
            if(!where.equals(""))
            {
                where += " AND";
            }
            where += " LOWER(t.nom) LIKE :nom";            
            params.add(new Object[]{"nom", "%" + ip.getNom().toLowerCase() + "%"});
        }
        
        /*
         * 
         * Recherche Par Prénom
         * 
         */
        
        if(!ip.getPrenom().equals(""))
        {
            if(!where.equals(""))
            {
                where += " AND";
            }
            where += " LOWER(t.prenom) LIKE :prenom";
            params.add(new Object[]{"prenom", "%" + ip.getPrenom().toLowerCase() + "%"});
        }
        
        /*
         * 
         * Recherche Par Date de Naissance
         * 
         */
        
        if(!ip.getDateNaissance().equals(""))
        {            
            try {
                String date = dateFormat.format(dateParse.parse(ip.getDateNaissance()));
                if(!where.equals(""))
                {
                    where += " AND";
                }
                where += " t.dateDeNaissance = '" + date + "'";
            } catch (ParseException ex) {
                Logger.getLogger(PersonneWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /*
         * 
         * Recherche Par Sexe
         * 
         */
        
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
    
    @WebMethod(exclude = true)
    public List<InfoPersonne> RemplirInfoPersonne(List<TPersonne> list)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<InfoPersonne> ListIP = new ArrayList<InfoPersonne>();
        for (TPersonne p : list)
        {
            InfoPersonne ip = new InfoPersonne();
            ip.setID(p.getIdPersonne());
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
