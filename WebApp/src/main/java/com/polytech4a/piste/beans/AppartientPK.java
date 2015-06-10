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
public class AppartientPK implements Serializable {
    private Integer numjeu;
    private Integer numaction;

    @Column(name = "NUMJEU")
    @Id
    public Integer getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(Integer numjeu) {
        this.numjeu = numjeu;
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

        AppartientPK that = (AppartientPK) o;

        if (numjeu != null ? !numjeu.equals(that.numjeu) : that.numjeu != null) return false;
        if (numaction != null ? !numaction.equals(that.numaction) : that.numaction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numjeu != null ? numjeu.hashCode() : 0;
        result = 31 * result + (numaction != null ? numaction.hashCode() : 0);
        return result;
    }
}
