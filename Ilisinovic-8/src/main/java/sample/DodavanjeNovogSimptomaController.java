package main.java.sample;

import hr.java.covidportal.enumi.Vrijednost;
import hr.java.covidportal.model.Simptom;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DodavanjeNovogSimptomaController implements Initializable {
    private List<Simptom> simptomi = Main.getSimptomi();

    @FXML
    private TextField nazivSimptoma;
    @FXML
    private ChoiceBox vrijednostSimptoma = new ChoiceBox();

    public void provjeriIDodajSimptom(){
        if(!nazivSimptoma.getText().isBlank() && vrijednostSimptoma.getValue()!=null){
            String naziv;
            Vrijednost vrijednost;

            naziv = nazivSimptoma.getText();
            vrijednost=(Vrijednost) vrijednostSimptoma.getValue();
            dodajSimptom(naziv,vrijednost);
            ispravanUnos();


        }else{
            pogresanUnos();
        }
    }

    private void dodajSimptom(String naziv, Vrijednost vrijednost) {
        Long id = simptomi.stream()
                .map(z->z.getId())
                .max(Long::compare)
                .get();
        id++;
        simptomi.add(new Simptom(naziv,vrijednost,id));
        File simptomiFile = new File("dat\\simptomi.txt");
        try (FileWriter simptomiFileWriter = new FileWriter(simptomiFile, true)){
            BufferedWriter simptomiWriter = new BufferedWriter(simptomiFileWriter);
            simptomiWriter.write("\n"+id.toString());
            simptomiWriter.write("\n"+naziv);
            simptomiWriter.write("\n"+vrijednost);

            simptomiWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pogresanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Neuspješan unos");
        alert.setContentText("Pogreška u unosu simptoma!");
        alert.showAndWait();
    }

    private void ispravanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Uspješan unos");
        alert.setContentText("Uspješno dodan simptom!");
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vrijednostSimptoma.getItems()
                .addAll(Vrijednost.RIJETKO,Vrijednost.SREDNJE,Vrijednost.CESTO);
    }


}
