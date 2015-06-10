package com.polytech4a.piste.beans;

import javax.persistence.*;

/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
@IdClass(FixePK.class)
public class Fixe {
    private Integer nummission;
    private Integer numobjectif;
    private Objectif objectifByNumobjectif;
    private Mission missionByNummission;

    @Id
    @Column(name = "NUMMISSION")
    public Integer getNummission() {
        return nummission;
    }

    public void setNummission(Integer nummission) {
        this.nummission = nummission;
    }

    @Id
    @Column(name = "NUMOBJECTIF")
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

        Fixe fixe = (Fixe) o;

        if (nummission != null ? !nummission.equals(fixe.nummission) : fixe.nummission != null) return false;
        if (numobjectif != null ? !numobjectif.equals(fixe.numobjectif) : fixe.numobjectif != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nummission != null ? nummission.hashCode() : 0;
        result = 31 * result + (numobjectif != null ? numobjectif.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMOBJECTIF", referencedColumnName = "NUMOBJECTIF", nullable = false, insertable = false, updatable = false)
    public Objectif getObjectifByNumobjectif() {
        return objectifByNumobjectif;
    }

    public void setObjectifByNumobjectif(Objectif objectifByNumobjectif) {
        this.objectifByNumobjectif = objectifByNumobjectif;
    }

    @ManyToOne
    @JoinColumn(name = "NUMMISSION", referencedColumnName = "NUMMISSION", nullable = false, insertable = false, updatable = false)
    public Mission getMissionByNummission() {
        return missionByNummission;
    }

    public void setMissionByNummission(Mission missionByNummission) {
        this.missionByNummission = missionByNummission;
    }
}
