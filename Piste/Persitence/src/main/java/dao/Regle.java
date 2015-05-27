package dao;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
public class Regle {
    private int numregle;
    private String libregle;
    private Integer scoremin;
    private Collection<Possede> possedesByNumregle;

    @Id
    @Column(name = "NUMREGLE", nullable = false, insertable = true, updatable = true)
    public int getNumregle() {
        return numregle;
    }

    public void setNumregle(int numregle) {
        this.numregle = numregle;
    }

    @Basic
    @Column(name = "LIBREGLE", nullable = true, insertable = true, updatable = true, length = 25)
    public String getLibregle() {
        return libregle;
    }

    public void setLibregle(String libregle) {
        this.libregle = libregle;
    }

    @Basic
    @Column(name = "SCOREMIN", nullable = true, insertable = true, updatable = true)
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
        return !(scoremin != null ? !scoremin.equals(regle.scoremin) : regle.scoremin != null);

    }

    @Override
    public int hashCode() {
        int result = numregle;
        result = 31 * result + (libregle != null ? libregle.hashCode() : 0);
        result = 31 * result + (scoremin != null ? scoremin.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "regleByNumregle")
    public Collection<Possede> getPossedesByNumregle() {
        return possedesByNumregle;
    }

    public void setPossedesByNumregle(Collection<Possede> possedesByNumregle) {
        this.possedesByNumregle = possedesByNumregle;
    }
}
