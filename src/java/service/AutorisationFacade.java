/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Adresse;
import bean.Autorisation;
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
public class AutorisationFacade extends AbstractFacade<Autorisation> {

    @PersistenceContext(unitName = "gestionAutorisationsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutorisationFacade() {
        super(Autorisation.class);
    }

    public int confirmerDemande(long numBureauOrdre, Demande demande, Date dateAutorisation) {
        Autorisation autorisation = new Autorisation();
        autorisation.setNumBureauOrdre(numBureauOrdre);
        autorisation.setDateAutorisation(dateAutorisation);
        autorisation.setDemande(demande);
        create(autorisation);
        return 1;

    }

    public List<Autorisation> findAutorisationsByAdresse(Adresse adresse) {
        String query = "SELECT a FROM Autorisation a WHERE a.demande.adresseProjet='" + adresse + "' ";
        return getMultipleResult(query);
    }

    public Long findAutorisationByDate(Date dateDebut, Date dateFin) {
        String requete = "SELECT COUNT(a.id) FROM Autorisation a WHERE 1=1";
        if (dateDebut == null && dateFin == null) {
            return null;
        } else {
            int res = dateDebut.compareTo(dateFin);
            if (res < 0) {
                requete += SearchUtil.addConstraintMinMaxDate("a", "dateAutorisation", dateDebut, dateFin);
            } else if (res == 0) {
                requete += SearchUtil.addConstraintDate("a", "dateAutorisation", "=", dateDebut);
            } else {
                requete += SearchUtil.addConstraintMinMaxDate("a", "dateAutorisation", dateFin, dateDebut);
            }
        }

        return (Long) em.createNativeQuery(requete).getSingleResult();

    }

}
