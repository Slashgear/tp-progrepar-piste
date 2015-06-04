package com.polytech4a.piste.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Entity
public class Calendrier {
    private Date datejour;

    @Id
    @Column(name = "DATEJOUR")
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

        if (datejour != null ? !datejour.equals(that.datejour) : that.datejour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return datejour != null ? datejour.hashCode() : 0;
    }
}
