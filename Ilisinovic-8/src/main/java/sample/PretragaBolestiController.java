package main.java.sample;

import hr.java.covidportal.model.Bolest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PretragaBolestiController {
    private List<Bolest> bolesti = Main.getBolesti();
    ObservableList<Bolest> bolestiZaPrikaz;

    @FXML
    private TextField nazivBolesti;

    @FXML
    private TableView<Bolest> tablica = new TableView<>();

    @FXML
    private TableColumn<Bolest,String> nazivBolTablica = new TableColumn<>("naziv bolesti");

    @FXML
    private TableColumn<Bolest, String> simptomiTablica = new TableColumn<>("simptomi bolesti");

    @FXML
    public void initialize(){
        nazivBolTablica.setCellValueFactory(new PropertyValueFactory<Bolest,String>("naziv"));

        simptomiTablica.setCellValueFactory(new PropertyValueFactory<Bolest,String>("simptomi"));

        tablica.getColumns().setAll(nazivBolTablica,simptomiTablica);

    }

    @FXML
    public void pretraziBolesti(){

        List<Bolest> trazeneBolesti = bolesti.stream()
                .filter(z -> z.getNaziv().toUpperCase().contains(nazivBolesti.getText().toUpperCase()))
                .collect(Collectors.toList());

        bolestiZaPrikaz = FXCollections.observableArrayList(trazeneBolesti);

        tablica.setItems(bolestiZaPrikaz);

    }

}
