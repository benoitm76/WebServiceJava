/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservices.externe;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Benoît
 */
@WebService(serviceName = "AndroidWebService")
@Stateless()
public class AndroidWebService {
    
    @WebMethod(operationName = "factorielleNombre")
    public long factorielleNombre(@WebParam(name = "nombre") int nombre) {
        long factNbr = 1;
        for(int i = 1; i < nombre; i++)
        {
            factNbr += i * factNbr;
        }
        System.out.println("/*** Le client Android fait un appel au serveur Java ***/");
        return factNbr;
    }
   
}
