package hr.java.covidportal.genericsi;

import hr.java.covidportal.model.Osoba;
import hr.java.covidportal.model.Virus;

import java.util.List;

/**
 * sadrzi poznate viruse i zarazne ljude u listi
 *
 * @param <T> parametar koji nasljeduje klasu Virus
 * @param <S> parametar koji nasljeduje klasu Osoba
 */
public class KlinikaZaInfektivneBolesti <T extends Virus,S extends Osoba>{
    private List<T> poznatiVirusi;
    private List<S> osobeZarazeneVirusom;

    public KlinikaZaInfektivneBolesti(List<T> poznatiVirusi, List<S> osobeZarazeneVirusom) {
        this.poznatiVirusi = poznatiVirusi;
        this.osobeZarazeneVirusom = osobeZarazeneVirusom;
    }

    public List<T> getPoznatiVirusi() {
        return poznatiVirusi;
    }

    public void dodajNoviVirus(T virus) {
        this.poznatiVirusi.add(virus);
    }

    public List<S> getOsobeZarazeneVirusom() {
        return osobeZarazeneVirusom;
    }

    public void dodajZarazenuOsobu(S novaOsoba) {
        this.osobeZarazeneVirusom.add(novaOsoba);
    }
}
