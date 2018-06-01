/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Annexe;
import bean.Quartier;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lenovo
 */
@Stateless
public class QuartierFacade extends AbstractFacade<Quartier> {

    @PersistenceContext(unitName = "com.mycompany_GestionAutorisation_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuartierFacade() {
        super(Quartier.class);
    }
    public List<Quartier> findQuartierByAnnexe(Annexe annexe){
        return getMultipleResult("SELECT q FROM Quartier WHERE q.annexe.id='"+annexe.getId()+"'");
    }
    
}
