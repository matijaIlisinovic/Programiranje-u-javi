package hr.java.covidportal.sort;

import hr.java.covidportal.model.Zupanija;

import java.util.Comparator;

public class CovidSorter implements Comparator<Zupanija> {

    public int compare(Zupanija a, Zupanija b) {
            return (a.getBrojZarazenih()*100/a.getBrojStanovnika())-
                    (b.getBrojZarazenih()*100/b.getBrojStanovnika());
    }
}
