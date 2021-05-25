package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PocetniEkranController implements Initializable {

    @FXML
    static BorderPane layout = new BorderPane();
    @FXML
    private static AnchorPane prikaz = new AnchorPane();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Parent menu = FXMLLoader.load(getClass().getClassLoader().getResource("izbornik.fxml"));
            layout.setTop(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prikaz.setVisible(true);


    }

    static void setCenterPane(GridPane centerPane){
             prikaz.getChildren().setAll(centerPane);

    }


}
