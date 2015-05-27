package dao;

import javax.persistence.*;

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
    private Action actionByNumaction;

    @Id
    @Column(name = "NUMINDIC", nullable = false, insertable = true, updatable = true)
    public int getNumindic() {
        return numindic;
    }

    public void setNumindic(int numindic) {
        this.numindic = numindic;
    }

    @Basic
    @Column(name = "NUMACTION", nullable = false, insertable = true, updatable = true)
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
    }

    @Basic
    @Column(name = "LIBINDIC", nullable = true, insertable = true, updatable = true, length = 20)
    public String getLibindic() {
        return libindic;
    }

    public void setLibindic(String libindic) {
        this.libindic = libindic;
    }

    @Basic
    @Column(name = "POIDS", nullable = true, insertable = true, updatable = true)
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
        return !(poids != null ? !poids.equals(that.poids) : that.poids != null);

    }

    @Override
    public int hashCode() {
        int result = numindic;
        result = 31 * result + numaction;
        result = 31 * result + (libindic != null ? libindic.hashCode() : 0);
        result = 31 * result + (poids != null ? poids.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMACTION", referencedColumnName = "NUMACTION", nullable = false)
    public Action getActionByNumaction() {
        return actionByNumaction;
    }

    public void setActionByNumaction(Action actionByNumaction) {
        this.actionByNumaction = actionByNumaction;
    }
}
