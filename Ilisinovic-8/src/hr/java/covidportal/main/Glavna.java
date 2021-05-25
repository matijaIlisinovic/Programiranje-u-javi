package hr.java.covidportal.main;

import hr.java.covidportal.enumi.Vrijednost;
import hr.java.covidportal.genericsi.KlinikaZaInfektivneBolesti;
import hr.java.covidportal.model.*;
import hr.java.covidportal.sort.CovidSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * provodi aplikaciju te su u njoj inicijalizirani podatci
 * sadrzi liste podataka, logger, main i pomocne metode
 *
 */

public class Glavna {

    private static List<Osoba> osobe = new ArrayList<>();
    private static List<Simptom> simptomi = new ArrayList<>();
    private static List<Zupanija> zupanije = new ArrayList<>();
    private static List<Bolest> bolesti = new ArrayList<>();
    private static Map<Bolest,List<Osoba>> mapaRazboljenih = new HashMap<>();
    private static KlinikaZaInfektivneBolesti<Virus,Osoba> klinika =
                new KlinikaZaInfektivneBolesti<>(new ArrayList<Virus>(),new ArrayList<Osoba>());

    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    /**
     * provodi petlje upisa podataka i ispisuje popis osoba
     *
      * @param args argumenti komandne linije, ne koriste se
     */
    public static void main(String[] args) {

        logger.info("pocetak programa ");

        Scanner ulaz = new Scanner(System.in);

        logger.info("početak učitavanja podataka");
        System.out.println("Učitavanje županija");

        File zupanijeFile = new File("dat\\zupanije.txt");
        try (FileReader zupFileReader = new FileReader(zupanijeFile)){
            BufferedReader zupanijeReader = new BufferedReader(zupFileReader);
            String procitanaLinija;
            while((procitanaLinija = zupanijeReader.readLine()) != null){
                Long zupId = Long.parseLong(procitanaLinija);
                String nazivZup = zupanijeReader.readLine();
                Integer brojStanovnikaZup = Integer.parseInt(zupanijeReader.readLine());
                Integer brojZarazenihStanovnikaZup = Integer.parseInt(zupanijeReader.readLine());
                zupanije.add(new Zupanija(nazivZup,brojStanovnikaZup,brojZarazenihStanovnikaZup,zupId));
            }
        } catch (IOException e) {
            logger.info("pogreška u radu s datotekom",e);
        }

        System.out.println("Učitavanje simptoma");

        File simptomiFile = new File("dat\\simptomi.txt");
        try (FileReader simFileReader = new FileReader(simptomiFile)){
            BufferedReader simptomiReader = new BufferedReader(simFileReader);
            String procitanaLinija;
            while((procitanaLinija = simptomiReader.readLine()) != null){
                Long simId = Long.parseLong(procitanaLinija);
                String nazivSim = simptomiReader.readLine();
                String vrijednostSimptoma = simptomiReader.readLine();
                Vrijednost vrijednostSim=null;
                if(vrijednostSimptoma.equals("RIJETKO"))
                    vrijednostSim=Vrijednost.RIJETKO;
                if(vrijednostSimptoma.equals("SREDNJE"))
                    vrijednostSim=Vrijednost.SREDNJE;
                if(vrijednostSimptoma.equals("CESTO"))
                    vrijednostSim=Vrijednost.CESTO;
                simptomi.add(new Simptom(nazivSim,vrijednostSim,simId));
            }
        } catch (IOException e) {
            logger.info("pogreška u radu s datotekom",e);
        }

        System.out.println("Učitavanje bolesti");

        File bolestiFile = new File("dat\\bolesti.txt");
        try (FileReader bolFileReader = new FileReader(bolestiFile)){
            BufferedReader bolestiReader = new BufferedReader(bolFileReader);
            String procitanaLinija;
            while((procitanaLinija = bolestiReader.readLine()) != null){
                Long bolId = Long.parseLong(procitanaLinija);
                String nazivBol = bolestiReader.readLine();
                Set<Simptom> simptomiBol=new HashSet<>();
                String simptomiBolesti = bolestiReader.readLine();
                for(String string : simptomiBolesti.split(",")){
                    simptomiBol.add(simptomi.get(Integer.parseInt(string)-1));
                }
                bolesti.add(new Bolest(nazivBol,simptomiBol,bolId));
            }
        } catch (IOException e) {
            logger.error("pogreška u radu s datotekom",e);
        }

        Integer brojBolesti = bolesti.size();
        System.out.println("Učitavanje virusa");

        File virusiFile = new File("dat\\virusi.txt");
        try (FileReader virFileReader = new FileReader(virusiFile)){
            BufferedReader virusiReader = new BufferedReader(virFileReader);
            String procitanaLinija;
            while((procitanaLinija = virusiReader.readLine()) != null){
                Long virId = Long.parseLong(procitanaLinija)+brojBolesti;
                String nazivVir = virusiReader.readLine();
                Set<Simptom> simptomiVir=new HashSet<>();
                String simptomiBolesti = virusiReader.readLine();
                for(String string : simptomiBolesti.split(",")){
                    simptomiVir.add(simptomi.get(Integer.parseInt(string)-1));
                }
                bolesti.add(new Virus(nazivVir,simptomiVir,virId));
            }
        } catch (IOException e) {
            logger.error("pogreška u radu s datotekom",e);
        }

        System.out.println("Učitavanje osoba");

        File osobeFile = new File("dat\\osobe.txt");
        try (FileReader osobeFileReader = new FileReader(osobeFile)){
            BufferedReader osobeReader = new BufferedReader(osobeFileReader);
            String procitanaLinija;
            while((procitanaLinija = osobeReader.readLine()) != null){
                String imeOsoba = procitanaLinija;
                String prezimeOsoba = osobeReader.readLine();
                Integer starostOsoba = Integer.parseInt(osobeReader.readLine());
                Zupanija zupanijaOsoba = zupanije.get(Integer.parseInt(osobeReader.readLine())-1);
                Bolest bolestOsoba = bolesti.get(Integer.parseInt(osobeReader.readLine())-1);
                List<Osoba> kontakti=new ArrayList<>();
                if(osobeReader.readLine().equals("da")){
                    String kontaktiPopis = osobeReader.readLine();
                    for(String string : kontaktiPopis.split(",")){
                        kontakti.add(osobe.get(Integer.parseInt(string)-1));
                    }
                    osobe.add(new Osoba.Builder().sImenom(imeOsoba)
                            .sPrezimenom(prezimeOsoba)
                            .stara(starostOsoba)
                            .uZupaniji(zupanijaOsoba)
                            .zarazenaBolescu(bolestOsoba)
                            .jeKontaktirala(kontakti)
                            .build());
                }else{
                    osobe.add(new Osoba.Builder().sImenom(imeOsoba)
                            .sPrezimenom(prezimeOsoba)
                            .stara(starostOsoba)
                            .uZupaniji(zupanijaOsoba)
                            .zarazenaBolescu(bolestOsoba)
                            .build());
                }
            }
        } catch (IOException e) {
            logger.error("pogreška u radu s datotekom",e);
        }

        logger.info("Završen upis podataka");
        List<Zupanija> odabraneZupanije = zupanije.stream()
                .filter(zupanija -> ((zupanija.getBrojZarazenih()*100)/zupanija.getBrojStanovnika()>2))
                .collect(Collectors.toList());
        try(ObjectOutputStream serializatorZupanija = new ObjectOutputStream(
                new FileOutputStream("dat\\serijaliziraneZupanije.data"))){
            serializatorZupanija.writeObject(odabraneZupanije);
        }catch (IOException e){
            logger.error("pogreška pri serijalizaciji",e);
        }
        mapaRazboljenih = osobe.stream().collect(groupingBy(Osoba::getZarazenBolescu));

        logger.info("ispis osoba...");
        System.out.println("Popis osoba:");
        for (Osoba osoba : osobe){
            System.out.println("Ime: "+osoba.getIme()+
                    "\nPrezime: " + osoba.getPrezime()+
                    "\nŽupanija prebivališta: " + osoba.getZupanija().getNaziv()+
                    "\nZaražen bolešću: "+ osoba.getZarazenBolescu().getNaziv()+
                    "\nKontaktirane osobe: ");
            if(osoba.getKontaktiraneOsobe()==null){
                System.out.println("Nema kontaktiranih osoba");
            }else{
                for(Osoba kontakt : osoba.getKontaktiraneOsobe()){
                    System.out.println(kontakt.getIme() + " "+
                            kontakt.getPrezime());
                }
            }
        }

        System.out.println("razboljeni sortirani po bolestima: ");
        for(Bolest bolest: mapaRazboljenih.keySet()){
            System.out.println(bolest.getNaziv() + ":");
            for(Osoba osoba: mapaRazboljenih.get(bolest)){
                System.out.println(osoba.getIme()+" "+osoba.getPrezime());
            }
        }

        System.out.print("Zupanija s najvise posto razboljenih: ");
        Zupanija zupanija = zupanije.stream().
                max((a,b)->new CovidSorter().compare(a,b))
                .get();
        System.out.println(zupanija.getNaziv()+
                " (" + zupanija.getBrojZarazenih()*100/zupanija.getBrojStanovnika()+"%)");

        bolesti.stream().filter(bolest -> bolest instanceof Virus)
                .forEach(virus -> klinika.dodajNoviVirus((Virus) virus));

        osobe.stream().filter(osoba -> osoba.getZarazenBolescu() instanceof Virus)
                .forEach(osoba -> klinika.dodajZarazenuOsobu(osoba));

        Instant start = Instant.now();
        klinika.getPoznatiVirusi().stream()
                .sorted(Comparator.comparing(Virus::getNaziv).reversed());
        Instant end = Instant.now();
        System.out.println("Trajanje sortiranja s lambdama: " + Duration.between(start,end).toMillis());

        start=Instant.now();
        Collections.sort(klinika.getPoznatiVirusi(), new Comparator<Virus>() {
            @Override
            public int compare(Virus o1, Virus o2) {
                return o2.getNaziv().compareTo(o1.getNaziv());
            }
        });
        end=Instant.now();
        System.out.println("Trajanje sortiranja bez lambdi: " + Duration.between(start,end).toMillis());

        System.out.println("Virusi u klinici: ");
        klinika.getPoznatiVirusi().stream().forEach(System.out::println);

        System.out.println("Unesite string za pretragu prezimena : ");
        String filter = ulaz.nextLine();
        Optional<List<Osoba>> odabraneOsobe = Optional.of(osobe.stream()
                .filter((Osoba osoba) -> osoba.getPrezime().contains(filter))
                .collect(Collectors.toList()));

        System.out.println("Osobe čije prezime sadrži "+filter+ " su:");
        if(odabraneOsobe.isPresent()&& !odabraneOsobe.isEmpty()){
            odabraneOsobe.get().stream().forEach(System.out::println);
        }else {
            System.out.println("nema osoba koje odgovaraju kriteriju");
        }
        System.out.println("");
        bolesti.stream()
                .map(bolest-> bolest.getNaziv().toUpperCase()+
                        " ima "+bolest.getSimptomi().size()+" simptoma")
                .forEach(System.out::println);

        logger.info("završetak programa ");
    }

}