package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IzbornikController implements Initializable {
    @FXML
    private Menu zupMenu;
    @FXML
    private Menu simMenu;
    @FXML
    private Menu bolMenu;
    @FXML
    private Menu virMenu;
    @FXML
    private Menu osobaMenu;
    @FXML
    private MenuItem zupPretraga;
    @FXML
    private MenuItem zupUpis;
    @FXML
    private MenuItem simPretraga;
    @FXML
    private MenuItem simUpis;
    @FXML
    private MenuItem bolPretraga;
    @FXML
    private MenuItem bolUpis;
    @FXML
    private MenuItem virPretraga;
    @FXML
    private MenuItem virUpis;
    @FXML
    private MenuItem osobaPretraga;
    @FXML
    private MenuItem osobaUpis;

    @FXML
    public void prikaziEkranZaPretraguZupanija() throws IOException {
        Parent pretragaZupanijaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaZupanija.fxml"));
        Scene pretragaZupanijaScene = new Scene(pretragaZupanijaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaZupanijaScene);

    }
    @FXML
    public void prikaziEkranZaUpisZupanija() throws IOException {
        Parent upisZupanijaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNoveZupanije.fxml"));
        Scene upisZupanijeScene = new Scene(upisZupanijaFrame, 600, 400);
        Main.getMainStage().setScene(upisZupanijeScene);

    }

    @FXML
    public void prikaziEkranZaPretraguSimptoma() throws IOException {
        Parent pretragaSimptomaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaSimptoma.fxml"));
        Scene pretragaSimptomaScene = new Scene(pretragaSimptomaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaSimptomaScene);
    }
    @FXML
    public void prikaziEkranZaUpisSimptoma() throws IOException {
        Parent upisSimptomaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNovogSimptoma.fxml"));
        Scene upisSimptomaScene = new Scene(upisSimptomaFrame, 600, 400);
        Main.getMainStage().setScene(upisSimptomaScene);
    }
    @FXML
    public void prikaziEkranZaPretraguBolesti() throws IOException {
        Parent pretragaBolestiFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaBolesti.fxml"));
        Scene pretragaBolestiScene = new Scene(pretragaBolestiFrame, 600, 400);
        Main.getMainStage().setScene(pretragaBolestiScene);
    }
    @FXML
    public void prikaziEkranZaUpisBolesti() throws IOException {
        Parent upisBolestiFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNoveBolesti.fxml"));
        Scene upisBolestiScene = new Scene(upisBolestiFrame, 600, 400);
        Main.getMainStage().setScene(upisBolestiScene);
    }
    @FXML
    public void prikaziEkranZaPretraguVirusa() throws IOException {
        Parent pretragaVirusaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaVirusa.fxml"));
        Scene pretragaVirusaScene = new Scene(pretragaVirusaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaVirusaScene);
    }
    @FXML
    public void prikaziEkranZaUpisVirusa() throws IOException {
        Parent upisVirusaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNovogVirusa.fxml"));
        Scene upisVirusaScene = new Scene(upisVirusaFrame, 600, 400);
        Main.getMainStage().setScene(upisVirusaScene);
    }
    @FXML
    public void prikaziEkranZaPretraguOsoba() throws IOException {
        Parent pretragaOsobaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaOsoba.fxml"));
        Scene pretragaOsobaScene = new Scene(pretragaOsobaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaOsobaScene);
    }
    @FXML
    public void prikaziEkranZaUpisOsoba() throws IOException {
        Parent upisOsobaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNoveOsobe.fxml"));
        Scene upisOsobaScene = new Scene(upisOsobaFrame, 600, 400);
        //2.zadatak
        upisOsobaScene.getStylesheets()
                .add(getClass().getClassLoader().getResource("red-border.css").toExternalForm());

        Main.getMainStage().setScene(upisOsobaScene);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
