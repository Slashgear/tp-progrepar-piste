package com.polytech4a.piste.beans;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Antoine CARON on 15/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
public class Apprenant {

    private Integer numapprenant;
    private String nomapprenant;
    private String prenomapprenant;
    private Collection<Inscription> inscriptionsByNumapprenant;
    private Collection<Obtient> obtientsByNumapprenant;

    @Id
    @Column(name = "NUMAPPRENANT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(Integer numapprenant) {
        this.numapprenant = numapprenant;
    }

    @Basic
    @Column(name = "NOMAPPRENANT")
    public String getNomapprenant() {
        return nomapprenant;
    }

    public void setNomapprenant(String nomapprenant) {
        this.nomapprenant = nomapprenant;
    }

    @Basic
    @Column(name = "PRENOMAPPRENANT")
    public String getPrenomapprenant() {
        return prenomapprenant;
    }

    public void setPrenomapprenant(String prenomapprenant) {
        this.prenomapprenant = prenomapprenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apprenant apprenant = (Apprenant) o;

        if (numapprenant != null ? !numapprenant.equals(apprenant.numapprenant) : apprenant.numapprenant != null)
            return false;
        if (nomapprenant != null ? !nomapprenant.equals(apprenant.nomapprenant) : apprenant.nomapprenant != null)
            return false;
        if (prenomapprenant != null ? !prenomapprenant.equals(apprenant.prenomapprenant) : apprenant.prenomapprenant != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numapprenant != null ? numapprenant.hashCode() : 0;
        result = 31 * result + (nomapprenant != null ? nomapprenant.hashCode() : 0);
        result = 31 * result + (prenomapprenant != null ? prenomapprenant.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "apprenantByNumapprenant")
    public Collection<Inscription> getInscriptionsByNumapprenant() {
        return inscriptionsByNumapprenant;
    }

    public void setInscriptionsByNumapprenant(Collection<Inscription> inscriptionsByNumapprenant) {
        this.inscriptionsByNumapprenant = inscriptionsByNumapprenant;
    }

    @OneToMany(mappedBy = "apprenantByNumapprenant")
    public Collection<Obtient> getObtientsByNumapprenant() {
        return obtientsByNumapprenant;
    }

    public void setObtientsByNumapprenant(Collection<Obtient> obtientsByNumapprenant) {
        this.obtientsByNumapprenant = obtientsByNumapprenant;
    }
}
