package com.polytech4a.piste.beans;

import javax.persistence.*;

/**
 * Created by Antoine CARON on 15/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
@IdClass(ObtientPK.class)
public class Obtient {
    private Integer numapprenant;
    private Integer numaction;
    private Integer valeur;
    private Action actionByNumaction;
    private Apprenant apprenantByNumapprenant;

    @Id
    @Column(name = "NUMAPPRENANT")
    public Integer getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(Integer numapprenant) {
        this.numapprenant = numapprenant;
    }

    @Id
    @Column(name = "NUMACTION")
    public Integer getNumaction() {
        return numaction;
    }

    public void setNumaction(Integer numaction) {
        this.numaction = numaction;
    }

    @Basic
    @Column(name = "VALEUR")
    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Obtient obtient = (Obtient) o;

        if (numapprenant != null ? !numapprenant.equals(obtient.numapprenant) : obtient.numapprenant != null)
            return false;
        if (numaction != null ? !numaction.equals(obtient.numaction) : obtient.numaction != null) return false;
        if (valeur != null ? !valeur.equals(obtient.valeur) : obtient.valeur != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numapprenant != null ? numapprenant.hashCode() : 0;
        result = 31 * result + (numaction != null ? numaction.hashCode() : 0);
        result = 31 * result + (valeur != null ? valeur.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "NUMAPPRENANT", referencedColumnName = "NUMAPPRENANT", nullable = false, insertable = false, updatable = false)
    public Apprenant getApprenantByNumapprenant() {
        return apprenantByNumapprenant;
    }

    public void setApprenantByNumapprenant(Apprenant apprenantByNumapprenant) {
        this.apprenantByNumapprenant = apprenantByNumapprenant;
    }
}
