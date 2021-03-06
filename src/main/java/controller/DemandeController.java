package controller;

import bean.Adresse;
import bean.Annexe;
import bean.Demande;
import bean.Quartier;
import controller.util.DateUtil;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("demandeController")
@SessionScoped
public class DemandeController implements Serializable {

    @EJB
    private service.DemandeFacade ejbFacade;
    private List<Demande> items = null;
    private Demande selected;
    private List<Demande> filteredDemandes;
    private Adresse adresseProjet;
    private Adresse adressePersonnel;
    private List<Demande> demandesAnnulees;
    private Demande selectedDemande;
    private List<Demande> filteredDemandesAnnulees;
    @EJB
    private service.QuartierFacade quartierFacade;

    public DemandeController() {
    }

    public Demande getSelected() {
        if (selected == null) {
            selected = new Demande();
        }
        return selected;
    }

    public void setSelected(Demande selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeFacade getFacade() {
        return ejbFacade;
    }

    public Demande prepareCreate() {
        selected = new Demande();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Demande> getItems() {
        if (items == null) {
            items = getFacade().findDemandeEnAttente();
        }
        return items;
    }

    public DemandeFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DemandeFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Demande> getFilteredDemandes() {
        return filteredDemandes;
    }

    public void setFilteredDemandes(List<Demande> filteredDemandes) {
        this.filteredDemandes = filteredDemandes;
    }

    public Adresse getAdresseProjet() {
        if (adresseProjet == null) {
            adresseProjet = new Adresse();
        }
        return adresseProjet;
    }

    public void setAdresseProjet(Adresse adresseProjet) {
        this.adresseProjet = adresseProjet;
    }

    public Adresse getAdressePersonnel() {
        if (adressePersonnel == null) {
            adressePersonnel = new Adresse();
        }
        return adressePersonnel;
    }

    public void setAdressePersonnel(Adresse adressePersonnel) {
        this.adressePersonnel = adressePersonnel;
    }

    public List<Demande> getDemandesAnnulees() {
        if (demandesAnnulees == null) {
            demandesAnnulees = getFacade().findDemandeAnnules();
        }
        return demandesAnnulees;
    }

    public void setDemandesAnnulees(List<Demande> demandesAnnulees) {
        this.demandesAnnulees = demandesAnnulees;
    }

    public Demande getSelectedDemande() {
        if (selectedDemande == null) {
            selectedDemande = new Demande();
        }
        return selectedDemande;
    }

    public void setSelectedDemande(Demande selectedDemande) {
        this.selectedDemande = selectedDemande;
    }

    public List<Demande> getFilteredDemandesAnnulees() {
        return filteredDemandesAnnulees;
    }

    public void setFilteredDemandesAnnulees(List<Demande> filteredDemandesAnnulees) {
        this.filteredDemandesAnnulees = filteredDemandesAnnulees;
    }

    public List<Quartier> findQuartierByAnnexe(Annexe annexe) {
        return quartierFacade.findQuartierByAnnexe(annexe);
    }

    public String createDemande() {
        if (selected != null) {
            selected.setAdresseProjet(adresseProjet);
            selected.setAdressePersonnel(adressePersonnel);
            getFacade().edit(selected);
            selected = null;
            adresseProjet = null;
            adressePersonnel = null;

            return "/template/Demandes";
        } else {
            return null;
        }
    }

    public void preparerEdition(Demande demande) {
        adresseProjet = demande.getAdresseProjet();
        adressePersonnel = demande.getAdressePersonnel();
        selected = demande;
    }

    public void editeDemande() {
        if (selected != null) {
            selected.setAdresseProjet(adresseProjet);
            selected.setAdressePersonnel(adressePersonnel);
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("la demande est modifiée");
            selected = null;
            adresseProjet = null;
            adressePersonnel = null;
        }else {
            JsfUtil.addErrorMessage("la demande n'est pas modifiée");
        }
    }

    public String changeDate(Date date) {
        return DateUtil.formateDate("dd/MM/yyyy", date);
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Demande getDemande(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Demande> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Demande> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Demande.class)
    public static class DemandeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeController controller = (DemandeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeController");
            return controller.getDemande(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Demande) {
                Demande o = (Demande) object;
                return getStringKey(o.getNumBureauOrdre());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Demande.class.getName()});
                return null;
            }
        }

    }

}
