package dao;

import javax.persistence.*;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
@IdClass(FixePK.class)
public class Fixe {
    private int nummission;
    private int numobjectif;
    private Objectif objectifByNumobjectif;
    private Mission missionByNummission;

    @Id
    @Column(name = "NUMMISSION", nullable = false, insertable = true, updatable = true)
    public int getNummission() {
        return nummission;
    }

    public void setNummission(int nummission) {
        this.nummission = nummission;
    }

    @Id
    @Column(name = "NUMOBJECTIF", nullable = false, insertable = true, updatable = true)
    public int getNumobjectif() {
        return numobjectif;
    }

    public void setNumobjectif(int numobjectif) {
        this.numobjectif = numobjectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fixe fixe = (Fixe) o;

        if (nummission != fixe.nummission) return false;
        return numobjectif == fixe.numobjectif;

    }

    @Override
    public int hashCode() {
        int result = nummission;
        result = 31 * result + numobjectif;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMOBJECTIF", referencedColumnName = "NUMOBJECTIF", nullable = false)
    public Objectif getObjectifByNumobjectif() {
        return objectifByNumobjectif;
    }

    public void setObjectifByNumobjectif(Objectif objectifByNumobjectif) {
        this.objectifByNumobjectif = objectifByNumobjectif;
    }

    @ManyToOne
    @JoinColumn(name = "NUMMISSION", referencedColumnName = "NUMMISSION", nullable = false)
    public Mission getMissionByNummission() {
        return missionByNummission;
    }

    public void setMissionByNummission(Mission missionByNummission) {
        this.missionByNummission = missionByNummission;
    }
}
