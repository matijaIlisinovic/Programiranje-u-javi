package main.java.sample;

import hr.java.covidportal.model.Virus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PretragaVirusaController {

        private List<Virus> virusi = Main.getVirusi();
        ObservableList<Virus> virusiZaPrikaz;

        @FXML
        private TextField nazivVirusa;

        @FXML
        private TableView<Virus> tablica = new TableView<>();

        @FXML
        private TableColumn<Virus,String> nazivVirTablica = new TableColumn<>("naziv virusa");

        @FXML
        private TableColumn<Virus, String> simptomiTablica = new TableColumn<>("simptomi virusa");

        @FXML
        public void initialize(){
            nazivVirTablica.setCellValueFactory(new PropertyValueFactory<Virus,String>("naziv"));

            simptomiTablica.setCellValueFactory(new PropertyValueFactory<Virus,String>("simptomi"));

            tablica.getColumns().setAll(nazivVirTablica,simptomiTablica);

        }

        @FXML
        public void pretraziViruse() {

            List<Virus> trazeniVirusi = virusi.stream()
                    .filter(z -> z.getNaziv().toUpperCase().contains(nazivVirusa.getText().toUpperCase()))
                    .collect(Collectors.toList());

            virusiZaPrikaz = FXCollections.observableArrayList(trazeniVirusi);

            tablica.setItems(virusiZaPrikaz);

        }
}
