/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountingsystemfinal;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author ENG.WESAM H ASHOUR
 */
public class Login_ViewController implements Initializable {

    @FXML
    private PasswordField TFpassword;
    @FXML
    private TextField TFemail;
    @FXML
    Statement statement;
    private ImageView ImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/accountingsystemfinal?serverTimezone=UTC", "root", "");
            statement = con.createStatement();
        } catch (Exception ex) {
            System.out.println("ErrorSql");
        }

    }

    @FXML
    private void SignIn(ActionEvent event) throws SQLException, IOException {

        String Email = TFemail.getText();
        String Password = TFpassword.getText();
        Password = AccountingSystemFinal.hash(Password);
        int Data = Email.indexOf('@');
        String Select = Email.substring(Data + 1, Data + 2);

        if (Select.equalsIgnoreCase("a")) {

            String query = "SELECT Email, Password FROM `admin` Where Email = '" + Email + "' AND Password = '" + Password + "'";
            ResultSet s = AccountingSystemFinal.statement.executeQuery(query);
            if (s.next()) {

                Parent p = FXMLLoader.load(this.getClass().getResource("Admin.fxml"));
                Scene scene = new Scene(p);
                AccountingSystemFinal.primaryStage.setScene(scene);

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Login");
                alert.setHeaderText("Look, You Have Enter an Error Pasword Or Email !");
                alert.setContentText("Ooops, Please Try Again!");

                alert.showAndWait();

            }
        } else if (Select.equalsIgnoreCase("e")) {
            String query = "SELECT Email, Password FROM `employee` Where Email = '" + Email + "' AND Password = '" + Password + "'";
            ResultSet s = AccountingSystemFinal.statement.executeQuery(query);
            if (s.next()) {

                Parent p = FXMLLoader.load(this.getClass().getResource("Employee.fxml"));
                Scene scene = new Scene(p);
                AccountingSystemFinal.primaryStage.setScene(scene);

            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Login");
            alert.setHeaderText("Look, You Have Enter an Error Pasword Or Email !");
            alert.setContentText("Ooops, Please Try Again!");

            alert.showAndWait();

        }
    }
}
