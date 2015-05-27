package dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Alexandre
 *         27/05/2015
 */
public class EstAssociePK implements Serializable {
    private int numaction;
    private int numobjectif;

    @Column(name = "NUMACTION", nullable = false, insertable = true, updatable = true)
    @Id
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
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

        EstAssociePK that = (EstAssociePK) o;

        if (numaction != that.numaction) return false;
        return numobjectif == that.numobjectif;

    }

    @Override
    public int hashCode() {
        int result = numaction;
        result = 31 * result + numobjectif;
        return result;
    }
}
