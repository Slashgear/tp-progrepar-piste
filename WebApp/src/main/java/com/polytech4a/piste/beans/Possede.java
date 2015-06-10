package com.polytech4a.piste.beans;

import javax.persistence.*;

/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
@IdClass(PossedePK.class)
public class Possede {
    private Integer numaction;
    private Integer numregle;
    private Regle regleByNumregle;
    private Action actionByNumaction;

    @Id
    @Column(name = "NUMACTION")
    public Integer getNumaction() {
        return numaction;
    }

    public void setNumaction(Integer numaction) {
        this.numaction = numaction;
    }

    @Id
    @Column(name = "NUMREGLE")
    public Integer getNumregle() {
        return numregle;
    }

    public void setNumregle(Integer numregle) {
        this.numregle = numregle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Possede possede = (Possede) o;

        if (numaction != null ? !numaction.equals(possede.numaction) : possede.numaction != null) return false;
        if (numregle != null ? !numregle.equals(possede.numregle) : possede.numregle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numaction != null ? numaction.hashCode() : 0;
        result = 31 * result + (numregle != null ? numregle.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMREGLE", referencedColumnName = "NUMREGLE", nullable = false, insertable = false, updatable = false)
    public Regle getRegleByNumregle() {
        return regleByNumregle;
    }

    public void setRegleByNumregle(Regle regleByNumregle) {
        this.regleByNumregle = regleByNumregle;
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
