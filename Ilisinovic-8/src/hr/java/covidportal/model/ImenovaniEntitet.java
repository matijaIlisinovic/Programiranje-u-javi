package hr.java.covidportal.model;

import java.io.Serializable;

/**
 * sadrži svojstvo imenovanja
 */
public abstract class ImenovaniEntitet implements Serializable {
    protected String naziv;
    protected Long id;

    /**
     * konstruktor ImenovanogEntiteta
     *
     * @param naziv daje ime entitetu
     */
    public ImenovaniEntitet(String naziv, Long id) {
        this.naziv = naziv; this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
