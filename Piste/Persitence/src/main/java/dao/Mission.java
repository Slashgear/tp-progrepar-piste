package dao;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
public class Mission {
    private int nummission;
    private int numjeu;
    private String libmission;
    private Collection<Fixe> fixesByNummission;
    private Jeu jeuByNumjeu;

    @Id
    @Column(name = "NUMMISSION", nullable = false, insertable = true, updatable = true)
    public int getNummission() {
        return nummission;
    }

    public void setNummission(int nummission) {
        this.nummission = nummission;
    }

    @Basic
    @Column(name = "NUMJEU", nullable = false, insertable = true, updatable = true)
    public int getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(int numjeu) {
        this.numjeu = numjeu;
    }

    @Basic
    @Column(name = "LIBMISSION", nullable = true, insertable = true, updatable = true, length = 25)
    public String getLibmission() {
        return libmission;
    }

    public void setLibmission(String libmission) {
        this.libmission = libmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mission mission = (Mission) o;

        if (nummission != mission.nummission) return false;
        if (numjeu != mission.numjeu) return false;
        return !(libmission != null ? !libmission.equals(mission.libmission) : mission.libmission != null);

    }

    @Override
    public int hashCode() {
        int result = nummission;
        result = 31 * result + numjeu;
        result = 31 * result + (libmission != null ? libmission.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "missionByNummission")
    public Collection<Fixe> getFixesByNummission() {
        return fixesByNummission;
    }

    public void setFixesByNummission(Collection<Fixe> fixesByNummission) {
        this.fixesByNummission = fixesByNummission;
    }

    @ManyToOne
    @JoinColumn(name = "NUMJEU", referencedColumnName = "NUMJEU", nullable = false)
    public Jeu getJeuByNumjeu() {
        return jeuByNumjeu;
    }

    public void setJeuByNumjeu(Jeu jeuByNumjeu) {
        this.jeuByNumjeu = jeuByNumjeu;
    }
}
