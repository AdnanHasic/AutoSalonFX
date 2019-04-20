package sample.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private Connection connection;


    public void createDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bazausera.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        String createTableString = "CREATE TABLE IF NOT EXISTS User (" +
                "ID INTEGER PRIMARY KEY, " +
                "IME TEXT NOT NULL, " +
                "PREZIME TEXT NOT NULL, " +
                "EMAIL TEXT NOT NULL, " +
                "USERNAME TEXT NOT NULL, " +
                "PASSWORD TEXT NOT NULL)";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createAutaTable() {
        String createTableString = "CREATE TABLE IF NOT EXISTS Auta(" +
                "ID INTEGER PRIMARY KEY, " +
                "MARKA TEXT NOT NULL, " +
                "MODEL TEXT NOT NULL, " +
                "GODISTE INTEGER NOT NULL, " +
                "KILOMETRAZA INTEGER NOT NULL)";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    public void createUser(User user) {
        String createRowString = "INSERT INTO User(ime, prezime, email, username, password) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createRowString);
            preparedStatement.setString(1, user.getIme());
            preparedStatement.setString(2, user.getPrezime());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createAuto(Auto auto) {
        String createRowString = "INSERT INTO Auta(marka, model, godiste, kilometraza) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createRowString);
            preparedStatement.setString(1, auto.getMarka());
            preparedStatement.setString(2, auto.getModel());
            preparedStatement.setInt(3, auto.getGodiste());
            preparedStatement.setInt(4, auto.getKilometraza());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public User postojiLiSaOvimUsernameomIPasswordom (String username, String password) {
        User user = null;
        String nadjiUsera = "SELECT * FROM User WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(nadjiUsera);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setIme(resultSet.getString(1));
                user.setPrezime(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setUsername(resultSet.getString(4));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;

    }


    public User postojiLiSaOvimUsernameom(String username) {
        User user = null;
        String nadjiUsera = "SELECT * FROM User WHERE username = ?";
        try {
           PreparedStatement preparedStatement = connection.prepareStatement(nadjiUsera);
           preparedStatement.setString(1,username);
           ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setIme(resultSet.getString(1));
                user.setPrezime(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setUsername(resultSet.getString(4));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;

    }

    public List<User> readRows() {
        String readRowsString = "SELECT * FROM User";
        List<User> useriIzBaze = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readRowsString);

            while (resultSet.next()) {
                User user = new User();
                String ime = resultSet.getString(2);
                user.setIme(ime);
                String prezime = resultSet.getString(3);
                user.setPrezime(prezime);
                String email = resultSet.getString(4);
                user.setEmail(email);
                String username = resultSet.getString(5);
                user.setUsername(username);
                String password = resultSet.getString(6);
                user.setPassword(password);

                useriIzBaze.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return useriIzBaze;
    }
    public List<Auto> vratiSvaAuta() {
        String readRowsString = "SELECT * FROM Auta";
        List<Auto> autaIzBaze = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readRowsString);

            while (resultSet.next()) {
                Auto auto = new Auto();
                String marka = resultSet.getString(2);
                auto.setMarka(marka);
                String model = resultSet.getString(3);
                auto.setModel(model);
                int godiste = resultSet.getInt(4);
                auto.setGodiste(godiste);
                int kilometraza = resultSet.getInt(5);
                auto.setKilometraza(kilometraza);



                autaIzBaze.add(auto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autaIzBaze;
    }

}
