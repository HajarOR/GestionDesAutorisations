/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Adresse;
import bean.Protestation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lenovo
 */
@Stateless
public class ProtestationFacade extends AbstractFacade<Protestation> {

    @PersistenceContext(unitName = "com.mycompany_GestionAutorisation_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProtestationFacade() {
        super(Protestation.class);
    }

    public List<Protestation> searchDemande(Adresse adresse) {
        if (adresse == null) {
            return null;
        } else {
            String requete = "SELECT p FROM Protestation p WHERE p.adresseProjet='" + adresse + "' ";
            return getMultipleResult(requete);
        }
    }

}
