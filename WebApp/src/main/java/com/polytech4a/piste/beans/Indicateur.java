package com.polytech4a.piste.beans;

import javax.persistence.*;

/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
public class Indicateur {
    private Integer numindic;
    private Integer numaction;
    private String libindic;
    private Integer poids;
    private Action actionByNumaction;

    @Id
    @Column(name = "NUMINDIC")
    public Integer getNumindic() {
        return numindic;
    }

    public void setNumindic(Integer numindic) {
        this.numindic = numindic;
    }

    @Basic
    @Column(name = "NUMACTION")
    public Integer getNumaction() {
        return numaction;
    }

    public void setNumaction(Integer numaction) {
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

        if (numindic != null ? !numindic.equals(that.numindic) : that.numindic != null) return false;
        if (numaction != null ? !numaction.equals(that.numaction) : that.numaction != null) return false;
        if (libindic != null ? !libindic.equals(that.libindic) : that.libindic != null) return false;
        if (poids != null ? !poids.equals(that.poids) : that.poids != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numindic != null ? numindic.hashCode() : 0;
        result = 31 * result + (numaction != null ? numaction.hashCode() : 0);
        result = 31 * result + (libindic != null ? libindic.hashCode() : 0);
        result = 31 * result + (poids != null ? poids.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMACTION", referencedColumnName = "NUMACTION", nullable = false, insertable = false, updatable = false)
    public Action getActionByNumaction() {
        return actionByNumaction;
    }

    public void setActionByNumaction(Action actionByNumaction) {
        this.actionByNumaction = actionByNumaction;
    }
}
