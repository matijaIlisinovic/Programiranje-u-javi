package main.java.sample;

import hr.java.covidportal.model.Bolest;
import hr.java.covidportal.model.Osoba;
import hr.java.covidportal.model.Virus;
import hr.java.covidportal.model.Zupanija;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DodavanjeNoveOsobeController implements Initializable {
    private List<Osoba> osobe = Main.getOsobe();

    @FXML
    private TextField imeOsobe;
    @FXML
    private TextField prezimeOsobe;
    //1.zadatak
    @FXML
    private TextField oibOsobe;
    @FXML
    private TextField starostOsobe;

    @FXML
    private CheckBox dodavanjeKontakata;

    @FXML
    private ListView<Zupanija> zupanijaOsoba = new ListView<>();
    @FXML
    private ListView<Bolest> bolestOsoba = new ListView<>();
    @FXML
    private ListView<Osoba> kontaktiOsoba = new ListView<>();
    //1. zadatak
    public void provjeriIDodajOsobu(){
        osvjezi();
        if(!imeOsobe.getText().isBlank()
                && !prezimeOsobe.getText().isBlank()
                && !oibOsobe.getText().isBlank()
                && !starostOsobe.getText().isBlank()
                && !zupanijaOsoba.getSelectionModel().getSelectedItems().isEmpty()
                && !bolestOsoba.getSelectionModel().getSelectedItems().isEmpty()){
            String ime = imeOsobe.getText();
            String prezime = prezimeOsobe.getText();
            String oib = oibOsobe.getText();
            Integer starost;
            try {
                starost = Integer.parseInt(starostOsobe.getText());
            }catch (NumberFormatException e){
                pogresanUnos();
                starostOsobe.getStyleClass().add("error");
                return;
            }
            Zupanija zupanija = zupanijaOsoba.getSelectionModel().getSelectedItem();
            Bolest bolest = bolestOsoba.getSelectionModel().getSelectedItem();

            if(dodavanjeKontakata.isSelected()&&!kontaktiOsoba.getSelectionModel().getSelectedItems().isEmpty()){
                List<Osoba> kontakti = kontaktiOsoba.getSelectionModel().getSelectedItems();
                dodajOsobu(ime,prezime,starost,zupanija,bolest,kontakti,oib);
                ispravanUnos();
                return;
            }else{
                if(dodavanjeKontakata.isSelected()){
                        pogresanUnos();
                        return;
                }else{
                        dodajOsobu(ime,prezime,starost,zupanija,bolest,oib);
                        ispravanUnos();
                }
            }
        } else{
            pogresanUnos();
            //2.zadatak
            if(imeOsobe.getText().isBlank()){
                imeOsobe.getStyleClass().add("error");
            }
            if(prezimeOsobe.getText().isBlank()){
                prezimeOsobe.getStyleClass().add("error");

            }
            if(oibOsobe.getText().isBlank()){
                oibOsobe.getStyleClass().add("error");

            }
            if(starostOsobe.getText().isBlank()){
                starostOsobe.getStyleClass().add("error");

            }

        }
    }

    private void osvjezi() {
        imeOsobe.getStyleClass().remove("error");
        prezimeOsobe.getStyleClass().remove("error");
        starostOsobe.getStyleClass().remove("error");
        oibOsobe.getStyleClass().remove("error");

    }

    private void dodajOsobu(String ime, String prezime, Integer starost,
                            Zupanija zupanija, Bolest bolest,
                            List<Osoba> kontakti,String oib) {
        osobe.add(new Osoba.Builder()
                .sImenom(ime)
                .sPrezimenom(prezime)
                .sOibom(oib)
                .stara(starost)
                .uZupaniji(zupanija)
                .zarazenaBolescu(bolest)
                .jeKontaktirala(kontakti)
                .build());

        File osobeFile = new File("dat\\osobe.txt");
        try (FileWriter osobeFileWriter = new FileWriter(osobeFile, true)) {
            BufferedWriter osobeWriter = new BufferedWriter(osobeFileWriter);
            osobeWriter.write("\n"+ime);
            osobeWriter.write("\n"+prezime);
            osobeWriter.write("\n"+oib);
            osobeWriter.write("\n"+starost.toString());
            osobeWriter.write("\n"+zupanija.getId().toString());
            Long id;
            if(bolest instanceof Virus){
                id = bolest.getId()+Main.getBolesti().size();
            }else {
                id = bolest.getId();
            }
            osobeWriter.write("\n"+ id.toString());
            osobeWriter.write("\nda");
            StringBuilder indeksiKontakata=new StringBuilder();
            for(Osoba o:kontakti){
                int i = osobe.indexOf(o)+1;
                indeksiKontakata.append(i+",");
            }
            indeksiKontakata.deleteCharAt(indeksiKontakata.length()-1);
            osobeWriter.write("\n"+indeksiKontakata);
            osobeWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void dodajOsobu(String ime, String prezime, Integer starost,
                            Zupanija zupanija, Bolest bolest,String oib) {
        osobe.add(new Osoba.Builder()
                .sImenom(ime)
                .sPrezimenom(prezime)
                .sOibom(oib)
                .stara(starost)
                .uZupaniji(zupanija)
                .zarazenaBolescu(bolest)
                .build());

        File osobeFile = new File("dat\\osobe.txt");
        try (FileWriter osobeFileWriter = new FileWriter(osobeFile, true)) {
            BufferedWriter osobeWriter = new BufferedWriter(osobeFileWriter);
            osobeWriter.write("\n"+ime);
            osobeWriter.write("\n"+prezime);
            osobeWriter.write("\n"+oib);
            osobeWriter.write("\n"+starost.toString());
            osobeWriter.write("\n"+zupanija.getId().toString());
            Long id;
            if(bolest instanceof Virus){
                id = bolest.getId()+Main.getBolesti().size();
            }else {
                id = bolest.getId();
            }
            osobeWriter.write("\n"+ id.toString());
            osobeWriter.write("\nne");
            osobeWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void pogresanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Neuspješan unos");
        alert.setContentText("Pogreška u unosu osobe!");
        alert.showAndWait();
    }

    private void ispravanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Uspješan unos");
        alert.setContentText("Uspješno dodana osoba!");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        osobe.stream().forEach(osoba -> kontaktiOsoba.getItems().add(osoba));
        kontaktiOsoba.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Main.getBolesti().stream()
                .forEach(bolest -> bolestOsoba.getItems().add(bolest));
        Main.getVirusi().stream()
                .forEach(virus -> bolestOsoba.getItems().add(virus));
        bolestOsoba.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Main.getZupanije().stream()
                .forEach(zupanija -> zupanijaOsoba.getItems().add(zupanija));
        zupanijaOsoba.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
