package dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Alexandre
 *         27/05/2015
 */
public class AppartientPK implements Serializable {
    private int numjeu;
    private int numaction;

    @Column(name = "NUMJEU", nullable = false, insertable = true, updatable = true)
    @Id
    public int getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(int numjeu) {
        this.numjeu = numjeu;
    }

    @Column(name = "NUMACTION", nullable = false, insertable = true, updatable = true)
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

        AppartientPK that = (AppartientPK) o;

        if (numjeu != that.numjeu) return false;
        return numaction == that.numaction;

    }

    @Override
    public int hashCode() {
        int result = numjeu;
        result = 31 * result + numaction;
        return result;
    }
}
