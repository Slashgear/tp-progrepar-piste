package dao;

import javax.persistence.*;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
@Table(name = "est_associe", schema = "", catalog = "permispiste")
@IdClass(EstAssociePK.class)
public class EstAssocie {
    private int numaction;
    private int numobjectif;
    private Objectif objectifByNumobjectif;
    private Action actionByNumaction;

    @Id
    @Column(name = "NUMACTION", nullable = false, insertable = true, updatable = true)
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
    }

    @Id
    @Column(name = "NUMOBJECTIF", nullable = false, insertable = true, updatable = true)
    public int getNumobjectif() {
        return numobjectif;
    }

    public void setNumobjectif(int numobjectif) {
        this.numobjectif = numobjectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstAssocie that = (EstAssocie) o;

        if (numaction != that.numaction) return false;
        return numobjectif == that.numobjectif;

    }

    @Override
    public int hashCode() {
        int result = numaction;
        result = 31 * result + numobjectif;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMOBJECTIF", referencedColumnName = "NUMOBJECTIF", nullable = false)
    public Objectif getObjectifByNumobjectif() {
        return objectifByNumobjectif;
    }

    public void setObjectifByNumobjectif(Objectif objectifByNumobjectif) {
        this.objectifByNumobjectif = objectifByNumobjectif;
    }

    @ManyToOne
    @JoinColumn(name = "NUMACTION", referencedColumnName = "NUMACTION", nullable = false)
    public Action getActionByNumaction() {
        return actionByNumaction;
    }

    public void setActionByNumaction(Action actionByNumaction) {
        this.actionByNumaction = actionByNumaction;
    }
}
