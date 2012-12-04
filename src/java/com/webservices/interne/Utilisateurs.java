/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservices.interne;

import com.jpa.Utilisateur;
import java.security.NoSuchAlgorithmException;
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
@WebService(serviceName = "Utilisateurs")
@Stateless()
public class Utilisateurs {
    
    @PersistenceContext(unitName = "WebServiceJavaPU")
    private EntityManager em;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "Connexion")
    public Utilisateur hello(String login, String password) {
        Query query = em.createNamedQuery("Utilisateur.connect");
        query.setParameter("login", login);
        try {
            query.setParameter("mdp", com.utils.Utils.sha1(password).toLowerCase());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utilisateurs.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(query.getResultList().size() == 1)
        {
            return (Utilisateur)query.getResultList().get(0);
        }
        else
        {
            return null;
        }        
    }
}
