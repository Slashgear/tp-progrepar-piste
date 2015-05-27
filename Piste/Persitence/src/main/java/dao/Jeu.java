package dao;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
public class Jeu {
    private int numjeu;
    private String libellejeu;
    private Collection<Appartient> appartientsByNumjeu;
    private Collection<Mission> missionsByNumjeu;

    @Id
    @Column(name = "NUMJEU", nullable = false, insertable = true, updatable = true)
    public int getNumjeu() {
        return numjeu;
    }

    public void setNumjeu(int numjeu) {
        this.numjeu = numjeu;
    }

    @Basic
    @Column(name = "LIBELLEJEU", nullable = true, insertable = true, updatable = true, length = 25)
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

        if (numjeu != jeu.numjeu) return false;
        return !(libellejeu != null ? !libellejeu.equals(jeu.libellejeu) : jeu.libellejeu != null);

    }

    @Override
    public int hashCode() {
        int result = numjeu;
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
    public Collection<Mission> getMissionsByNumjeu() {
        return missionsByNumjeu;
    }

    public void setMissionsByNumjeu(Collection<Mission> missionsByNumjeu) {
        this.missionsByNumjeu = missionsByNumjeu;
    }
}
