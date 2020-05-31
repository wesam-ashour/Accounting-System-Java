/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountingsystemfinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ENG.WESAM H ASHOUR
 */
public class AccountingSystemFinal extends Application {

    public static Scene scene;
    public static Stage primaryStage;

    static Statement statement;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/accountingsystemfinal?serverTimezone=UTC", "root", "");
            statement = con.createStatement();
        } catch (Exception ex) {
            System.out.println("ErrorSql");
        }
        primaryStage.setOnCloseRequest(e -> {
            try {
                LogFile();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AccountingSystemFinal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AccountingSystemFinal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AccountingSystemFinal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Parent root = FXMLLoader.load(getClass().getResource("Login_View.fxml"));
        LogFile();
        scene = new Scene(root);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.primaryStage = primaryStage;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
private void LogFile() throws SQLException, IOException {
    
        File file = new File("LogFile.txt");
        PrintWriter output = new PrintWriter(file);
        ResultSet rs = AccountingSystemFinal.statement.executeQuery("SELECT * FROM `employee` ");
        output.print("employees :\n");
        while (rs.next()) {
            output.print("ID:" + rs.getInt("ID") + " | " + "Name:" + rs.getString("Name") + " | " + "DOB:" + rs.getString("DOB")
                    + " | " + "Salary:" + rs.getDouble("salary") + " | " + "Email :" + rs.getString("Email") + " | " + "Password:" + rs.getString("Password") + " | " + "Phone :" + rs.getInt("Phone")
                    + "\n");
        }
        rs = AccountingSystemFinal.statement.executeQuery("SELECT * FROM `customer` ");
        output.print("Customers :\n");
        while (rs.next()) {
            output.print("ID:" + rs.getInt("ID") + " | " + "Name:" + rs.getString("Name") + " | " + "DOB:" + rs.getString("DOB")
                    + " | " + "Salary:" + rs.getDouble("salary") + " | " + "Email:" + rs.getString("Email") + " | " + "Password:" + rs.getString("Password") + " | " + "Payment_Method:" + rs.getString("Payment_Method") + " | " + "Phone :" + rs.getInt("Phone")
                    + "\n");
        }output.close();
        

        File fileTRA = new File("Transaction.txt");
        PrintWriter outputTra = new PrintWriter(fileTRA);
        outputTra.print("Transaction :\n");
        rs = AccountingSystemFinal.statement.executeQuery("Select * From `transaction`");
        while (rs.next()) {
            outputTra.print("Transaction_ID :" + rs.getInt("Transaction_ID") + " | " + "Customer id :" + rs.getInt("Customer id") + " | " + "Emp_ID:" + rs.getInt("Emp_ID")
                    + " | " + "Transaction_Amount:" + rs.getDouble("Transaction_Amount") + " | " + " Transaction_Date :" + rs.getString("Transaction_Date") + " | " + "Payment_Method:" + rs.getString("Payment_Method") + " | " + "Command :" + rs.getString("Command")
                    + "\n");
        }outputTra.close();
        
    }
    
    public static String hash(String pass) {

        String passwordToHash = pass;
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    
    // the project Finshed commit
}
