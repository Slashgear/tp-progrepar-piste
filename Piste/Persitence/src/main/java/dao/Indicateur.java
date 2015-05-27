package dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
public class Indicateur {
    private int numindic;
    private int numaction;
    private String libindic;
    private Integer poids;

    @Id
    @Column(name = "NUMINDIC")
    public int getNumindic() {
        return numindic;
    }

    public void setNumindic(int numindic) {
        this.numindic = numindic;
    }

    @Basic
    @Column(name = "NUMACTION")
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
    }

    @Basic
    @Column(name = "LIBINDIC")
    public String getLibindic() {
        return libindic;
    }

    public void setLibindic(String libindic) {
        this.libindic = libindic;
    }

    @Basic
    @Column(name = "POIDS")
    public Integer getPoids() {
        return poids;
    }

    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Indicateur that = (Indicateur) o;

        if (numindic != that.numindic) return false;
        if (numaction != that.numaction) return false;
        if (libindic != null ? !libindic.equals(that.libindic) : that.libindic != null) return false;
        if (poids != null ? !poids.equals(that.poids) : that.poids != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numindic;
        result = 31 * result + numaction;
        result = 31 * result + (libindic != null ? libindic.hashCode() : 0);
        result = 31 * result + (poids != null ? poids.hashCode() : 0);
        return result;
    }
}
