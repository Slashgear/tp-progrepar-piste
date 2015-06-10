package com.polytech4a.piste.beans;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class ObtientPK implements Serializable {
    private Integer numapprenant;
    private Date datejour;
    private Integer numaction;

    @Column(name = "NUMAPPRENANT")
    @Id
    public Integer getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(Integer numapprenant) {
        this.numapprenant = numapprenant;
    }

    @Column(name = "DATEJOUR")
    @Id
    public Date getDatejour() {
        return datejour;
    }

    public void setDatejour(Date datejour) {
        this.datejour = datejour;
    }

    @Column(name = "NUMACTION")
    @Id
    public Integer getNumaction() {
        return numaction;
    }

    public void setNumaction(Integer numaction) {
        this.numaction = numaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObtientPK obtientPK = (ObtientPK) o;

        if (numapprenant != null ? !numapprenant.equals(obtientPK.numapprenant) : obtientPK.numapprenant != null)
            return false;
        if (datejour != null ? !datejour.equals(obtientPK.datejour) : obtientPK.datejour != null) return false;
        if (numaction != null ? !numaction.equals(obtientPK.numaction) : obtientPK.numaction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numapprenant != null ? numapprenant.hashCode() : 0;
        result = 31 * result + (datejour != null ? datejour.hashCode() : 0);
        result = 31 * result + (numaction != null ? numaction.hashCode() : 0);
        return result;
    }
}
