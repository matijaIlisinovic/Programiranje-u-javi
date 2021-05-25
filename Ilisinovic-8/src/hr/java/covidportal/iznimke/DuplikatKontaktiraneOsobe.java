package hr.java.covidportal.iznimke;

/**
 * upozorava na više istih osoba u kontaktima
 *
 */
public class DuplikatKontaktiraneOsobe extends RuntimeException{
    /**
     * prima poruku pogreske
     * @param poruka
     */
    public DuplikatKontaktiraneOsobe(String poruka){
        super(poruka);
    }

    /**
     * prima poruku i Throwable pogreske
     * @param poruka
     * @param uzrok
     */
    public DuplikatKontaktiraneOsobe(String poruka, Throwable uzrok){
        super(poruka, uzrok);
    }

    /**
     * prima Throwable pogreske
     * @param uzrok
     */
    public DuplikatKontaktiraneOsobe(Throwable uzrok){
        super(uzrok);
    }
}
