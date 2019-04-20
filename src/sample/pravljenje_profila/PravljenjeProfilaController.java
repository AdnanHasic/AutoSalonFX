package sample.pravljenje_profila;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sample.data.DatabaseHelper;
import sample.data.User;

import javax.xml.soap.Text;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PravljenjeProfilaController implements Initializable {

    @FXML
    TextField textFieldIme;

    @FXML
    TextField textFieldPrezime;

    @FXML
    TextField textFieldEmail;

    @FXML
    TextField textFieldUsername;

    @FXML
    TextField textFieldPassword;

    @FXML
    TextField textFieldPonoviPassword;

    @FXML
    Label labelIme;

    @FXML
    Label labelPrezime;

    @FXML
    Label labelEmail;

    @FXML
    Label labelUsername;

    @FXML
    Label LabelPassword;

    @FXML
    Label LabelPonoviPassword;
    DatabaseHelper databaseHelper;

    public void klikNaNapraviProfil (ActionEvent event) throws IOException {
       boolean postojiPraznoPolje = provjeriJesuLiPraznaPolja();
       if (postojiPraznoPolje) {
           return;
       }

       boolean usernameZauzet = provjeriJeLIUsernameZauzet();
       if (usernameZauzet) {
           return;
       }
       boolean jesuLiPasswordiIsti = provjeriJesuLiPasswordiIsti();
       if (jesuLiPasswordiIsti){
           return;
       }
       dodajUseraUBazu();

        URL url = new File("src/sample/login/login.fxml").toURL();
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void nazadNaLogin (ActionEvent event ) throws IOException {
        URL url = new File("src/sample/login/login.fxml").toURL();
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void dodajUseraUBazu () {
        User user = new User ();
        user.setIme(textFieldIme.getText());
        user.setPrezime(textFieldPrezime.getText());
        user.setEmail(textFieldEmail.getText());
        user.setUsername(textFieldUsername.getText());
        user.setPassword(textFieldPassword.getText());

        databaseHelper.createUser(user);
    }


    private boolean provjeriJesuLiPasswordiIsti() {
        if
         (!textFieldPassword.getText().equals(textFieldPonoviPassword.getText())) {
             LabelPassword.setTextFill(Color.web("#ff0000"));
             LabelPonoviPassword.setTextFill(Color.web("#ff0000"));
             return true;
         } else {
             LabelPassword.setTextFill(Color.web("#000000"));
             LabelPonoviPassword.setTextFill(Color.web("#000000"));
             return false;
         }
    }

    private boolean provjeriJeLIUsernameZauzet() {
        User user = databaseHelper.postojiLiSaOvimUsernameom(textFieldUsername.getText());
        if (user != null) {
            labelUsername.setTextFill(Paint.valueOf("#ff0000"));
            return true;
        }
        labelUsername.setTextFill(Paint.valueOf("#000000"));
        return  false;
    }

    private boolean obojiPolje (TextField textField, Label label) {
        if (textField.getText().isEmpty()) {
            label.setTextFill (Paint.valueOf("#ff0000"));
            return true;
        }
        else {
            label.setTextFill (Paint.valueOf("#000000"));
            return false;

        }
    }
    private boolean provjeriJesuLiPraznaPolja() {

       /* if (textFieldIme.getText().isEmpty()) {
            labelIme.setTextFill(Paint.valueOf("#ff0000"));
        } else {
            labelIme.setTextFill(Paint.valueOf("#000000"));
        }

        if (textFieldPrezime.getText().isEmpty()) {
            labelPrezime.setTextFill(Paint.valueOf("#ff0000"));
        } else {
            labelPrezime.setTextFill(Paint.valueOf("#000000"));
        }


        if (textFieldEmail.getText().isEmpty()) {
            labelEmail.setTextFill(Paint.valueOf("#ff0000"));
        } else {
            labelEmail.setTextFill(Paint.valueOf("#000000"));
        }

        if (textFieldUsername.getText().isEmpty()) {
            labelUsername.setTextFill(Paint.valueOf("#ff0000"));
        } else {
            labelUsername.setTextFill(Paint.valueOf("#000000"));
        }*/
        boolean imePrazno = obojiPolje(textFieldIme, labelIme);
        boolean prezimePrazno = obojiPolje(textFieldPrezime, labelPrezime);
        boolean emailPrazno = obojiPolje(textFieldEmail, labelEmail);
        boolean usernamePrazno = obojiPolje(textFieldUsername, labelUsername);
        boolean passwordPrazno = obojiPolje(textFieldPonoviPassword, LabelPonoviPassword);
        boolean ponoviPasswordPrazno = obojiPolje (textFieldPassword, LabelPassword);
        boolean biloKojePoljePrazno = imePrazno || prezimePrazno || emailPrazno || usernamePrazno ||
                passwordPrazno ||passwordPrazno || ponoviPasswordPrazno;
        return biloKojePoljePrazno;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHelper = new DatabaseHelper();
        databaseHelper.createDatabase();
        databaseHelper.createTable();
    }
}
