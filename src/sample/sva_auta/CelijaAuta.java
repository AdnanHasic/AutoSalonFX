package sample.sva_auta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import sample.data.Auto;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CelijaAuta extends ListCell<Auto> {

    @FXML
    Label labelMarka, labelModel, labelGodiste, labelKilometraza;

    @FXML
    HBox hBox;

    FXMLLoader fxmlLoader;


    @Override
    protected void updateItem(Auto auto, boolean empty) {
        super.updateItem(auto, empty);

        if (auto == null || empty) {
            setText(null);
            setGraphic(null);
            return;
        }

        if (fxmlLoader == null) {

         try {
             URL url  = new File("src/sample/sva_auta/celija_auta.fxml").toURL();
             fxmlLoader = new FXMLLoader(url);
             fxmlLoader.setController(this);
             fxmlLoader.load();

         } catch (IOException e) {

         }

        }
        labelMarka.setText(auto.getMarka());
        labelModel.setText(auto.getModel());
        labelGodiste.setText(String.valueOf(auto.getGodiste()));
        labelKilometraza.setText(String.valueOf(auto.getKilometraza()));

       setText(null);
       setGraphic(hBox);

    }
}
