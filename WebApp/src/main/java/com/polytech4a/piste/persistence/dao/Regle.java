package com.polytech4a.piste.persistence.dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
public class Regle {
    private int numregle;
    private String libregle;
    private Integer scoremin;

    @Id
    @Column(name = "NUMREGLE")
    public int getNumregle() {
        return numregle;
    }

    public void setNumregle(int numregle) {
        this.numregle = numregle;
    }

    @Basic
    @Column(name = "LIBREGLE")
    public String getLibregle() {
        return libregle;
    }

    public void setLibregle(String libregle) {
        this.libregle = libregle;
    }

    @Basic
    @Column(name = "SCOREMIN")
    public Integer getScoremin() {
        return scoremin;
    }

    public void setScoremin(Integer scoremin) {
        this.scoremin = scoremin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Regle regle = (Regle) o;

        if (numregle != regle.numregle) return false;
        if (libregle != null ? !libregle.equals(regle.libregle) : regle.libregle != null) return false;
        if (scoremin != null ? !scoremin.equals(regle.scoremin) : regle.scoremin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numregle;
        result = 31 * result + (libregle != null ? libregle.hashCode() : 0);
        result = 31 * result + (scoremin != null ? scoremin.hashCode() : 0);
        return result;
    }
}
