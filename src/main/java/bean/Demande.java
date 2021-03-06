/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Lenovo
 */
@Entity
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long numBureauOrdre;
    private String nomComplet;
    private String cin;
    private String tele;
    private String type;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDemande;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAnnulation;
    @ManyToOne
    private Activite activite;
    @ManyToOne
    private Adresse adresseProjet;
    @ManyToOne
    private Adresse adressePersonnel;
    @OneToOne(mappedBy = "demande")
    private Autorisation autorisation;

    public Demande() {
    }

    public Demande(Long numBureauOrdre) {
        this.numBureauOrdre = numBureauOrdre;
    }

    public Long getNumBureauOrdre() {
        return numBureauOrdre;
    }

    public void setNumBureauOrdre(Long numBureauOrdre) {
        this.numBureauOrdre = numBureauOrdre;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Date getDateAnnulation() {
        return dateAnnulation;
    }

    public void setDateAnnulation(Date dateAnnulation) {
        this.dateAnnulation = dateAnnulation;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Adresse getAdresseProjet() {
        return adresseProjet;
    }

    public void setAdresseProjet(Adresse adresseProjet) {
        this.adresseProjet = adresseProjet;
    }

    public Adresse getAdressePersonnel() {
        return adressePersonnel;
    }

    public void setAdressePersonnel(Adresse adressePersonnel) {
        this.adressePersonnel = adressePersonnel;
    }

    public Autorisation getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Autorisation autorisation) {
        this.autorisation = autorisation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numBureauOrdre != null ? numBureauOrdre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the numBureauOrdre fields are not set
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.numBureauOrdre == null && other.numBureauOrdre != null) || (this.numBureauOrdre != null && !this.numBureauOrdre.equals(other.numBureauOrdre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Demande{" + "numBureauOrdre=" + numBureauOrdre + ", nomComplet=" + nomComplet + ", cin=" + cin + ", tele=" + tele + ", type=" + type + ", dateDemande=" + dateDemande + ", dateAnnulation=" + dateAnnulation + '}';
    }

}
