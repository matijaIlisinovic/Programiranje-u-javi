package hr.java.covidportal.model;

import java.util.Objects;

/**
 * definira zupaniju s nazivom i brojem stanovnika
 */
public class Zupanija extends ImenovaniEntitet{
    private Integer brojStanovnika;
    private Integer brojZarazenih;

    /**
     * konstruktor Zupanija
     * @param naziv ime zupanije
     * @param brojZarazenih broj zarazenih stanovnika zupanije
     * @param brojStanovnika broj stanovnika zupanije
     */
    public Zupanija(String naziv, Integer brojStanovnika, Integer brojZarazenih, Long id) {
        super(naziv,id);
        this.brojStanovnika = brojStanovnika;
        this.brojZarazenih = brojZarazenih;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zupanija zupanija = (Zupanija) o;
        return naziv.equals(zupanija.getNaziv());
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

    public Integer getBrojZarazenih() {
        return brojZarazenih;
    }

    public void setBrojZarazenih(Integer brojZarazenih) {
        this.brojZarazenih = brojZarazenih;
    }

    public Integer getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(Integer brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
