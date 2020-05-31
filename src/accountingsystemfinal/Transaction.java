/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountingsystemfinal;

/**
 *
 * @author ENG.WESAM H ASHOUR
 */
public class Transaction {

    private int Transaction_ID;
    private int Customer_id;
    private int Emp_ID;
    private String Transaction_Date;
    private String Payment_Method;
    private Double Transaction_Amount;
    private String Command;

    public Transaction(int Transaction_ID, int Customer_id, int Emp_ID, String Transaction_Date, String Payment_Method, Double Transaction_Amount, String Command) {
        this.Transaction_ID = Transaction_ID;
        this.Customer_id = Customer_id;
        this.Emp_ID = Emp_ID;
        this.Transaction_Date = Transaction_Date;
        this.Payment_Method = Payment_Method;
        this.Transaction_Amount = Transaction_Amount;
        this.Command = Command;
    }

    public int getTransaction_ID() {
        return Transaction_ID;
    }

    public void setTransaction_ID(int Transaction_ID) {
        this.Transaction_ID = Transaction_ID;
    }

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int Customer_id) {
        this.Customer_id = Customer_id;
    }

    public int getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(int Emp_ID) {
        this.Emp_ID = Emp_ID;
    }

    public String getTransaction_Date() {
        return Transaction_Date;
    }

    public void setTransaction_Date(String Transaction_Date) {
        this.Transaction_Date = Transaction_Date;
    }

    public String getPayment_Method() {
        return Payment_Method;
    }

    public void setPayment_Method(String Payment_Method) {
        this.Payment_Method = Payment_Method;
    }

    public Double getTransaction_Amount() {
        return Transaction_Amount;
    }

    public void setTransaction_Amount(Double Transaction_Amount) {
        this.Transaction_Amount = Transaction_Amount;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String Command) {
        this.Command = Command;
    }
}
