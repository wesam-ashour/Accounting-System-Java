/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountingsystemfinal;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ENG.WESAM H ASHOUR
 */
public class EmployeeController implements Initializable {

    Statement statement;
    @FXML
    private Tab AdminCUS;
    @FXML
    private TextField AdminCusID;
    @FXML
    private TextField AdminCusName;
    @FXML
    private TextField AdminCusDOB;
    @FXML
    private TextField AdminCusEmail;
    @FXML
    private TextField AdminCusPayment;
    @FXML
    private TextField AdminCusSalary;
    @FXML
    private TextField AdminCusPassword;
    @FXML
    private TextField AdminCusPhone;
    private TableView<Customer> AdminCusTabelView;
    @FXML
    private TableColumn<Customer, Integer> AdminCusIdTableColumn;
    @FXML
    private TableColumn<Customer, String> AdminCusNameTableColumn;
    @FXML
    private TableColumn<Customer, Integer> AdminCusPhoneTableColumn;
    @FXML
    private TableColumn<Customer, String> AdminCusDOBTableColumn;
    @FXML
    private TableColumn<Customer, String> AdminCusEmailTableColumn;
    @FXML
    private TableColumn<Customer, String> AdminCusPasswordTableColumn;
    @FXML
    private TableColumn<Customer, String> AdminCusPaymentTableColumn;
    @FXML
    private TableColumn<Customer, Double> AdminCusSalaryTableColumn;
    @FXML
    private TableView<Transaction> EmpWithTabelView;
    @FXML
    private TableColumn<Transaction, Integer> AdminTraTransactionID1;
    @FXML
    private TableColumn<Transaction, Integer> AdminTraCustomerID1;
    @FXML
    private TableColumn<Transaction, Integer> AdminTraEmployeeID1;
    @FXML
    private TableColumn<Transaction, String> AdminTraTransactionDate1;
    @FXML
    private TableColumn<Transaction, String> AdminTraPaymentType1;
    @FXML
    private TableColumn<Transaction, Double> AdminTraTransactionAmount1;
    @FXML
    private TableColumn<Transaction, String> AdminTraCommand1;
    @FXML
    private TextField EmpWithTraId;
    @FXML
    private TextField EmpWithCusId;
    @FXML
    private TextField EmpWithEmpId;
    @FXML
    private TextField EmpWithTraDate;
    @FXML
    private TextField EmpWithPayType;
    @FXML
    private TextField EmpWithTraAmount;
    @FXML
    private TextField EmpWithComm;
    @FXML
    private TableView<Customer> AdminCusTabelVieew;

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

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        String date = df.format(dateobj);
        EmpWithTraDate.setText(date);
        try {
            SHcus();
        } catch (SQLException ex) {
            System.out.println("ErrorCus");
        }

        try {
            SHwith();
        } catch (SQLException ex) {
            System.out.println("Errorwith");
        }
        AdminCusIdTableColumn.setCellValueFactory(new PropertyValueFactory("IDCus"));
        AdminCusNameTableColumn.setCellValueFactory(new PropertyValueFactory("NameCus"));
        AdminCusDOBTableColumn.setCellValueFactory(new PropertyValueFactory("DOBCus"));
        AdminCusEmailTableColumn.setCellValueFactory(new PropertyValueFactory("EmailCus"));
        AdminCusPasswordTableColumn.setCellValueFactory(new PropertyValueFactory("PasswordCus"));
        AdminCusPaymentTableColumn.setCellValueFactory(new PropertyValueFactory("Payment_MethodCus"));
        AdminCusSalaryTableColumn.setCellValueFactory(new PropertyValueFactory("SalaryCus"));
        AdminCusPhoneTableColumn.setCellValueFactory(new PropertyValueFactory("PhoneCus"));

