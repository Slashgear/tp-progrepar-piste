package dao;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Alexandre
 *         27/05/2015
 */
@Entity
public class Apprenant {
    private int numapprenant;
    private String nomapprenant;
    private String prenomapprenant;
    private Collection<Obtient> obtientsByNumapprenant;

    @Id
    @Column(name = "NUMAPPRENANT", nullable = false, insertable = true, updatable = true)
    public int getNumapprenant() {
        return numapprenant;
    }

    public void setNumapprenant(int numapprenant) {
        this.numapprenant = numapprenant;
    }

    @Basic
    @Column(name = "NOMAPPRENANT", nullable = true, insertable = true, updatable = true, length = 25)
    public String getNomapprenant() {
        return nomapprenant;
    }

    public void setNomapprenant(String nomapprenant) {
        this.nomapprenant = nomapprenant;
    }

    @Basic
    @Column(name = "PRENOMAPPRENANT", nullable = true, insertable = true, updatable = true, length = 25)
    public String getPrenomapprenant() {
        return prenomapprenant;
    }

    public void setPrenomapprenant(String prenomapprenant) {
        this.prenomapprenant = prenomapprenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apprenant apprenant = (Apprenant) o;

        if (numapprenant != apprenant.numapprenant) return false;
        if (nomapprenant != null ? !nomapprenant.equals(apprenant.nomapprenant) : apprenant.nomapprenant != null)
            return false;
        return !(prenomapprenant != null ? !prenomapprenant.equals(apprenant.prenomapprenant) : apprenant.prenomapprenant != null);

    }

    @Override
    public int hashCode() {
        int result = numapprenant;
        result = 31 * result + (nomapprenant != null ? nomapprenant.hashCode() : 0);
        result = 31 * result + (prenomapprenant != null ? prenomapprenant.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "apprenantByNumapprenant")
    public Collection<Obtient> getObtientsByNumapprenant() {
        return obtientsByNumapprenant;
    }

    public void setObtientsByNumapprenant(Collection<Obtient> obtientsByNumapprenant) {
        this.obtientsByNumapprenant = obtientsByNumapprenant;
    }
}
