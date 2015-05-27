package dao;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
@IdClass(ObtientPK.class)
public class Obtient {
    private int numapprenant;
    private Date datejour;
    private int numaction;
    private Integer valeurdebut;
    private Integer valeurfin;
    private Action actionByNumaction;
    private Calendrier calendrierByDatejour;
    private Apprenant apprenantByNumapprenant;

    @Id
    @Column(name = "NUMAPPRENANT", nullable = false, insertable = true, updatable = true)
    public int getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(int numapprenant) {
        this.numapprenant = numapprenant;
    }

    @Id
    @Column(name = "DATEJOUR", nullable = false, insertable = true, updatable = true)
    public Date getDatejour() {
        return datejour;
    }

    public void setDatejour(Date datejour) {
        this.datejour = datejour;
    }

    @Id
    @Column(name = "NUMACTION", nullable = false, insertable = true, updatable = true)
    public int getNumaction() {
        return numaction;
    }

    public void setNumaction(int numaction) {
        this.numaction = numaction;
    }

    @Basic
    @Column(name = "VALEURDEBUT", nullable = true, insertable = true, updatable = true)
    public Integer getValeurdebut() {
        return valeurdebut;
    }

    public void setValeurdebut(Integer valeurdebut) {
        this.valeurdebut = valeurdebut;
    }

    @Basic
    @Column(name = "VALEURFIN", nullable = true, insertable = true, updatable = true)
    public Integer getValeurfin() {
        return valeurfin;
    }

    public void setValeurfin(Integer valeurfin) {
        this.valeurfin = valeurfin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Obtient obtient = (Obtient) o;

        if (numapprenant != obtient.numapprenant) return false;
        if (numaction != obtient.numaction) return false;
        if (datejour != null ? !datejour.equals(obtient.datejour) : obtient.datejour != null) return false;
        if (valeurdebut != null ? !valeurdebut.equals(obtient.valeurdebut) : obtient.valeurdebut != null) return false;
        return !(valeurfin != null ? !valeurfin.equals(obtient.valeurfin) : obtient.valeurfin != null);

    }

    @Override
    public int hashCode() {
        int result = numapprenant;
        result = 31 * result + (datejour != null ? datejour.hashCode() : 0);
        result = 31 * result + numaction;
        result = 31 * result + (valeurdebut != null ? valeurdebut.hashCode() : 0);
        result = 31 * result + (valeurfin != null ? valeurfin.hashCode() : 0);
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
    @JoinColumn(name = "DATEJOUR", referencedColumnName = "DATEJOUR", nullable = false)
    public Calendrier getCalendrierByDatejour() {
        return calendrierByDatejour;
    }

    public void setCalendrierByDatejour(Calendrier calendrierByDatejour) {
        this.calendrierByDatejour = calendrierByDatejour;
    }

    @ManyToOne
    @JoinColumn(name = "NUMAPPRENANT", referencedColumnName = "NUMAPPRENANT", nullable = false)
    public Apprenant getApprenantByNumapprenant() {
        return apprenantByNumapprenant;
    }

    public void setApprenantByNumapprenant(Apprenant apprenantByNumapprenant) {
        this.apprenantByNumapprenant = apprenantByNumapprenant;
    }
}
