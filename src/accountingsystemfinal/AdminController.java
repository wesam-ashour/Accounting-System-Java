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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class AdminController implements Initializable {

    Statement statement;
    @FXML
    private Tab AdminEMP;
    @FXML
    private Tab AdminCUS;
    @FXML
    private Tab AdminTra;
    @FXML
    private TableView<Employee> AdminEmpTabelView;
    @FXML
    private TableColumn<Employee, Integer> AdminEmpIdTableColumn;
    @FXML
    private TableColumn<Employee, String> AdminEmpNameTableColumn;
    @FXML
    private TableColumn<Employee, Integer> AdminEmpPhoneTableColumn;
    @FXML
    private TableColumn<Employee, String> AdminEmpDOBTableColumn;
    @FXML
    private TableColumn<Employee, String> AdminEmpEmailTableColumn;
    @FXML
    private TableColumn<Employee, String> AdminEmpPasswordTableColumn;
    @FXML
    private TableColumn<Employee, Double> AdminEmpSalaryTableColumn;
    @FXML
    private TextField AdminEmpID;
    @FXML
    private TextField AdminEmpName;
    @FXML
    private TextField AdminEmpPhone;
    @FXML
    private TextField AdminEmpDOB;
    @FXML
    private TextField AdminEmpPassword;
    @FXML
    private TextField AdminEmpSalary;
    @FXML
    private TextField AdminEmpEmail;
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
    @FXML
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
    private TableColumn<Admin, Integer> AdminTraTransactionID;
    @FXML
    private TableColumn<Admin, Integer> AdminTraCustomerID;
    @FXML
    private TableColumn<Admin, Integer> AdminTraEmployeeID;
    @FXML
    private TableColumn<Admin, String> AdminTraTransactionDate;
    @FXML
    private TableColumn<Admin, String> AdminTraPaymentType;
    @FXML
    private TableColumn<Admin, Double> AdminTraTransactionAmount;
    @FXML
    private TableColumn<Admin, String> AdminTraCommand;
    @FXML
    private TableView<Transaction> Admintraemp;

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

        AdminEmpIdTableColumn.setCellValueFactory(new PropertyValueFactory("IDEmp"));
        AdminEmpNameTableColumn.setCellValueFactory(new PropertyValueFactory("NameEmp"));
        AdminEmpPhoneTableColumn.setCellValueFactory(new PropertyValueFactory("PhoneEmp"));
        AdminEmpDOBTableColumn.setCellValueFactory(new PropertyValueFactory("DOBEmp"));
        AdminEmpEmailTableColumn.setCellValueFactory(new PropertyValueFactory("EmaiEmpl"));
        AdminEmpPasswordTableColumn.setCellValueFactory(new PropertyValueFactory("PasswordEmp"));
        AdminEmpSalaryTableColumn.setCellValueFactory(new PropertyValueFactory("SalaryEmp"));

        AdminCusIdTableColumn.setCellValueFactory(new PropertyValueFactory("IDCus"));
        AdminCusNameTableColumn.setCellValueFactory(new PropertyValueFactory("NameCus"));
        AdminCusDOBTableColumn.setCellValueFactory(new PropertyValueFactory("DOBCus"));
        AdminCusEmailTableColumn.setCellValueFactory(new PropertyValueFactory("EmailCus"));
        AdminCusPasswordTableColumn.setCellValueFactory(new PropertyValueFactory("PasswordCus"));
        AdminCusPaymentTableColumn.setCellValueFactory(new PropertyValueFactory("Payment_MethodCus"));
        AdminCusSalaryTableColumn.setCellValueFactory(new PropertyValueFactory("SalaryCus"));
        AdminCusPhoneTableColumn.setCellValueFactory(new PropertyValueFactory("PhoneCus"));

        AdminTraTransactionID.setCellValueFactory(new PropertyValueFactory("Transaction_ID"));
        AdminTraCustomerID.setCellValueFactory(new PropertyValueFactory("Customer_id"));
        AdminTraEmployeeID.setCellValueFactory(new PropertyValueFactory("Emp_ID"));
        AdminTraTransactionDate.setCellValueFactory(new PropertyValueFactory("Transaction_Date"));
        AdminTraPaymentType.setCellValueFactory(new PropertyValueFactory("Payment_Method"));
        AdminTraTransactionAmount.setCellValueFactory(new PropertyValueFactory("Transaction_Amount"));
        AdminTraCommand.setCellValueFactory(new PropertyValueFactory("Command"));

        try {
            SH();
        } catch (SQLException ex) {
            System.out.println("Error");
        }

        try {
            SHTra();
        } catch (SQLException ex) {
            System.out.println("ErrorTra");
        }
        try {
            SHcus();
        } catch (SQLException ex) {
            System.out.println("ErrorCus");
        }

        Admintraemp.getSelectionModel().selectedItemProperty().addListener(e -> {
            Transaction Em = Admintraemp.getSelectionModel().getSelectedItem();

            if (Em != null) {
                AdminTraTransactionID.setText(String.valueOf(Em.getTransaction_ID()));
                AdminTraCustomerID.setText(String.valueOf(Em.getCustomer_id()));
                AdminTraEmployeeID.setText(String.valueOf(Em.getEmp_ID()));
                AdminTraTransactionDate.setText(Em.getTransaction_Date());
                AdminTraPaymentType.setText(Em.getPayment_Method());
                AdminTraTransactionAmount.setText(String.valueOf(Em.getTransaction_Amount()));
                AdminTraCommand.setText(Em.getCommand());

            }
        });
        AdminEmpTabelView.getSelectionModel().selectedItemProperty().addListener(e -> {
            Employee Em = AdminEmpTabelView.getSelectionModel().getSelectedItem();

            if (Em != null) {
                AdminEmpID.setText(String.valueOf(Em.getIDEmp()));
                AdminEmpName.setText(Em.getNameEmp());
                AdminEmpPhone.setText(String.valueOf(Em.getPhoneEmp()));
                AdminEmpDOB.setText(Em.getDOBEmp());
                AdminEmpPassword.setText(Em.getPasswordEmp());
                AdminEmpSalary.setText(String.valueOf(Em.getSalaryEmp()));
                AdminEmpEmail.setText(Em.getEmaiEmpl());
            }
        });

        AdminCusTabelView.getSelectionModel().selectedItemProperty().addListener(e -> {
            Customer s = AdminCusTabelView.getSelectionModel().getSelectedItem();
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
    }

    @FXML
    private void AdminEmpEditeHandle(ActionEvent event) throws SQLException {
        int ID = 0;
        boolean AdminID = true;
        boolean AdminName = true;
        boolean AdminPhone = true;
        boolean AdminDOB = true;
        boolean AdminEmail = true;
        boolean AdminPassword = true;
        boolean AdminSalary = true;
        try {
            ID = Integer.parseInt(AdminEmpID.getText());
        } catch (NumberFormatException n) {
            AdminID = false;

        }
        String adminEmpName = AdminEmpName.getText();
        String adminEmpPhone = AdminEmpPhone.getText();
        String adminEmpDOB = AdminEmpDOB.getText();
        String adminEmpEmail = AdminEmpEmail.getText();
        String adminEmpPassword = AdminEmpPassword.getText();
        String adminEmpSalary = AdminEmpSalary.getText();

        if (adminEmpName.equals("")) {
            AdminName = false;

        }
        if (adminEmpPhone.equals("")) {
            AdminPhone = false;

        }
        if (adminEmpDOB.equals("")) {
            AdminDOB = false;

        }
        if (adminEmpEmail.equals("")) {
            AdminEmail = false;

        }
        if (adminEmpPassword.equals("")) {
            AdminPassword = false;

        }

        Double grade = 0.0;
        try {
            grade = Double.parseDouble(AdminEmpSalary.getText());
        } catch (NumberFormatException e) {
            AdminSalary = false;

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edite Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order will Edite in database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (AdminID && AdminName && AdminPhone && AdminDOB && AdminEmail && AdminPassword && AdminSalary) {
                ResultSet s = statement.executeQuery("select ID from employee where ID = " + ID);
                String sql = "UPDATE `employee` SET `Name` = '" + AdminEmpName.getText() + "', `DOB` = '" + AdminEmpDOB.getText() + "', `Email` = '" + AdminEmpEmail.getText() + "', `Password` = '" + AdminEmpPassword.getText() + "',`Salary` = '" + AdminEmpSalary.getText() + "', `Phone` = '" + AdminEmpPhone.getText() + "' WHERE `employee`.`ID` = " + AdminEmpID.getText() + "";
                statement.executeUpdate(sql);
                SH();
                clearAllStudent();
            }
        }
         
    }

    @FXML
    private void AdminEmpClearHandle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Clear Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order Clear From database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            clearAllStudent();
        }
    }

    @FXML
    private void AdminEmpDeleteHandle(ActionEvent event) throws SQLException {
        int ID = 0;
        boolean AdminID = true;
        boolean AdminName = true;
        boolean AdminPhone = true;
        boolean AdminDOB = true;
        boolean AdminEmail = true;
        boolean AdminPassword = true;
        boolean AdminSalary = true;
        try {
            ID = Integer.parseInt(AdminEmpID.getText());
        } catch (NumberFormatException n) {
            AdminID = false;

        }
        String adminEmpName = AdminEmpName.getText();
        String adminEmpPhone = AdminEmpPhone.getText();
        String adminEmpDOB = AdminEmpDOB.getText();
        String adminEmpEmail = AdminEmpEmail.getText();
        String adminEmpPassword = AdminEmpPassword.getText();
        String adminEmpSalary = AdminEmpSalary.getText();

        if (adminEmpName.equals("")) {
            AdminName = false;

        }
        if (adminEmpPhone.equals("")) {
            AdminPhone = false;

        }
        if (adminEmpDOB.equals("")) {
            AdminDOB = false;

        }
        if (adminEmpEmail.equals("")) {
            AdminEmail = false;

        }
        if (adminEmpPassword.equals("")) {
            AdminPassword = false;

        }

        Double grade = 0.0;
        try {
            grade = Double.parseDouble(AdminEmpSalary.getText());
        } catch (NumberFormatException e) {
            AdminSalary = false;

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order elete to database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (AdminID && AdminName && AdminPhone && AdminDOB && AdminEmail && AdminPassword && AdminSalary) {
                ResultSet rs = statement.executeQuery("select ID from employee where ID = " + ID);
                if (rs.next()) {
                    String sql = "delete from employee where ID = " + ID;
                    statement.executeUpdate(sql);
                    SH();
                    clearAllStudent();
                } else {
                }
            }
        }

    }

    @FXML
    private void AdminEmpAddHandle(ActionEvent event) throws SQLException {
        int ID = 0;
        boolean AdminID = true;
        boolean AdminName = true;
        boolean AdminPhone = true;
        boolean AdminDOB = true;
        boolean AdminEmail = true;
        boolean AdminPassword = true;

        boolean AdminSalary = true;
        try {
            ID = Integer.parseInt(AdminEmpID.getText());
        } catch (NumberFormatException n) {
            AdminID = false;

        }
        String adminEmpName = AdminEmpName.getText();
        String adminEmpDOB = AdminEmpDOB.getText();
        String adminEmpEmail = AdminEmpEmail.getText();
        String adminEmpPassword = AdminEmpPassword.getText();
        adminEmpPassword = AccountingSystemFinal.hash(adminEmpPassword);
        String adminEmpSalary = AdminEmpSalary.getText();
        String adminEmpPhone = AdminEmpPhone.getText();

        if (adminEmpName.equals("")) {
            AdminName = false;

        }
        if (adminEmpPhone.equals("")) {
            AdminPhone = false;

        }
        if (adminEmpDOB.equals("")) {
            AdminDOB = false;

        }
        if (adminEmpEmail.equals("")) {
            AdminEmail = false;

        }
        if (adminEmpPassword.equals("")) {
            AdminPassword = false;

        }

        Double grade = 0.0;
        try {
            grade = Double.parseDouble(AdminEmpSalary.getText());
        } catch (NumberFormatException e) {
            AdminSalary = false;

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Order");
        alert.setHeaderText("Are You Sure ? ");
        alert.setContentText("In this case the order Add to database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (AdminID && AdminName && AdminPhone && AdminDOB && AdminEmail && AdminPassword && AdminSalary) {
                String sql = " INSERT INTO `employee` (`ID`, `Name`, `DOB`, `Email`, `Password`, `Salary`, `Phone`) "
                        + "VALUES ('" + AdminEmpID.getText() + "', '" + AdminEmpName.getText() + "', '" + AdminEmpDOB.getText() + "', '" + AdminEmpEmail.getText() + "', '" + adminEmpPassword + "', '" + AdminEmpSalary.getText() + "', '" + AdminEmpPhone.getText() + "'); ";
                statement.executeUpdate(sql);
                SH();
                clearAllStudent();
            }
        }
    }

    private void clearAllStudent() {
        AdminEmpID.setText("");
        AdminEmpName.setText("");
        AdminEmpPhone.setText("");
        AdminEmpDOB.setText("");
        AdminEmpEmail.setText("");
        AdminEmpPassword.setText("");
        AdminEmpSalary.setText("");

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
        alert.setContentText("In this case the order Add to database ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (CusID && CusName && CusPhone && CusDOB && CusEmail && CusPassword && CusSalary & CusPayment) {
                String sql = "INSERT INTO `customer` (`ID`, `Name`, `DOB`, `Email`, `Password`, `Payment_Method`, `Salary`, `Phone`) VALUES ('" + AdminCusID.getText() + "', '" + AdminCusName.getText() + "', '" + AdminCusDOB.getText() + "', '" + AdminCusEmail.getText() + "', '" + adminEmpPassword + "', '" + AdminCusPayment.getText() + "', '" + AdminCusSalary.getText() + "', '" + AdminCusPhone.getText() + "');";
                statement.executeUpdate(sql);
                SHcus();
                ClearCus();
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
        alert.setContentText("In this case the order Delete From database ");
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
        alert.setContentText("In this case the order Clear From database ");
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

    private void SHTra() throws SQLException {
        AdminCusTabelView.getItems().clear();
        ResultSet rs = statement.executeQuery("select * from transaction");
        while (rs.next()) {
            Transaction s = new Transaction(rs.getInt("Transaction_ID"), rs.getInt("Customer id"), rs.getInt("Emp_ID"), rs.getString("Transaction_Date"), rs.getString("Payment_Method"), rs.getDouble("Transaction_Amount"), rs.getString("Command"));
            Admintraemp.getItems().add(s);

        }
    }

    private void SHcus() throws SQLException {
        AdminCusTabelView.getItems().clear();
        ResultSet rs = statement.executeQuery("select * from customer");
        ArrayList<Customer> AllCustomer = new ArrayList<Customer>();

        while (rs.next()) {
            Customer s = new Customer(rs.getInt("ID"), rs.getString("Name"), rs.getString("DOB"), rs.getString("Email"), rs.getString("Password"), rs.getString("Payment_Method"), rs.getInt("Salary"), rs.getInt("Phone"));

            AllCustomer.add(s);
        }
        for (int i = 0; i < AllCustomer.size(); i++) {
            AdminCusTabelView.getItems().add(AllCustomer.get(i));

        }
    }

    private void SH() throws SQLException {
        AdminEmpTabelView.getItems().clear();
        ResultSet rs = statement.executeQuery("select * from employee");
        ArrayList<Employee> AllEmployee = new ArrayList<Employee>();
        while (rs.next()) {

            Employee s = new Employee(rs.getInt("ID"), rs.getString("Name"), rs.getString("DOB"), rs.getString("Email"), rs.getString("Password"), rs.getInt("Salary"), rs.getInt("Phone"));
            AllEmployee.add(s);

        }
        for (int i = 0; i < AllEmployee.size(); i++) {
            AdminEmpTabelView.getItems().add(AllEmployee.get(i));

        }
    }
}
