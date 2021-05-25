package main.java.sample;

import hr.java.covidportal.model.Zupanija;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PretragaZupanijaController {

    private List<Zupanija> zupanije = Main.getZupanije();
    ObservableList<Zupanija> zupanijeZaPrikaz;

    @FXML
    private TextField nazivZupanije;

    @FXML
    private TableView<Zupanija> tablica = new TableView<>();

    @FXML
    private TableColumn<Zupanija,String> nazivZupTablica = new TableColumn<>("Naziv županije");

    @FXML
    private TableColumn<Zupanija,Integer> brojStanTablica = new TableColumn<>("broj stanovnika");

    @FXML
    private TableColumn<Zupanija,Integer> brojZarTablica = new TableColumn<>("broj zaraženih");


    @FXML
    public void initialize(){
        nazivZupTablica.setCellValueFactory(new PropertyValueFactory<Zupanija,String>("naziv"));

        brojStanTablica.setCellValueFactory(new PropertyValueFactory<Zupanija,Integer>("brojStanovnika"));

        brojZarTablica.setCellValueFactory(new PropertyValueFactory<Zupanija,Integer>("brojZarazenih"));
        tablica.getColumns().setAll(nazivZupTablica,brojStanTablica,brojZarTablica);

    }
    @FXML
    public void pretraziZupanije(){

        List<Zupanija> trazeneZupanije = zupanije.stream()
                .filter(z -> z.getNaziv().toUpperCase().contains(nazivZupanije.getText().toUpperCase()))
                .collect(Collectors.toList());

        zupanijeZaPrikaz = FXCollections.observableArrayList(trazeneZupanije);

        tablica.setItems(zupanijeZaPrikaz);

    }
}
