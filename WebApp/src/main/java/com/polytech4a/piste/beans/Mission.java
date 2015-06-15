package com.polytech4a.piste.beans;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Antoine CARON on 15/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
public class Mission {
    private Integer nummission;
    private Integer numjeu;
    private String libmission;
    private Collection<Fixe> fixesByNummission;
    private Jeu jeuByNumjeu;

    @Id
    @Column(name = "NUMMISSION")
    public Integer getNummission() {
        return nummission;
    }

    public void setNummission(Integer nummission) {
        this.nummission = nummission;
    }

    @Basic
    @Column(name = "NUMJEU")
    public Integer getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(Integer numjeu) {
        this.numjeu = numjeu;
    }

    @Basic
    @Column(name = "LIBMISSION")
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

        if (nummission != null ? !nummission.equals(mission.nummission) : mission.nummission != null) return false;
        if (numjeu != null ? !numjeu.equals(mission.numjeu) : mission.numjeu != null) return false;
        if (libmission != null ? !libmission.equals(mission.libmission) : mission.libmission != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nummission != null ? nummission.hashCode() : 0;
        result = 31 * result + (numjeu != null ? numjeu.hashCode() : 0);
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
    @JoinColumn(name = "NUMJEU", referencedColumnName = "NUMJEU", nullable = false, insertable = false, updatable = false)
    public Jeu getJeuByNumjeu() {
        return jeuByNumjeu;
    }

    public void setJeuByNumjeu(Jeu jeuByNumjeu) {
        this.jeuByNumjeu = jeuByNumjeu;
    }
}
