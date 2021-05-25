package main.java.sample;

import hr.java.covidportal.enumi.Vrijednost;
import hr.java.covidportal.model.Simptom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PretragaSimptomaController {
    private List<Simptom> simptomi = Main.getSimptomi();
    ObservableList<Simptom> simptomiZaPrikaz;

    @FXML
    private TextField nazivSimptoma;

    @FXML
    private TableView<Simptom> tablica = new TableView<>();

    @FXML
    private TableColumn<Simptom,String> nazivSimTablica = new TableColumn<>("naziv simptoma");

    @FXML
    private TableColumn<Simptom, Vrijednost> vrijednostSimTablica = new TableColumn<>("vrijednost simptoma");

    @FXML
    public void initialize(){
        nazivSimTablica.setCellValueFactory(new PropertyValueFactory<Simptom,String>("naziv"));

        vrijednostSimTablica.setCellValueFactory(new PropertyValueFactory<Simptom,Vrijednost>("vrijednost"));

        tablica.getColumns().setAll(nazivSimTablica,vrijednostSimTablica);

    }
    @FXML
    public void pretraziSimptome(){

        List<Simptom> trazeniSimptomi = simptomi.stream()
                .filter(z -> z.getNaziv().toUpperCase().contains(nazivSimptoma.getText().toUpperCase()))
                .collect(Collectors.toList());

        simptomiZaPrikaz = FXCollections.observableArrayList(trazeniSimptomi);

        tablica.setItems(simptomiZaPrikaz);

    }

}
