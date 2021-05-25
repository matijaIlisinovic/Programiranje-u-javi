package hr.java.covidportal.model;

import hr.java.covidportal.enumi.Vrijednost;

import java.util.Objects;

/**
 * definira simptom nazivom i vrijednosti
 */
public class Simptom extends ImenovaniEntitet{
    private Vrijednost vrijednost;

    /**
     * konstruktor Simptoma
     * @param naziv imenuje simptom
     * @param vrijednost definira jacinu simptoma
     */
    public Simptom(String naziv, Vrijednost vrijednost, Long id) {
        super(naziv,id);
        this.vrijednost = vrijednost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simptom simptom = (Simptom) o;
        return naziv.equals(simptom.naziv) &&
                vrijednost == simptom.vrijednost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, vrijednost);
    }

    public Vrijednost getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(Vrijednost vrijednost) {
        this.vrijednost = vrijednost;
    }

    @Override
    public String toString() {
        return naziv +' '+ vrijednost;
    }
}
