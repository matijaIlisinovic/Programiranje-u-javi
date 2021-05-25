package main.java.sample;

import hr.java.covidportal.model.Zupanija;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DodavanjeNoveZupanijeController {
    private List<Zupanija> zupanije = Main.getZupanije();

    @FXML
    private TextField nazivZupanije;
    @FXML
    private TextField brojStanovnika;
    @FXML
    private TextField brojZarazenih;


    public void provjeriIDodajZupaniju(){
        if(!nazivZupanije.getText().isBlank()
        && !brojStanovnika.getText().isBlank()
        && !brojZarazenih.getText().isBlank()){
            String naziv;
            Integer brojStan;
            Integer brojZar;
            try{
                naziv = nazivZupanije.getText();
                brojStan = Integer.parseInt(brojStanovnika.getText());
                brojZar = Integer.parseInt(brojZarazenih.getText());
                dodajZupaniju(naziv,brojStan,brojZar);
                ispravanUnos();
            }catch(NumberFormatException e){
                e.printStackTrace();
                pogresanUnos();
                return;
            }

        }else{
            pogresanUnos();
        }
    }

    private void pogresanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Neuspješan unos");
        alert.setContentText("Pogreška u unosu županije!");
        alert.showAndWait();
    }

    private void ispravanUnos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Uspješan unos");
        alert.setContentText("Uspješno dodana županija!");
        alert.showAndWait();
    }

    public void dodajZupaniju(String nazivZup,Integer brojStan,Integer brojZar){

        Long id = zupanije.stream()
                .map(z->z.getId())
                .max(Long::compare)
                .get();
        id++;
        zupanije.add(new Zupanija(nazivZup,brojStan,brojZar,id));

        File zupanijeFile = new File("dat\\zupanije.txt");
        try (FileWriter zupanijeFileWriter = new FileWriter(zupanijeFile, true)){
            BufferedWriter zupanijeWriter = new BufferedWriter(zupanijeFileWriter);
            zupanijeWriter.write("\n"+id.toString());
            zupanijeWriter.write("\n"+nazivZup);
            zupanijeWriter.write("\n"+brojStan.toString());
            zupanijeWriter.write("\n"+brojZar.toString());

            zupanijeWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
