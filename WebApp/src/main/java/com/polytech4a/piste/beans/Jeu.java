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
public class Jeu {
    private Integer numjeu;
    private String libellejeu;
    private Collection<Appartient> appartientsByNumjeu;
    private Collection<Inscription> inscriptionsByNumjeu;
    private Collection<Mission> missionsByNumjeu;

    @Id
    @Column(name = "NUMJEU")
    public Integer getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(Integer numjeu) {
        this.numjeu = numjeu;
    }

    @Basic
    @Column(name = "LIBELLEJEU")
    public String getLibellejeu() {
        return libellejeu;
    }

    public void setLibellejeu(String libellejeu) {
        this.libellejeu = libellejeu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jeu jeu = (Jeu) o;

        if (numjeu != null ? !numjeu.equals(jeu.numjeu) : jeu.numjeu != null) return false;
        if (libellejeu != null ? !libellejeu.equals(jeu.libellejeu) : jeu.libellejeu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numjeu != null ? numjeu.hashCode() : 0;
        result = 31 * result + (libellejeu != null ? libellejeu.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "jeuByNumjeu")
    public Collection<Appartient> getAppartientsByNumjeu() {
        return appartientsByNumjeu;
    }

    public void setAppartientsByNumjeu(Collection<Appartient> appartientsByNumjeu) {
        this.appartientsByNumjeu = appartientsByNumjeu;
    }

    @OneToMany(mappedBy = "jeuByNumjeu")
    public Collection<Inscription> getInscriptionsByNumjeu() {
        return inscriptionsByNumjeu;
    }

    public void setInscriptionsByNumjeu(Collection<Inscription> inscriptionsByNumjeu) {
        this.inscriptionsByNumjeu = inscriptionsByNumjeu;
    }

    @OneToMany(mappedBy = "jeuByNumjeu")
    public Collection<Mission> getMissionsByNumjeu() {
        return missionsByNumjeu;
    }

    public void setMissionsByNumjeu(Collection<Mission> missionsByNumjeu) {
        this.missionsByNumjeu = missionsByNumjeu;
    }
}