        AdminTraTransactionID1.setCellValueFactory(new PropertyValueFactory("Transaction_ID"));
        AdminTraCustomerID1.setCellValueFactory(new PropertyValueFactory("Customer_id"));
        AdminTraEmployeeID1.setCellValueFactory(new PropertyValueFactory("Emp_ID"));
        AdminTraTransactionDate1.setCellValueFactory(new PropertyValueFactory("Transaction_Date"));
        AdminTraPaymentType1.setCellValueFactory(new PropertyValueFactory("Payment_Method"));
        AdminTraTransactionAmount1.setCellValueFactory(new PropertyValueFactory("Transaction_Amount"));
        AdminTraCommand1.setCellValueFactory(new PropertyValueFactory("Command"));

        AdminCusTabelVieew.getSelectionModel().selectedItemProperty().addListener(e -> {
            Customer s = AdminCusTabelVieew.getSelectionModel().getSelectedItem();
            if (s != null) {
                AdminCusID.setText(String.valueOf(s.getIDCus()));
                AdminCusName.setText(s.getNameCus());
                AdminCusDOB.setText(s.getDOBCus());
                AdminCusEmail.setText(s.getEmailCus());
                AdminCusPassword.setText(s.getPasswordCus());
                AdminCusPayment.setText(s.getPayment_MethodCus());
                AdminCusPhone.setText(String.valueOf(s.getPhoneCus()));
                AdminCusSalary.setText(String.valueOf(s.getSalaryCus()));

            }
        });

