package dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Alexandre
 *         27/05/2015
 */
public class FixePK implements Serializable {
    private int nummission;
    private int numobjectif;

    @Column(name = "NUMMISSION", nullable = false, insertable = true, updatable = true)
    @Id
    public int getNummission() {
        return nummission;
    }

    public void setNummission(int nummission) {
        this.nummission = nummission;
    }

    @Column(name = "NUMOBJECTIF", nullable = false, insertable = true, updatable = true)
    @Id
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

        FixePK fixePK = (FixePK) o;

        if (nummission != fixePK.nummission) return false;
        return numobjectif == fixePK.numobjectif;

    }

    @Override
    public int hashCode() {
        int result = nummission;
        result = 31 * result + numobjectif;
        return result;
    }
}
