package sample.home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.data.Auto;
import sample.data.DatabaseHelper;
import sample.sva_auta.CelijaAuta;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    Button buttonDodajAuto;

    @FXML
    ListView<Auto>listViewAuta;



    public void nazadNaLogin (ActionEvent event ) throws IOException {
        URL url = new File("src/sample/login/login.fxml").toURL();
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    public void postavkeZaUsera () {
        buttonDodajAuto.setManaged(false);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.createDatabase();
        databaseHelper.createAutaTable();
        List<Auto> auta = databaseHelper.vratiSvaAuta();
       ObservableList<Auto> listaAuta = FXCollections.observableArrayList(auta);

        listViewAuta.setItems(listaAuta);
        listViewAuta.setCellFactory(new Callback<ListView<Auto>, ListCell<Auto>>() {
            @Override
            public ListCell<Auto> call(ListView<Auto> param) {
                return new CelijaAuta();
            }
        });

        buttonDodajAuto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try { URL url = new File("src/sample/dodaj_auto/dodaj_auto.fxml").toURL();
                    Parent parent = FXMLLoader.load(url);
                    Scene scene = new Scene(parent);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e){

                }

        }
        });
    }
}
