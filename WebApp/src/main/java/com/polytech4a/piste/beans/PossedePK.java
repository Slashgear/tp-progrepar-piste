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
public class PossedePK implements Serializable {
    private Integer numaction;
    private Integer numregle;

    @Column(name = "NUMACTION")
    @Id
    public Integer getNumaction() {
        return numaction;
    }

    public void setNumaction(Integer numaction) {
        this.numaction = numaction;
    }

    @Column(name = "NUMREGLE")
    @Id
    public Integer getNumregle() {
        return numregle;
    }

    public void setNumregle(Integer numregle) {
        this.numregle = numregle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PossedePK possedePK = (PossedePK) o;

        if (numaction != null ? !numaction.equals(possedePK.numaction) : possedePK.numaction != null) return false;
        if (numregle != null ? !numregle.equals(possedePK.numregle) : possedePK.numregle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numaction != null ? numaction.hashCode() : 0;
        result = 31 * result + (numregle != null ? numregle.hashCode() : 0);
        return result;
    }
}
