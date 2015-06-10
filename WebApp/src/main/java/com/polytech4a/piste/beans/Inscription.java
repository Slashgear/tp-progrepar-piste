package com.polytech4a.piste.beans;

import javax.persistence.*;

/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
public class Inscription {
    private Integer numjeu;
    private Apprenant apprenantByNumapprenant;
    private Jeu jeuByNumjeu;

    @Id
    @Column(name = "NUMJEU")
    public Integer getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(Integer numjeu) {
        this.numjeu = numjeu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inscription that = (Inscription) o;

        if (numjeu != null ? !numjeu.equals(that.numjeu) : that.numjeu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return numjeu != null ? numjeu.hashCode() : 0;
    }

    @ManyToOne
    @JoinColumn(name = "NUMAPPRENANT", referencedColumnName = "NUMAPPRENANT", nullable = false, insertable = false, updatable = false)
    public Apprenant getApprenantByNumapprenant() {
        return apprenantByNumapprenant;
    }

    public void setApprenantByNumapprenant(Apprenant apprenantByNumapprenant) {
        this.apprenantByNumapprenant = apprenantByNumapprenant;
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
