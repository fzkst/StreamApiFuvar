package hu.fzks.fuvar;

public class Fuvar {
    private int taxiAzonosito;
    private String indulasIdopontja;
    private int utazasIdotartama;
    private double megtettTavolsag;
    private double viteldij;
    private double borravalo;
    private String fizetesModja;

    public Fuvar(int taxiAzonosito, String indulasIdopontja, int utazasIdotartama, double megtettTavolsag, double viteldij, double borravalo, String fizetesModja) {
        this.taxiAzonosito = taxiAzonosito;
        this.indulasIdopontja = indulasIdopontja;
        this.utazasIdotartama = utazasIdotartama;
        this.megtettTavolsag = megtettTavolsag;
        this.viteldij = viteldij;
        this.borravalo = borravalo;
        this.fizetesModja = fizetesModja;
    }

    public int getTaxiAzonosito() {
        return taxiAzonosito;
    }

    public String getIndulasIdopontja() {
        return indulasIdopontja;
    }

    public int getUtazasIdotartama() {
        return utazasIdotartama;
    }

    public double getMegtettTavolsag() {
        return megtettTavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public String getFizetesModja() {
        return fizetesModja;
    }

    @Override
    public String toString() {
        return "Taxi azonosítója: " + this.taxiAzonosito + ", indulás időpontja: " + this.indulasIdopontja +
                ", utazás időtartama: " + this.utazasIdotartama + ", megtett távolság: " + this.megtettTavolsag +
                "mph, viteldíj összege: $" + this.viteldij + ", borravaló: $" + this.borravalo + ", fizetés módja: " +
                this.fizetesModja;
    }
}
