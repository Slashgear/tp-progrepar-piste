package com.polytech4a.piste.beans;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
public class Action {
    private Integer numaction;
    private Integer actNumaction;
    private String libaction;
    private Integer scoremin;
    private Action actionByActNumaction;
    private Collection<Action> actionsByNumaction;
    private Collection<Appartient> appartientsByNumaction;
    private Collection<EstAssocie> estAssociesByNumaction;
    private Collection<Indicateur> indicateursByNumaction;
    private Collection<Obtient> obtientsByNumaction;
    private Collection<Possede> possedesByNumaction;

    @Id
    @Column(name = "NUMACTION")
    public Integer getNumaction() {
        return numaction;
    }

    public void setNumaction(Integer numaction) {
        this.numaction = numaction;
    }

    @Basic
    @Column(name = "ACT_NUMACTION")
    public Integer getActNumaction() {
        return actNumaction;
    }

    public void setActNumaction(Integer actNumaction) {
        this.actNumaction = actNumaction;
    }

    @Basic
    @Column(name = "LIBACTION")
    public String getLibaction() {
        return libaction;
    }

    public void setLibaction(String libaction) {
        this.libaction = libaction;
    }

    @Basic
    @Column(name = "SCOREMIN")
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

        Action action = (Action) o;

        if (numaction != null ? !numaction.equals(action.numaction) : action.numaction != null) return false;
        if (actNumaction != null ? !actNumaction.equals(action.actNumaction) : action.actNumaction != null)
            return false;
        if (libaction != null ? !libaction.equals(action.libaction) : action.libaction != null) return false;
        if (scoremin != null ? !scoremin.equals(action.scoremin) : action.scoremin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numaction != null ? numaction.hashCode() : 0;
        result = 31 * result + (actNumaction != null ? actNumaction.hashCode() : 0);
        result = 31 * result + (libaction != null ? libaction.hashCode() : 0);
        result = 31 * result + (scoremin != null ? scoremin.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ACT_NUMACTION", referencedColumnName = "NUMACTION", insertable = false, updatable = false)
    public Action getActionByActNumaction() {
        return actionByActNumaction;
    }

    public void setActionByActNumaction(Action actionByActNumaction) {
        this.actionByActNumaction = actionByActNumaction;
    }

    @OneToMany(mappedBy = "actionByActNumaction")
    public Collection<Action> getActionsByNumaction() {
        return actionsByNumaction;
    }

    public void setActionsByNumaction(Collection<Action> actionsByNumaction) {
        this.actionsByNumaction = actionsByNumaction;
    }

    @OneToMany(mappedBy = "actionByNumaction")
    public Collection<Appartient> getAppartientsByNumaction() {
        return appartientsByNumaction;
    }

    public void setAppartientsByNumaction(Collection<Appartient> appartientsByNumaction) {
        this.appartientsByNumaction = appartientsByNumaction;
    }

    @OneToMany(mappedBy = "actionByNumaction")
    public Collection<EstAssocie> getEstAssociesByNumaction() {
        return estAssociesByNumaction;
    }

    public void setEstAssociesByNumaction(Collection<EstAssocie> estAssociesByNumaction) {
        this.estAssociesByNumaction = estAssociesByNumaction;
    }

    @OneToMany(mappedBy = "actionByNumaction")
    public Collection<Indicateur> getIndicateursByNumaction() {
        return indicateursByNumaction;
    }

    public void setIndicateursByNumaction(Collection<Indicateur> indicateursByNumaction) {
        this.indicateursByNumaction = indicateursByNumaction;
    }

    @OneToMany(mappedBy = "actionByNumaction")
    public Collection<Obtient> getObtientsByNumaction() {
        return obtientsByNumaction;
    }

    public void setObtientsByNumaction(Collection<Obtient> obtientsByNumaction) {
        this.obtientsByNumaction = obtientsByNumaction;
    }

    @OneToMany(mappedBy = "actionByNumaction")
    public Collection<Possede> getPossedesByNumaction() {
        return possedesByNumaction;
    }

    public void setPossedesByNumaction(Collection<Possede> possedesByNumaction) {
        this.possedesByNumaction = possedesByNumaction;
    }
}
