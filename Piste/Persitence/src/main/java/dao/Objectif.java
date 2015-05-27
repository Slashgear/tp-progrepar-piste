package dao;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
public class Objectif {
    private int numobjectif;
    private String libobectif;
    private Collection<EstAssocie> estAssociesByNumobjectif;
    private Collection<Fixe> fixesByNumobjectif;

    @Id
    @Column(name = "NUMOBJECTIF", nullable = false, insertable = true, updatable = true)
    public int getNumobjectif() {
        return numobjectif;
    }

    public void setNumobjectif(int numobjectif) {
        this.numobjectif = numobjectif;
    }

    @Basic
    @Column(name = "LIBOBECTIF", nullable = true, insertable = true, updatable = true, length = 25)
    public String getLibobectif() {
        return libobectif;
    }

    public void setLibobectif(String libobectif) {
        this.libobectif = libobectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Objectif objectif = (Objectif) o;

        if (numobjectif != objectif.numobjectif) return false;
        return !(libobectif != null ? !libobectif.equals(objectif.libobectif) : objectif.libobectif != null);

    }

    @Override
    public int hashCode() {
        int result = numobjectif;
        result = 31 * result + (libobectif != null ? libobectif.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "objectifByNumobjectif")
    public Collection<EstAssocie> getEstAssociesByNumobjectif() {
        return estAssociesByNumobjectif;
    }

    public void setEstAssociesByNumobjectif(Collection<EstAssocie> estAssociesByNumobjectif) {
        this.estAssociesByNumobjectif = estAssociesByNumobjectif;
    }

    @OneToMany(mappedBy = "objectifByNumobjectif")
    public Collection<Fixe> getFixesByNumobjectif() {
        return fixesByNumobjectif;
    }

    public void setFixesByNumobjectif(Collection<Fixe> fixesByNumobjectif) {
        this.fixesByNumobjectif = fixesByNumobjectif;
    }
}
