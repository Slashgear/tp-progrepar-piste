package dao;

import javax.persistence.*;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
@IdClass(AppartientPK.class)
public class Appartient {
    private int numjeu;
    private int numaction;
    private Action actionByNumaction;
    private Jeu jeuByNumjeu;

    @Id
    @Column(name = "NUMJEU", nullable = false, insertable = true, updatable = true)
    public int getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(int numjeu) {
        this.numjeu = numjeu;
    }

    @Id
    @Column(name = "NUMACTION", nullable = false, insertable = true, updatable = true)
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appartient that = (Appartient) o;

        if (numjeu != that.numjeu) return false;
        return numaction == that.numaction;

    }

    @Override
    public int hashCode() {
        int result = numjeu;
        result = 31 * result + numaction;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NUMACTION", referencedColumnName = "NUMACTION", nullable = false)
    public Action getActionByNumaction() {
        return actionByNumaction;
    }

    public void setActionByNumaction(Action actionByNumaction) {
        this.actionByNumaction = actionByNumaction;
    }

    @ManyToOne
    @JoinColumn(name = "NUMJEU", referencedColumnName = "NUMJEU", nullable = false)
    public Jeu getJeuByNumjeu() {
        return jeuByNumjeu;
    }

    public void setJeuByNumjeu(Jeu jeuByNumjeu) {
        this.jeuByNumjeu = jeuByNumjeu;
    }
}
