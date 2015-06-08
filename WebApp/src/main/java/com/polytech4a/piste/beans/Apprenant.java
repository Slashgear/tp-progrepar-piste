package com.polytech4a.piste.beans;

import javax.persistence.*;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
public class Apprenant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numapprenant;
    private String nomapprenant;
    private String prenomapprenant;


    @Column(name = "NUMAPPRENANT")
    public int getNumapprenant() {
        return numapprenant;
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

        if (numapprenant != apprenant.numapprenant) return false;
        if (nomapprenant != null ? !nomapprenant.equals(apprenant.nomapprenant) : apprenant.nomapprenant != null)
            return false;
        if (prenomapprenant != null ? !prenomapprenant.equals(apprenant.prenomapprenant) : apprenant.prenomapprenant != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numapprenant;
        result = 31 * result + (nomapprenant != null ? nomapprenant.hashCode() : 0);
        result = 31 * result + (prenomapprenant != null ? prenomapprenant.hashCode() : 0);
        return result;
    }
}
