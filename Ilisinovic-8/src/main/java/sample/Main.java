package main.java.sample;

import hr.java.covidportal.enumi.Vrijednost;
import hr.java.covidportal.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main extends Application {

    private static Stage mainStage;

    private static List<Osoba> osobe = new ArrayList<>();
    private static List<Simptom> simptomi = new ArrayList<>();
    private static List<Zupanija> zupanije = new ArrayList<>();
    private static List<Bolest> bolesti = new ArrayList<>();
    private static List<Virus> virusi = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static List<Osoba> getOsobe() {
        return osobe;
    }

    public static List<Simptom> getSimptomi() {
        return simptomi;
    }

    public static List<Zupanija> getZupanije() {
        return zupanije;
    }

    public static List<Bolest> getBolesti() {
        return bolesti;
    }

    public static List<Virus> getVirusi() {
        return virusi;
    }



    @Override
    public void start(Stage primaryStage) throws IOException {
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass()
                .getClassLoader().getResource("pocetniEkran.fxml"));

        mainStage.setTitle("ŽSBVO");
        Scene scene= new Scene(root, 600, 400);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {

        logger.info("Početak aplikacije");

        logger.info("Učitavanje županija");

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

        logger.info("Učitavanje simptoma");

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

        logger.info("Učitavanje bolesti");

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

        logger.info("Učitavanje virusa");

        File virusiFile = new File("dat\\virusi.txt");
        try (FileReader virFileReader = new FileReader(virusiFile)){
            BufferedReader virusiReader = new BufferedReader(virFileReader);
            String procitanaLinija;
            while((procitanaLinija = virusiReader.readLine()) != null){
                Long virId = Long.parseLong(procitanaLinija);
                String nazivVir = virusiReader.readLine();
                Set<Simptom> simptomiVir=new HashSet<>();
                String simptomiBolesti = virusiReader.readLine();
                for(String string : simptomiBolesti.split(",")){
                    simptomiVir.add(simptomi.get(Integer.parseInt(string)-1));
                }
                virusi.add(new Virus(nazivVir,simptomiVir,virId));
            }
        } catch (IOException e) {
            logger.error("pogreška u radu s datotekom",e);
        }

        logger.info("Učitavanje osoba");

        File osobeFile = new File("dat\\osobe.txt");
        try (FileReader osobeFileReader = new FileReader(osobeFile)){
            BufferedReader osobeReader = new BufferedReader(osobeFileReader);
            String procitanaLinija;
            while((procitanaLinija = osobeReader.readLine()) != null){
                String imeOsoba = procitanaLinija;
                String prezimeOsoba = osobeReader.readLine();
                String oib = osobeReader.readLine();
                Integer starostOsoba = Integer.parseInt(osobeReader.readLine());
                Zupanija zupanijaOsoba = zupanije.get(Integer.parseInt(osobeReader.readLine())-1);
                Integer brojBolesti = Integer.parseInt(osobeReader.readLine());
                Bolest bolestOsoba = null;
                if(brojBolesti>bolesti.size()){
                    brojBolesti= brojBolesti-bolesti.size();
                    bolestOsoba = virusi.get(brojBolesti-1);
                }else{
                    bolestOsoba = bolesti.get(brojBolesti-1);
                }
                List<Osoba> kontakti=new ArrayList<>();
                if(osobeReader.readLine().equals("da")){
                    String kontaktiPopis = osobeReader.readLine();
                    for(String string : kontaktiPopis.split(",")){
                        kontakti.add(osobe.get(Integer.parseInt(string)-1));
                    }
                    osobe.add(new Osoba.Builder().sImenom(imeOsoba)
                            .sPrezimenom(prezimeOsoba)
                            .sOibom(oib)
                            .stara(starostOsoba)
                            .uZupaniji(zupanijaOsoba)
                            .zarazenaBolescu(bolestOsoba)
                            .jeKontaktirala(kontakti)
                            .build());
                }else{
                    osobe.add(new Osoba.Builder().sImenom(imeOsoba)
                            .sPrezimenom(prezimeOsoba)
                            .sOibom(oib)
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

        launch(args);
    }
}
