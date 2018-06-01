/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Lenovo
 */
@Entity
public class Autorisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long numBureauOrdre;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAutorisation;
    @OneToOne
    private Demande demande;

    public Autorisation() {
    }

    public Autorisation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNumBureauOrdre() {
        return numBureauOrdre;
    }

    public void setNumBureauOrdre(long numBureauOrdre) {
        this.numBureauOrdre = numBureauOrdre;
    }

    public Date getDateAutorisation() {
        return dateAutorisation;
    }

    public void setDateAutorisation(Date dateAutorisation) {
        this.dateAutorisation = dateAutorisation;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autorisation)) {
            return false;
        }
        Autorisation other = (Autorisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Autorisation{" + "id=" + id + ", numBureauOrdre=" + numBureauOrdre + ", dateAutorisation=" + dateAutorisation + '}';
    }

}
