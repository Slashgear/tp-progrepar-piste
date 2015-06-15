package com.polytech4a.piste.beans;

import javax.persistence.*;

/**
 * Created by Antoine CARON on 15/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
@IdClass(InscriptionPK.class)
public class Inscription {
    private Integer numapprenant;
    private Integer numjeu;
    private Apprenant apprenantByNumapprenant;
    private Jeu jeuByNumjeu;

    @Id
    @Column(name = "NUMAPPRENANT")
    public Integer getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(Integer numapprenant) {
        this.numapprenant = numapprenant;
    }

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

        if (numapprenant != null ? !numapprenant.equals(that.numapprenant) : that.numapprenant != null) return false;
        if (numjeu != null ? !numjeu.equals(that.numjeu) : that.numjeu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numapprenant != null ? numapprenant.hashCode() : 0;
        result = 31 * result + (numjeu != null ? numjeu.hashCode() : 0);
        return result;
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
