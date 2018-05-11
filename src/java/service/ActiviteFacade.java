/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Activite;
import java.nio.charset.Charset;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lenovo
 */
@Stateless
public class ActiviteFacade extends AbstractFacade<Activite> {

    @PersistenceContext(unitName = "gestionAutorisationsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteFacade() {
        super(Activite.class);
    }

    public void testAffichage() {
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("\u0623\u0624\u0625\u0626\u0627\u0628");
        System.out.println("\u0633\u0634\u0635\u0636\u0637\u0638");
        System.out.println("arabe: \u1606");
        String str1="تبتتبتبتب";
        String str2 = new String(str1.getBytes(),Charset.forName("UTF-8")); 
        System.out.println(str2);
    }

}
