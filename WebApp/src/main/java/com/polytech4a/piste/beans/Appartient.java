package com.polytech4a.piste.beans;

import javax.persistence.*;

/**
 * Created by Antoine CARON on 15/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
@IdClass(AppartientPK.class)
public class Appartient {
    private Integer numjeu;
    private Integer numaction;
    private Action actionByNumaction;
    private Jeu jeuByNumjeu;

    @Id
    @Column(name = "NUMJEU")
    public Integer getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(Integer numjeu) {
        this.numjeu = numjeu;
    }

    @Id
    @Column(name = "NUMACTION")
    public Integer getNumaction() {
        return numaction;
    }

    public void setNumaction(Integer numaction) {
        this.numaction = numaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appartient that = (Appartient) o;

        if (numjeu != null ? !numjeu.equals(that.numjeu) : that.numjeu != null) return false;
        if (numaction != null ? !numaction.equals(that.numaction) : that.numaction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numjeu != null ? numjeu.hashCode() : 0;
        result = 31 * result + (numaction != null ? numaction.hashCode() : 0);
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
    @JoinColumn(name = "NUMJEU", referencedColumnName = "NUMJEU", nullable = false, insertable = false, updatable = false)
    public Jeu getJeuByNumjeu() {
        return jeuByNumjeu;
    }

    public void setJeuByNumjeu(Jeu jeuByNumjeu) {
        this.jeuByNumjeu = jeuByNumjeu;
    }
}
