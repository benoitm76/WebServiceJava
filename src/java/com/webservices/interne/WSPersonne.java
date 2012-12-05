/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservices.interne;

import com.jpa.TPersonne;
import com.objects.externe.InfoPersonne;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Benoît
 */
@WebService(serviceName = "WSPersonne")
@Stateless()
public class WSPersonne {
    
    @PersistenceContext(unitName = "WebServiceJavaPU")
    private EntityManager em;
    
    @WebMethod(operationName = "RecherchePersonnes")
    public List<TPersonne> RecherchePersonnes(InfoPersonne ip, boolean externe)
    {      
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
        return query.getResultList();
    }
}
