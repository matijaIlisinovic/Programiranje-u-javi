package hr.java.covidportal.model;

import java.util.Set;

/**
 * nasljeđuje Bolest s dodanim svojstvima zaraznosti
 */
public class Virus extends Bolest implements Zarazno{
    /**
     * konstruktor Virusa
     * @param naziv imenuje virus
     * @param simptomi definiraju simptome virusa
     */
    public Virus(String naziv, Set<Simptom> simptomi, Long id) {
        super(naziv, simptomi,id);
    }

    /**
     * sluzi prenosenju virusa na drugu osobu
     * mijenja svojstvo zarazenBolescu u ovaj virus
     * @param osoba osoba koju zarazujemo
     */
    @Override
    public void prelazakZarazeNaOsobu(Osoba osoba) {
        osoba.setZarazenBolescu(this);
    }
}
