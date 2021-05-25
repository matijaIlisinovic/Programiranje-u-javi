package main.java.sample;

import hr.java.covidportal.model.Simptom;
import hr.java.covidportal.model.Virus;
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

public class DodavanjeNovogVirusaController implements Initializable {
    private List<Virus> virusi = Main.getVirusi();

    @FXML
    private TextField nazivVirusa;

    @FXML
    private ListView<Simptom> simptomiVirusa = new ListView<>();

    public void provjeriIDodajVirus(){
        if(!nazivVirusa.getText().isBlank()
                && !simptomiVirusa.getSelectionModel().getSelectedItems().isEmpty()){
            String naziv= nazivVirusa.getText();
            List<Simptom> simptomi = simptomiVirusa.getSelectionModel().getSelectedItems();
            dodajVirus(naziv,simptomi);
            ispravanUnos();
        }else{
            pogresanUnos();
        }
    }

    private void dodajVirus(String naziv, List<Simptom> simptomi) {
        Long id = virusi.stream()
                .map(z->z.getId())
                .max(Long::compare)
                .get();
        id++;
        Set<Simptom> simp = simptomi.stream().collect(Collectors.toSet());
        virusi.add(new Virus(naziv,simp,id));

        File virusiFile = new File("dat\\virusi.txt");
        try (FileWriter virusiFileWriter = new FileWriter(virusiFile, true)){
            BufferedWriter virusiWriter = new BufferedWriter(virusiFileWriter);
            virusiWriter.write("\n"+id.toString());
            virusiWriter.write("\n"+naziv);
            StringBuilder simptomiID = new StringBuilder();
            for(Simptom s:simp){
                simptomiID.append(s.getId()+",");
            }
            simptomiID.deleteCharAt(simptomiID.length()-1);
            virusiWriter.write("\n"+simptomiID);

            virusiWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pogresanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Neuspješan unos");
        alert.setContentText("Pogreška u unosu virusa!");
        alert.showAndWait();
    }

    private void ispravanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Uspješan unos");
        alert.setContentText("Uspješno dodan virus!");
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.getSimptomi().stream()
                .forEach(simptom -> simptomiVirusa.getItems().add(simptom));
        simptomiVirusa.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}
