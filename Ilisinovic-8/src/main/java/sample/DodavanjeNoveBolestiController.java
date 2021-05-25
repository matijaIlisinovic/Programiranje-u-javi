package main.java.sample;

import hr.java.covidportal.model.Bolest;
import hr.java.covidportal.model.Simptom;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class DodavanjeNoveBolestiController implements Initializable{
    private List<Bolest> bolesti = Main.getBolesti();

    @FXML
    private TextField nazivBolesti;

    @FXML
    private ListView<Simptom> simptomiBolesti = new ListView<>();

    public void provjeriIDodajBolest(){
        if(!nazivBolesti.getText().isBlank()
                && !simptomiBolesti.getSelectionModel().getSelectedItems().isEmpty()){
            String naziv=nazivBolesti.getText();
            List<Simptom> simptomi = simptomiBolesti.getSelectionModel().getSelectedItems();
            dodajBolest(naziv,simptomi);
            ispravanUnos();
        }else{
            pogresanUnos();
        }
    }

    private void dodajBolest(String naziv, List<Simptom> simptomi) {
        Long id = bolesti.stream()
                .map(z->z.getId())
                .max(Long::compare)
                .get();
        id++;
        Set<Simptom> simp = simptomi.stream().collect(Collectors.toSet());
        bolesti.add(new Bolest(naziv,simp,id));

        File bolestiFile = new File("dat\\bolesti.txt");
        try (FileWriter bolestiFileWriter = new FileWriter(bolestiFile, true)){
            BufferedWriter bolestiWriter = new BufferedWriter(bolestiFileWriter);
            bolestiWriter.write("\n"+id.toString());
            bolestiWriter.write("\n"+naziv);
            StringBuilder simptomiID = new StringBuilder();
            for(Simptom s:simp){
                simptomiID.append(s.getId()+",");
            }
            simptomiID.deleteCharAt(simptomiID.length()-1);
            bolestiWriter.write("\n"+simptomiID);
            bolestiWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pogresanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Neuspješan unos");
        alert.setContentText("Pogreška u unosu bolesti!");
        alert.showAndWait();
    }

    private void ispravanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Uspješan unos");
        alert.setContentText("Uspješno dodana bolest!");
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.getSimptomi().stream()
                .forEach(simptom -> simptomiBolesti.getItems().add(simptom));
        simptomiBolesti.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


}
