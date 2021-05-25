package hr.java.covidportal.enumi;

public enum Vrijednost {
    RIJETKO("RIJETKO"),
    SREDNJE("SREDNJE"),
    CESTO("CESTO");

    private String vrijednost;

    private Vrijednost(String vrijednost){
        this.vrijednost=vrijednost;
    }

    public String getVrijednost() {
        return vrijednost;
    }
}
