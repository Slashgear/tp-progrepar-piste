package dao;

import javax.persistence.*;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
@IdClass(PossedePK.class)
public class Possede {
    private int numaction;
    private int numregle;
    private Regle regleByNumregle;
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
    @Column(name = "NUMREGLE", nullable = false, insertable = true, updatable = true)
    public int getNumregle() {
        return numregle;
    }

    public void setNumregle(int numregle) {
        this.numregle = numregle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Possede possede = (Possede) o;

        if (numaction != possede.numaction) return false;
        return numregle == possede.numregle;

    }

    @Override
    public int hashCode() {
        int result = numaction;
        result = 31 * result + numregle;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMREGLE", referencedColumnName = "NUMREGLE", nullable = false)
    public Regle getRegleByNumregle() {
        return regleByNumregle;
    }

    public void setRegleByNumregle(Regle regleByNumregle) {
        this.regleByNumregle = regleByNumregle;
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
