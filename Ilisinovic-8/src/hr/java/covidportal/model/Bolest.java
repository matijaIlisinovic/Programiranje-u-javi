package hr.java.covidportal.model;

import java.util.Objects;
import java.util.Set;

/**
 * definira bolest s nazivom i simptomima
 */
public class Bolest extends ImenovaniEntitet{
    private Set<Simptom> simptomi;

    /**
     * konstruktor Bolest
     * @param naziv imenuje bolest
     * @param simptomi definira simptome
     */
    public Bolest(String naziv, Set<Simptom> simptomi,Long id) {
        super(naziv,id);
        this.simptomi = simptomi;
    }

    public Set<Simptom> getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(Set<Simptom> simptomi) {
        this.simptomi = simptomi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bolest bolest = (Bolest) o;
        return naziv.equals(bolest.naziv) &&
                simptomi.equals(bolest.simptomi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, simptomi);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(naziv +", simptomi:");
        for(Simptom simptom : simptomi){
            s.append(' '+simptom.getNaziv()+' '+simptom.getVrijednost());
        }
        return s.toString();
    }
}
