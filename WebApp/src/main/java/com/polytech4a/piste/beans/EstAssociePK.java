package com.polytech4a.piste.beans;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class EstAssociePK implements Serializable {
    private Integer numaction;
    private Integer numobjectif;

    @Column(name = "NUMACTION")
    @Id
    public Integer getNumaction() {
        return numaction;
    }

    public void setNumaction(Integer numaction) {
        this.numaction = numaction;
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

        EstAssociePK that = (EstAssociePK) o;

        if (numaction != null ? !numaction.equals(that.numaction) : that.numaction != null) return false;
        if (numobjectif != null ? !numobjectif.equals(that.numobjectif) : that.numobjectif != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numaction != null ? numaction.hashCode() : 0;
        result = 31 * result + (numobjectif != null ? numobjectif.hashCode() : 0);
        return result;
    }
}
