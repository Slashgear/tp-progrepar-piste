package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.Collection;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
public class Calendrier {
    private Date datejour;
    private Collection<Obtient> obtientsByDatejour;

    @Id
    @Column(name = "DATEJOUR", nullable = false, insertable = true, updatable = true)
    public Date getDatejour() {
        return datejour;
    }

    public void setDatejour(Date datejour) {
        this.datejour = datejour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calendrier that = (Calendrier) o;

        return !(datejour != null ? !datejour.equals(that.datejour) : that.datejour != null);

    }

    @Override
    public int hashCode() {
        return datejour != null ? datejour.hashCode() : 0;
    }

    @OneToMany(mappedBy = "calendrierByDatejour")
    public Collection<Obtient> getObtientsByDatejour() {
        return obtientsByDatejour;
    }

    public void setObtientsByDatejour(Collection<Obtient> obtientsByDatejour) {
        this.obtientsByDatejour = obtientsByDatejour;
    }
}
