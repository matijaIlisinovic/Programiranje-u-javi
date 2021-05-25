package hr.java.covidportal.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * definira osobu i njene osnovne podatke
 */
public class Osoba implements Serializable {

    private String ime;
    private String prezime;
    private String oib;
    private Integer starost;
    private Zupanija zupanija;
    private Bolest zarazenBolescu;
    private List<Osoba> kontaktiraneOsobe;

    /**
     * pojednostavljuje generiranje nove osobe
     */
    public static class Builder {


        private String ime;
        private String prezime;
        private String oib;
        private Integer starost;
        private Zupanija zupanija;
        private Bolest zarazenBolescu;
        private List<Osoba> kontaktiraneOsobe;



        /**
         * dodavanje imena osobe builderu
         *
         * @param ime ime osobe
         * @return vraca se builder za daljnji rad
         */
        public Builder sImenom(String ime) {
            this.ime = ime;
            return this;
        }

        /**
         * dodavanje prezimena osobe builderu
         *
         * @param prezime prezime osobe
         * @return vraca se builder za daljnji rad
         */
        public Builder sPrezimenom(String prezime) {
            this.prezime = prezime;
            return this;
        }
        /**
         * dodavanje oiba
         */
        public Builder sOibom(String oib){
            this.oib=oib;
            return this;
        }

        /**
         * dodavanje starosti osobe builderu
         *
         * @param starost starost osobe
         * @return vraca se builder za daljnji rad
         */
        public Builder stara(Integer starost) {
            this.starost = starost;
            return this;
        }

        /**
         * dodavanje zupanije u kojoj osoba stanuje builderu
         *
         * @param zupanija zupanija stanovanja
         * @return vraca se builder za daljnji rad
         */
        public Builder uZupaniji(Zupanija zupanija) {
            this.zupanija = zupanija;
            return this;
        }

        /**
         * dodavanje bolesti osobe builderu
         *
         * @param bolest bolest kojom je osoba zarazena
         * @return vraca se builder za daljnji rad
         */
        public Builder zarazenaBolescu(Bolest bolest) {
            this.zarazenBolescu = bolest;
            return this;
        }

        /**
         * dodavanje kontakata osobe builderu
         *
         * @param osobe osobe s kojima je osoba kontaktirala
         * @return vraća se builder za daljnji rad
         */
        public Builder jeKontaktirala(List<Osoba> osobe) {
            this.kontaktiraneOsobe = osobe;
            return this;
        }

        /**
         * izgraduje osobu sa skupljenim parametrima
         * ukoliko je osoba zarazena virusom, prenosi ga kontaktiranim osobama
         *
         * @return vraca novu osobu
         */
        public Osoba build(){
            Osoba osoba = new Osoba();
            osoba.ime = this.ime;
            osoba.prezime = this.prezime;
            osoba.oib = this.oib;
            osoba.starost = this.starost;
            osoba.zupanija = this.zupanija;
            osoba.zarazenBolescu = this.zarazenBolescu;
            osoba.kontaktiraneOsobe = this.kontaktiraneOsobe;
            if(zarazenBolescu instanceof Virus && !(kontaktiraneOsobe==null)){
                Iterator<Osoba> kontaktiIterator = kontaktiraneOsobe.listIterator();
                for(int i=0;i< kontaktiraneOsobe.size();i++){
                    kontaktiIterator.next().setZarazenBolescu(zarazenBolescu);
                }
            }
            return osoba;
        }
    }
    private Osoba() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osoba osoba = (Osoba) o;
        return ime.equals(osoba.getIme()) &&
                prezime.equals(osoba.getPrezime());
    }

    @Override
    public String toString() {
        return
                ime + ' ' + prezime +
                ", " + starost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime);
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public Integer getStarost() {
        return starost;
    }

    public void setStarost(Integer starost) {
        this.starost = starost;
    }

    public Zupanija getZupanija() {
        return zupanija;
    }

    public void setZupanija(Zupanija zupanija) {
        this.zupanija = zupanija;
    }

    public Bolest getZarazenBolescu() {
        return zarazenBolescu;
    }

    public void setZarazenBolescu(Bolest zarazenBolescu) {
        this.zarazenBolescu = zarazenBolescu;
    }

    public List<Osoba> getKontaktiraneOsobe() {
        return kontaktiraneOsobe;
    }

    public void setKontaktiraneOsobe(List<Osoba> kontaktiraneOsobe) {
        this.kontaktiraneOsobe = kontaktiraneOsobe;
    }
}
