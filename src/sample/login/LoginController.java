package sample.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.data.DatabaseHelper;
import sample.data.User;
import sample.home.HomeController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    TextField textFieldUsername, textFieldPassword;
    DatabaseHelper databaseHelper;
    @FXML
    Label labelUpozorenje;

    public void klikNaNapraviProfil (ActionEvent event) throws IOException {
        URL url = new File("src/sample/pravljenje_profila/pravljenje_profila.fxml").toURL();
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public void klikNaLogin(ActionEvent event) throws IOException {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        User user = databaseHelper.postojiLiSaOvimUsernameomIPasswordom(username, password);
        if (user == null) {
            labelUpozorenje.setVisible(true);
            return;
        }
        labelUpozorenje.setVisible(false);

        URL url = new File("src/sample/home/home.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.load();
        if (!textFieldUsername.getText().equals("admin")) {
            HomeController homeController = fxmlLoader.getController();
            homeController.postavkeZaUsera();
        }

        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHelper = new DatabaseHelper();
        databaseHelper.createDatabase();
        databaseHelper.createTable();

    }
}
