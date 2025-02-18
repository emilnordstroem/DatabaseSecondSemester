package opgave07;

public class Medarbejder {
    private final int medarbejderNr;
    private final String navn;
    private final String stillingsbetegnelse;
    private final String mobil;

    public Medarbejder(int medarbejderNr, String navn, String stillingsbetegnelse, String mobil) {
        this.medarbejderNr = medarbejderNr;
        this.navn = navn;
        this.stillingsbetegnelse = stillingsbetegnelse;
        this.mobil = mobil;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s%n",
                medarbejderNr,
                navn,
                stillingsbetegnelse,
                mobil);
    }
}