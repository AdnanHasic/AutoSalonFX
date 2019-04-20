package sample.dodaj_auto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.data.Auto;
import sample.data.DatabaseHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class DodajAutoController implements Initializable {

    @FXML
    TextField textFieldMarka, textFieldModel, textFieldGodiste, textFieldKilometraza;

    DatabaseHelper databaseHelper;

    public void klikNaDodajAuto() {
        Auto auto = new Auto();

        auto.setMarka(textFieldMarka.getText());
        auto.setModel(textFieldModel.getText());
        auto.setGodiste(Integer.valueOf(textFieldGodiste.getText()));
        auto.setKilometraza(Integer.valueOf(textFieldKilometraza.getText()));

        databaseHelper.createAuto(auto);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHelper = new DatabaseHelper();
        databaseHelper.createDatabase();
        databaseHelper.createAutaTable();

    }
}
