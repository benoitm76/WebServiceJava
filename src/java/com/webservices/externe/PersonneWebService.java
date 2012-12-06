/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservices.externe;

import com.jpa.TEmploi;
import com.jpa.TFinanceur;
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
import javax.xml.ws.WebServiceRef;
import org.datacontract.schemas._2004._07.objetexterne.ArrayOfInfoPersonne;
import org.datacontract.schemas._2004._07.objetexterne.ObjectFactory;
import org.tempuri.WebServiceNet;

/**
 *
 * @author Benoît
 */
@WebService(serviceName = "PersonneWebService")
@Stateless()
public class PersonneWebService {
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/172.16.255.146_55111/WebServiceNet.svc.singlewsdl.wsdl")
    private WebServiceNet service;

    @PersistenceContext(unitName = "WebServiceJavaPU")
    private EntityManager em;

    @WebMethod(operationName = "hello")
    public String hello() {
        return "Cool";
    }

    @WebMethod(operationName = "RecherchePersonne")
    public InfoPersonne[] RecherchePersonne(String nom, String prenom, boolean sexe, String dateNaissance) {
        InfoPersonne[] ip = new InfoPersonne[0];
        return ip;
    }

    @WebMethod(operationName = "GetPersonne")
    public InfoPersonne GetPersonne(int id) {
        InfoPersonne ip = new InfoPersonne();
        return ip;
    }
    
    @WebMethod(operationName = "addPersonne")
    public void addPersonne(InfoPersonne ip) {
        DateFormat dateParse = new SimpleDateFormat("dd/MM/yyyy");
        TPersonne p = new TPersonne();
        Query query = em.createNamedQuery("TPersonne.findAll");
        List<TPersonne> lp = query.getResultList();
        p.setIdPersonne(lp.get(lp.size() - 1).getIdPersonne() + 1);
        p.setNom(ip.getNom());
        p.setPrenom(ip.getPrenom());
        if(ip.isSexe())
        {
            p.setSexe('1');
        }
        else
        {
            p.setSexe('0');
        }
        try{
            p.setDateDeNaissance(dateParse.parse(ip.getDateNaissance()));
        }
        catch(Exception ex)
        {
            
        }   
        p.setIdFinanceur(new TFinanceur(1));
        em.persist(p);
    }

    @WebMethod(operationName = "GetAllPersonnes")
    public List<InfoPersonne> GetAllPersonnes() {
        Query query = em.createNamedQuery("TPersonne.findAll");
        return RemplirInfoPersonne(query.getResultList());
    }

    @WebMethod(operationName = "RechercheExtraPersonnes")
    public List<InfoPersonne> RechercheExtraPersonnes(InfoPersonne ip) {
        org.tempuri.IWebServiceNet port = service.getBasicHttpBindingIWebServiceNet();
        ArrayOfInfoPersonne clip = port.recherchePersonnes(convertJavaPersonneToCSharp(ip));
        List<InfoPersonne> ljip = new ArrayList<InfoPersonne>();
        for(org.datacontract.schemas._2004._07.objetexterne.InfoPersonne cip : clip.getInfoPersonne())
        {
            ljip.add(convertCSharpToJavaPersonne(cip));
        }        
        System.out.println("/*** Le serveur Java va rechercher les données dans le serveur C# ***/");
        return ljip;
    }

    @WebMethod(operationName = "RecherchePersonnes")
    public List<InfoPersonne> RecherchePersonnes(InfoPersonne ip, boolean externe) {
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

        if (ip.getNom() != null && !ip.getNom().equals("")) {
            if (!where.equals("")) {
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

        if (ip.getPrenom() != null && !ip.getPrenom().equals("")) {
            if (!where.equals("")) {
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

        if (ip.getDateNaissance() != null && !ip.getDateNaissance().equals("")) {
            try {
                String date = dateFormat.format(dateParse.parse(ip.getDateNaissance()));
                if (!where.equals("")) {
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

        if (ip.isSexe()) {
            if (!where.equals("")) {
                where += " AND";
            }
            where += " t.sexe = :sexe";
            params.add(new Object[]{"sexe", '1'});
        } else {
            if (!where.equals("")) {
                where += " AND";
            }
            where += " t.sexe = :sexe";
            params.add(new Object[]{"sexe", '0'});
        }

        if (!where.equals("")) {
            squery += " WHERE" + where;
        }
        Query query = em.createQuery(squery);
        for (Object[] o : params) {
            query.setParameter((String) o[0], o[1]);
        }
        System.out.println("/*** Le serveur Java va rechercher les données sur la BDD Oracle ***/");
        return RemplirInfoPersonne(query.getResultList());
    }

    @WebMethod(exclude = true)
    public List<InfoPersonne> RemplirInfoPersonne(List<TPersonne> list) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<InfoPersonne> ListIP = new ArrayList<InfoPersonne>();
        for (TPersonne p : list) {
            InfoPersonne ip = new InfoPersonne();
            ip.setID(p.getIdPersonne());
            ip.setNom(p.getNom());
            ip.setPrenom(p.getPrenom());
            if (p.getDateDeNaissance() != null) {
                ip.setDateNaissance(dateFormat.format(p.getDateDeNaissance()));
            }
            if (p.getSexe() == '0') {
                ip.setSexe(false);
            } else {
                ip.setSexe(true);
            }
            ip.setEmplois(new ArrayList<InfoEmploi>());
            for (TEmploi e : p.getTEmploiList()) {
                ip.getEmplois().add(new InfoEmploi(e.getIdMetier(), e.getIntituleMetier(), new InfoType(e.getIdTypeEmploi().getIdTypeEmploi(), e.getIdTypeEmploi().getTypeEmploi()), dateFormat.format(e.getDtDebut()), dateFormat.format(e.getDtFin())));
            }
            ListIP.add(ip);
        }
        return ListIP;
    }

    @WebMethod(exclude = true)
    public InfoPersonne convertCSharpToJavaPersonne(org.datacontract.schemas._2004._07.objetexterne.InfoPersonne cip) {
        InfoPersonne ip = new InfoPersonne();
        ip.setNom(cip.getNom().getValue());
        ip.setPrenom(cip.getPrenom().getValue());
        ip.setSexe(cip.isSexe());
        ip.setDateNaissance(cip.getDateNaissance().getValue());
        return ip;
    }
    
    @WebMethod(exclude = true)
    public org.datacontract.schemas._2004._07.objetexterne.InfoPersonne  convertJavaPersonneToCSharp(InfoPersonne jip)
    {
            org.datacontract.schemas._2004._07.objetexterne.InfoPersonne cip = new org.datacontract.schemas._2004._07.objetexterne.InfoPersonne();
            ObjectFactory factory = new ObjectFactory();            
            cip.setNom(factory.createInfoPersonneNom(jip.getNom()));
            cip.setPrenom(factory.createInfoPersonnePrenom(jip.getPrenom()));
            cip.setSexe(jip.isSexe());
            cip.setDateNaissance(factory.createInfoPersonneDateNaissance(jip.getDateNaissance()));
            return cip;
    }
}
