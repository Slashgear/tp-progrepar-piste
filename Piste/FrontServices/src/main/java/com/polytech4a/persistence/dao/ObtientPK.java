package com.polytech4a.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Antoine CARON on 03/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class ObtientPK implements Serializable {
    private int numapprenant;
    private Date datejour;
    private int numaction;

    @Column(name = "NUMAPPRENANT")
    @Id
    public int getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(int numapprenant) {
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
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObtientPK obtientPK = (ObtientPK) o;

        if (numapprenant != obtientPK.numapprenant) return false;
        if (numaction != obtientPK.numaction) return false;
        if (datejour != null ? !datejour.equals(obtientPK.datejour) : obtientPK.datejour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numapprenant;
        result = 31 * result + (datejour != null ? datejour.hashCode() : 0);
        result = 31 * result + numaction;
        return result;
    }
}
