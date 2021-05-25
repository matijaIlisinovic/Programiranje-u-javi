package hr.java.covidportal.iznimke;

/**
 * upozorava na iste simptome više bolesti
 *
 */
public class BolestIstihSimptoma extends Exception{
    /**
     * prima poruku pogreske
     * @param message
     */
    public BolestIstihSimptoma(String message) {
        super(message);
    }

    /**
     * prima poruku i Throwable pogreske
      * @param message
     * @param cause
     */
    public BolestIstihSimptoma(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * prima Throwable pogreske
     * @param cause
     */
    public BolestIstihSimptoma(Throwable cause) {
        super(cause);
    }
}
