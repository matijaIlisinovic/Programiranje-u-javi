package main.java.sample;

import hr.java.covidportal.model.Osoba;
import hr.java.covidportal.model.Zupanija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PretragaOsobaController {

    private List<Osoba> osobe = Main.getOsobe();
    ObservableList<Osoba> osobeZaPrikaz;

    @FXML
    private TextField imeOsobe;
    @FXML
    private TextField prezimeOsobe;

    @FXML
    private TableView<Osoba> tablica = new TableView<>();

    @FXML
    private TableColumn<Osoba,String> imeTablica = new TableColumn<>("ime");

    @FXML
    private TableColumn<Osoba,String> prezimeTablica = new TableColumn<>("prezime");

    //1.zadatak
    @FXML
    private TableColumn<Osoba, String> oibTablica = new TableColumn<>("oib");

    @FXML
    private TableColumn<Osoba,Integer> starostTablica = new TableColumn<>("starost");

    @FXML
    private TableColumn<Osoba,String> zupTablica = new TableColumn<>("županija stanovanja");

    @FXML
    private TableColumn<Osoba,String> bolestTablica = new TableColumn<>("boluje od");

    @FXML
    public void initialize(){
        imeTablica.setCellValueFactory(new PropertyValueFactory<Osoba,String>("ime"));

        prezimeTablica.setCellValueFactory(new PropertyValueFactory<Osoba,String>("prezime"));

        oibTablica.setCellValueFactory(new PropertyValueFactory<Osoba,String>("oib"));

        starostTablica.setCellValueFactory(new PropertyValueFactory<Osoba,Integer>("starost"));

        zupTablica.setCellValueFactory(new PropertyValueFactory<Osoba,String>("zupanija"));

        bolestTablica.setCellValueFactory(new PropertyValueFactory<Osoba,String>("zarazenBolescu"));

        tablica.getColumns().setAll(imeTablica,prezimeTablica,oibTablica,starostTablica,zupTablica,bolestTablica);

    }

    @FXML
    public void pretraziOsobe(){

        List<Osoba> trazeneOsobe = osobe.stream()
                .filter(z -> (z.getIme().toUpperCase().contains(imeOsobe.getText().toUpperCase())
                && z.getPrezime().toUpperCase().contains(prezimeOsobe.getText().toUpperCase())))
                .collect(Collectors.toList());

        osobeZaPrikaz = FXCollections.observableArrayList(trazeneOsobe);

        tablica.setItems(osobeZaPrikaz);

    }
}
