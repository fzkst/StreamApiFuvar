package hu.fzks.fuvar;

import java.io.FileReader;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    static ArrayList<Fuvar> fuvarLista = new ArrayList<>();

    public static void fajlBeolvasas(String fajlnev) {
        try {
            FileReader fr = new FileReader(fajlnev);
            Scanner sc = new Scanner(fr);
            String elsoSor = sc.nextLine();
            System.out.println(elsoSor);
            while (sc.hasNext()) {
                String[] sor = sc.nextLine().replace(",", ".").split(";");
                Fuvar fuvar = new Fuvar(Integer.parseInt(sor[0]), sor[1], Integer.parseInt(sor[2]), Double.parseDouble(sor[3]), Double.parseDouble(sor[4]), Double.parseDouble(sor[5]), sor[6]);
                fuvarLista.add(fuvar);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void listatKiir() {
        for (Fuvar fuvar : fuvarLista) {
            System.out.println(fuvar);
        }
    }

    public static void main(String[] args) {
        fajlBeolvasas("fuvar.csv");
        System.out.println("\n" + fuvarLista.stream().count() + " számú utazás került feljegyzésre az állományban.\n");
        long fuvarSzam = fuvarLista.stream().filter(x -> x.getTaxiAzonosito() == 6185).count();
        long bevetel = fuvarLista.stream().filter(x -> x.getTaxiAzonosito() == 6185).mapToLong(x -> (long) x.getViteldij()).sum();
        long borravalo = fuvarLista.stream().filter(x -> x.getTaxiAzonosito() == 6185).mapToLong(x -> (long) x.getBorravalo()).sum();
        System.out.println("A 6185-ös azonosítójú taxisnak $" + (bevetel + borravalo) + " volt a bevétele, ami " + fuvarSzam + " fuvarból állt!\n");
        System.out.println(fuvarLista.stream().mapToDouble(x -> x.getMegtettTavolsag()).sum() + " mérföldet tettek meg a taxisok.\n");
        Fuvar leghosszabbFuvar = fuvarLista.stream().max(Comparator.comparingInt(x -> x.getUtazasIdotartama())).get();
        System.out.println("Az időben leghosszabb fuvar adatai: " + leghosszabbFuvar + "\n");
        Fuvar legBokezubbFuvar = fuvarLista.stream().min(Comparator.comparingDouble(x -> (x.getViteldij() - x.getBorravalo()))).get();
        System.out.println("A legbőkezűbb borravalójú fuvar adatai: " + legBokezubbFuvar + "\n");
        double osszesKm = fuvarLista.stream().filter(x -> x.getTaxiAzonosito() == 4261).mapToDouble(x -> x.getMegtettTavolsag()).sum() * 1.6;
        System.out.println("A 4261-es azonosítójú taxis összesen " + String.format("%.2f", osszesKm) + " km-t tett meg.\n");
        List<Fuvar> hibasAllomanyuFuvarok = fuvarLista.stream().filter(x -> x.getUtazasIdotartama() > 0 && x.getViteldij() > 0 && x.getMegtettTavolsag() == 0).toList();
        int hibasFuvarokSzama = hibasAllomanyuFuvarok.size();
        int hibasFuvarokOsszIdotartam = hibasAllomanyuFuvarok.stream().mapToInt(x -> x.getUtazasIdotartama()).sum();
        double hibasFuvarokTeljesBevetel = hibasAllomanyuFuvarok.stream().mapToDouble(x -> (x.getViteldij() + x.getBorravalo())).sum();
        System.out.println("Hibás sorok száma: " + hibasFuvarokSzama + ", hibás sorok összes időtartama: " + hibasFuvarokOsszIdotartam +
                ", hibás sorok teljes bevétele: " + hibasFuvarokTeljesBevetel + "\n");
        String szerepelE4261 = (fuvarLista.stream().allMatch(x -> x.getTaxiAzonosito() == 4261)) ? "szerepel" : "nem szerepel\n";
        System.out.println("A 1452-es számú taxi " + szerepelE4261 + " az adatok között.\n");
        Stream<Fuvar> legrovidebbHaromFuvar = fuvarLista.stream().filter(x -> x.getUtazasIdotartama() != 0).sorted(Comparator.comparingInt(x -> x.getUtazasIdotartama())).limit(3);
        System.out.println("A 3 időben legrövidebb utazás adatai: ");
        legrovidebbHaromFuvar.forEach(x -> System.out.println("\t" + x));
        int fuvarszamDec24 = (int) fuvarLista.stream().filter(x -> x.getIndulasIdopontja().contains("12-24")).count();
        System.out.println("\nDecember 24.-én " + fuvarszamDec24 + " fuvar volt.\n");
        List<Fuvar> fuvarokSzamaDec31 = fuvarLista.stream().filter(x -> x.getIndulasIdopontja().contains("12-31")).toList();
        List<Fuvar> borravalosFuvarokSzamaDec31 = fuvarLista.stream().filter(x -> x.getIndulasIdopontja().contains("12-31")).filter(x -> x.getBorravalo() != 0).toList();
        int borravaloMentesFuvarokDec31 = fuvarokSzamaDec31.size() - borravalosFuvarokSzamaDec31.size();
        System.out.println(borravaloMentesFuvarokDec31 + ":" + borravalosFuvarokSzamaDec31.size() + " arányban nem adtak borravalót az utasok December 31.-én.");
        System.out.println();

    }

}
