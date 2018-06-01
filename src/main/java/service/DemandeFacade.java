/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Adresse;
import bean.Demande;
import controller.util.SearchUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lenovo
 */
@Stateless
public class DemandeFacade extends AbstractFacade<Demande> {

    @PersistenceContext(unitName = "com.mycompany_GestionAutorisation_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeFacade() {
        super(Demande.class);
    }

    public List<Demande> searchDemande(String nom, String prenom, Adresse adresse) {
        if (nom == null && prenom == null && adresse == null) {
            return null;
        } else {
            String requete = "SELECT d FROM Demande d WHERE 1=1";
            requete += SearchUtil.addConstraint("d", "nom", "=", nom);
            requete += SearchUtil.addConstraint("d", "prenom", "=", prenom);
            requete += SearchUtil.addConstraint("d", "adresseProjet", "=", adresse);
            return getMultipleResult(requete);
        }
    }

    public Long findAutorisationByDate(Date dateDebut, Date dateFin) {
        String requete = "SELECT COUNT(d.numBureauOrdre) FROM Demande d WHERE 1=1";
        if (dateDebut == null && dateFin == null) {
            return null;
        } else {
            int res = dateDebut.compareTo(dateFin);
            if (res < 0) {
                requete += SearchUtil.addConstraintMinMaxDate("d", "dateDemande", dateDebut, dateFin);
            } else if (res == 0) {
                requete += SearchUtil.addConstraintDate("d", "dateDemande", "=", dateDebut);
            } else {
                requete += SearchUtil.addConstraintMinMaxDate("d", "dateDemande", dateFin, dateDebut);
            }
        }

        return (Long) em.createNativeQuery(requete).getSingleResult();

    }

}
