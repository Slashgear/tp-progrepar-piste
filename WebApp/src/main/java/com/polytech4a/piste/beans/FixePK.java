package com.polytech4a.piste.beans;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Antoine CARON on 15/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class FixePK implements Serializable {
    private Integer nummission;
    private Integer numobjectif;

    @Column(name = "NUMMISSION")
    @Id
    public Integer getNummission() {
        return nummission;
    }

    public void setNummission(Integer nummission) {
        this.nummission = nummission;
    }

    @Column(name = "NUMOBJECTIF")
    @Id
    public Integer getNumobjectif() {
        return numobjectif;
    }

    public void setNumobjectif(Integer numobjectif) {
        this.numobjectif = numobjectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FixePK fixePK = (FixePK) o;

        if (nummission != null ? !nummission.equals(fixePK.nummission) : fixePK.nummission != null) return false;
        if (numobjectif != null ? !numobjectif.equals(fixePK.numobjectif) : fixePK.numobjectif != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nummission != null ? nummission.hashCode() : 0;
        result = 31 * result + (numobjectif != null ? numobjectif.hashCode() : 0);
        return result;
    }
}