        EmpWithTabelView.getSelectionModel().selectedItemProperty().addListener(e -> {
            Transaction s = EmpWithTabelView.getSelectionModel().getSelectedItem();
            if (s != null) {
                EmpWithTraId.setText(String.valueOf(s.getTransaction_ID()));
                EmpWithCusId.setText(String.valueOf(s.getCustomer_id()));
                EmpWithEmpId.setText(String.valueOf(s.getEmp_ID()));
                EmpWithTraDate.setText(s.getTransaction_Date());
                EmpWithPayType.setText(s.getPayment_Method());
                EmpWithTraAmount.setText(String.valueOf(s.getTransaction_Amount()));
                EmpWithComm.setText(s.getCommand());

            }
        });
    }

    private void SHcus() throws SQLException {
        AdminCusTabelVieew.getItems().clear();
        ResultSet rs = statement.executeQuery("select * from customer");
        while (rs.next()) {
            Customer s = new Customer(rs.getInt("ID"), rs.getString("Name"), rs.getString("DOB"), rs.getString("Email"), rs.getString("Password"), rs.getString("Payment_Method"), rs.getInt("Salary"), rs.getInt("Phone"));
            AdminCusTabelVieew.getItems().add(s);

        }
    }

    private void SHwith() throws SQLException {
        EmpWithTabelView.getItems().clear();
        ResultSet rs = statement.executeQuery("select * from transaction");
        while (rs.next()) {
            Transaction s = new Transaction(rs.getInt("Transaction_ID"), rs.getInt("Customer id"), rs.getInt("Emp_ID"), rs.getString("Transaction_Date"), rs.getString("Payment_Method"), rs.getDouble("Transaction_Amount"), rs.getString("Command"));
            EmpWithTabelView.getItems().add(s);

        }
    }

    private void ClearCus() {
        AdminCusID.setText("");
        AdminCusName.setText("");
        AdminCusDOB.setText("");
        AdminCusEmail.setText("");
        AdminCusPassword.setText("");
        AdminCusPayment.setText("");
        AdminCusSalary.setText("");
        AdminCusPhone.setText("");

    }

    private void ClearWith() {
        EmpWithTraId.setText("");
        EmpWithCusId.setText("");
        EmpWithEmpId.setText("");
        EmpWithTraDate.setText("");
        EmpWithPayType.setText("");
        EmpWithTraAmount.setText("");
        EmpWithComm.setText("");
    }

    @FXML
    private void AdminCusAddHandle(ActionEvent event) throws SQLException {
        int ID = 0;
        boolean CusID = true;
        boolean CusName = true;
        boolean CusDOB = true;
        boolean CusEmail = true;
        boolean CusPassword = true;
        boolean CusPayment = true;
        boolean CusSalary = true;
        boolean CusPhone = true;
        try {
            ID = Integer.parseInt(AdminCusID.getText());
        } catch (NumberFormatException n) {
            CusID = false;

        }
        String adminEmpName = AdminCusName.getText();
        String adminEmpDOB = AdminCusDOB.getText();
        String adminEmpEmail = AdminCusEmail.getText();
        String adminEmpPassword = AdminCusPassword.getText();
        adminEmpPassword = AccountingSystemFinal.hash(adminEmpPassword);
        String AdminEmpPayment = AdminCusPayment.getText();
        String adminEmpSalary = AdminCusSalary.getText();
        String adminEmpPhone = AdminCusPhone.getText();

        if (adminEmpName.equals("")) {
            CusName = false;

        }
        if (adminEmpPhone.equals("")) {
            CusPhone = false;

        }
        if (adminEmpDOB.equals("")) {
            CusDOB = false;

        }
        if (adminEmpEmail.equals("")) {
            CusEmail = false;

        }
        if (adminEmpPassword.equals("")) {
            CusPassword = false;

        }
        if (AdminEmpPayment.equals("")) {
            CusPassword = false;

        }

        Double grade = 0.0;
        try {
            grade = Double.parseDouble(AdminCusSalary.getText());
        } catch (NumberFormatException e) {
            CusSalary = false;

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order will Insert to database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (CusID && CusName && CusPhone && CusDOB && CusEmail && CusPassword && CusSalary && CusPayment) {
                String sql = "INSERT INTO `customer` (`ID`, `Name`, `DOB`, `Email`, `Password`, `Payment_Method`, `Salary`, `Phone`) VALUES ('" + AdminCusID.getText() + "', '" + AdminCusName.getText() + "', '" + AdminCusDOB.getText() + "', '" + AdminCusEmail.getText() + "', '" + AdminCusPassword + "', '" + AdminCusPayment.getText() + "', '" + AdminCusSalary.getText() + "', '" + AdminCusPhone.getText() + "');";
                statement.executeUpdate(sql);
                SHcus();
                ClearCus();
            }
        }
    }

    @FXML
    private void AdminCusEditeHandle(ActionEvent event) throws SQLException {
        int ID = 0;
        boolean CusID = true;
        boolean CusName = true;
        boolean CusDOB = true;
        boolean CusEmail = true;
        boolean CusPassword = true;
        boolean CusPayment = true;
        boolean CusSalary = true;
        boolean CusPhone = true;
        try {
            ID = Integer.parseInt(AdminCusID.getText());
        } catch (NumberFormatException n) {
            CusID = false;

        }
        String adminEmpName = AdminCusName.getText();
        String adminEmpDOB = AdminCusDOB.getText();
        String adminEmpEmail = AdminCusEmail.getText();
        String adminEmpPassword = AdminCusPassword.getText();
        String AdminEmpPayment = AdminCusPayment.getText();
        String adminEmpSalary = AdminCusSalary.getText();
        String adminEmpPhone = AdminCusPhone.getText();

        if (adminEmpName.equals("")) {
            CusName = false;

        }
        if (adminEmpPhone.equals("")) {
            CusPhone = false;

        }
        if (adminEmpDOB.equals("")) {
            CusDOB = false;

        }
        if (adminEmpEmail.equals("")) {
            CusEmail = false;

        }
        if (adminEmpPassword.equals("")) {
            CusPassword = false;

        }
        if (AdminEmpPayment.equals("")) {
            CusPassword = false;

        }

        Double grade = 0.0;
        try {
            grade = Double.parseDouble(AdminCusSalary.getText());
        } catch (NumberFormatException e) {
            CusSalary = false;

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edite Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order will Edite in database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (CusID && CusName && CusPhone && CusDOB && CusEmail && CusPassword && CusSalary & CusPayment) {
                ResultSet s = statement.executeQuery("select ID from customer where ID = " + ID);
                String sql = "UPDATE `customer` SET `Name` = '" + AdminCusName.getText() + "', `DOB` = '" + AdminCusDOB.getText() + "', `Email` = '" + AdminCusEmail.getText() + "', `Password` = '" + AdminCusPassword.getText() + "',`Payment_Method` = '" + AdminCusPayment.getText() + "', `Salary` = '" + AdminCusSalary.getText() + "', `Phone` = '" + AdminCusPhone.getText() + "' WHERE `customer`.`ID` = " + AdminCusID.getText() + "";
                statement.executeUpdate(sql);
                SHcus();
                ClearCus();
            }
        }
    }

    private void AdminEmpWithEditeHandle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edite Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order will Edite in database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        }
    }

    @FXML
    private void AdminEmpWithDeleteHandle(ActionEvent event) throws SQLException {
        int ID = 0;
        boolean CusID = true;
        boolean CusName = true;
        boolean CusDOB = true;
        boolean CusEmail = true;
        boolean CusPassword = true;
        boolean CusPayment = true;
        boolean CusSalary = true;

        try {
            ID = Integer.parseInt(EmpWithTraId.getText());
        } catch (NumberFormatException n) {
            CusID = false;

        }
        String adminEmpName = EmpWithCusId.getText();
        String adminEmpDOB = EmpWithEmpId.getText();
        String adminEmpEmail = EmpWithTraDate.getText();
        String adminEmpPassword = EmpWithPayType.getText();
        String AdminEmpPayment = EmpWithComm.getText();
        String adminEmpSalary = EmpWithTraAmount.getText();

        if (adminEmpName.equals("")) {
            CusName = false;

        }

        if (adminEmpDOB.equals("")) {
            CusDOB = false;

        }
        if (adminEmpEmail.equals("")) {
            CusEmail = false;

        }
        if (adminEmpPassword.equals("")) {
            CusPassword = false;

        }
        if (AdminEmpPayment.equals("")) {
            CusPassword = false;

        }

        Double grade = 0.0;
        try {
            grade = Double.parseDouble(EmpWithTraAmount.getText());
        } catch (NumberFormatException e) {
            CusSalary = false;

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order will Delete From database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (CusID && CusName && CusDOB && CusEmail && CusPassword && CusSalary & CusPayment) {
                ResultSet rs = statement.executeQuery("select Transaction_ID from transaction where Transaction_ID = " + ID);
                if (rs.next()) {
                    String sql = "delete from transaction where Transaction_ID = " + ID;
                    statement.executeUpdate(sql);
                    SHwith();
                    ClearWith();
                }
            }
        }
    }

    @FXML
    private void AdminCusDeleteHandle(ActionEvent event) throws SQLException {
        int ID = 0;
        boolean CusID = true;
        boolean CusName = true;
        boolean CusDOB = true;
        boolean CusEmail = true;
        boolean CusPassword = true;
        boolean CusPayment = true;
        boolean CusSalary = true;
        boolean CusPhone = true;
        try {
            ID = Integer.parseInt(AdminCusID.getText());
        } catch (NumberFormatException n) {
            CusID = false;

        }
        String adminEmpName = AdminCusName.getText();
        String adminEmpDOB = AdminCusDOB.getText();
        String adminEmpEmail = AdminCusEmail.getText();
        String adminEmpPassword = AdminCusPassword.getText();
        String AdminEmpPayment = AdminCusPayment.getText();
        String adminEmpSalary = AdminCusSalary.getText();
        String adminEmpPhone = AdminCusPhone.getText();

        if (adminEmpName.equals("")) {
            CusName = false;

        }
        if (adminEmpPhone.equals("")) {
            CusPhone = false;

        }
        if (adminEmpDOB.equals("")) {
            CusDOB = false;

        }
        if (adminEmpEmail.equals("")) {
            CusEmail = false;

        }
        if (adminEmpPassword.equals("")) {
            CusPassword = false;

        }
        if (AdminEmpPayment.equals("")) {
            CusPassword = false;

        }

        Double grade = 0.0;
        try {
            grade = Double.parseDouble(AdminCusSalary.getText());
        } catch (NumberFormatException e) {
            CusSalary = false;

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order will Delete From database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (CusID && CusName && CusPhone && CusDOB && CusEmail && CusPassword && CusSalary & CusPayment) {
                ResultSet rs = statement.executeQuery("select ID from customer where ID = " + ID);
                if (rs.next()) {
                    String sql = "delete from customer where ID = " + ID;
                    statement.executeUpdate(sql);
                    SHcus();
                    ClearCus();
                } else {
                }
            }
        }
    }

    @FXML
    private void AdminCusClearHandle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Clear Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order will Clear From database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            AdminCusID.setText("");
            AdminCusName.setText("");
            AdminCusDOB.setText("");
            AdminCusEmail.setText("");
            AdminCusPassword.setText("");
            AdminCusPayment.setText("");
            AdminCusSalary.setText("");
            AdminCusPhone.setText("");
        }
    }

    @FXML
    private void AdminEmpWithClearHandle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Clear Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order will Clear From database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            EmpWithTraId.setText("");
            EmpWithCusId.setText("");
            EmpWithEmpId.setText("");
            EmpWithTraDate.setText("");
            EmpWithPayType.setText("");
            EmpWithTraAmount.setText("");
            EmpWithComm.setText("");
        }
    }

    @FXML
    private void AdminEmpWithAddHandle(ActionEvent event) throws SQLException {
        int ID = 0;
        boolean CusID = true;
        boolean CusName = true;
        boolean CusDOB = true;
        boolean CusEmail = true;
        boolean CusPassword = true;
        boolean CusPayment = true;
        boolean CusSalary = true;

        try {
            ID = Integer.parseInt(EmpWithTraId.getText());
        } catch (NumberFormatException n) {
            CusID = false;

        }
        String adminEmpName = EmpWithCusId.getText();
        String adminEmpDOB = EmpWithEmpId.getText();
        String adminEmpEmail = EmpWithTraDate.getText();
        String adminEmpPassword = EmpWithPayType.getText();
        String AdminEmpPayment = EmpWithComm.getText();
        String adminEmpSalary = EmpWithTraAmount.getText();

        if (adminEmpName.equals("")) {
            CusName = false;

        }

        if (adminEmpDOB.equals("")) {
            CusDOB = false;

        }
        if (adminEmpEmail.equals("")) {
            CusEmail = false;

        }
        if (adminEmpPassword.equals("")) {
            CusPassword = false;

        }
        if (AdminEmpPayment.equals("")) {
            CusPassword = false;

        }

        Double grade = 0.0;
        try {
            grade = Double.parseDouble(EmpWithTraAmount.getText());
        } catch (NumberFormatException e) {
            CusSalary = false;

        }

        String sql = "SELECT * FROM `customer` WHERE `customer`.`ID` =" + EmpWithCusId.getText();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            double CertinBalance = rs.getDouble("Salary");
            if (CertinBalance > Double.parseDouble(EmpWithTraAmount.getText())) {
                System.out.println("here");
                double NewBalance = CertinBalance - Double.parseDouble(EmpWithTraAmount.getText());
                sql = "UPDATE `customer` SET `Salary` =" + NewBalance + "  WHERE `customer`.`ID` = '" + EmpWithCusId.getText() + "'; ";
                statement.executeUpdate(sql);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Add Order");
                alert.setHeaderText("Are You Sure ? ");
                alert.setContentText("In this case the order will Insert to database ");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (CusID && CusName && CusDOB && CusEmail && CusPassword && CusSalary & CusPayment) {
                        String sqli = "INSERT INTO `transaction` (`Transaction_ID`, `Customer id`, `Emp_ID`, `Transaction_Date`, `Payment_Method`, `Transaction_Amount`, `Command`) VALUES ('" + EmpWithTraId.getText() + "', '" + EmpWithCusId.getText() + "', '" + EmpWithEmpId.getText() + "', '" + EmpWithTraDate.getText() + "', '" + EmpWithPayType.getText() + "', '" + EmpWithTraAmount.getText() + "', '" + EmpWithComm.getText() + "');";
                        statement.executeUpdate(sqli);

                    }

                    SHwith();
                    ClearWith();
                }
            } else {
                Alert alertw = new Alert(AlertType.WARNING);
                alertw.setTitle("Warning Dialog");
                alertw.setHeaderText("Look, There Is No enogh Blance");
                alertw.setContentText("Please Check Your Input");
                alertw.showAndWait();
            }
        }

    }
}
