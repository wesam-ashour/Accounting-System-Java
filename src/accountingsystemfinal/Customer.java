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
public class Customer {

    private int IDCus;
    private String NameCus;
    private String DOBCus;
    private String EmailCus;
    private String PasswordCus;
    private String Payment_MethodCus;
    private double SalaryCus;
    private int PhoneCus;

    public Customer(int IDCus, String NameCus, String DOBCus, String EmailCus, String PasswordCus, String Payment_MethodCus, double SalaryCus, int PhoneCus) {
        this.IDCus = IDCus;
        this.NameCus = NameCus;
        this.DOBCus = DOBCus;
        this.EmailCus = EmailCus;
        this.PasswordCus = PasswordCus;
        this.Payment_MethodCus = Payment_MethodCus;
        this.SalaryCus = SalaryCus;
        this.PhoneCus = PhoneCus;
    }

    public Customer() {

    }

    public int getIDCus() {
        return IDCus;
    }

    public void setIDCus(int IDCus) {
        this.IDCus = IDCus;
    }

    public String getNameCus() {
        return NameCus;
    }

    public void setNameCus(String NameCus) {
        this.NameCus = NameCus;
    }

    public String getDOBCus() {
        return DOBCus;
    }

    public void setDOBCus(String DOBCus) {
        this.DOBCus = DOBCus;
    }

    public String getEmailCus() {
        return EmailCus;
    }

    public void setEmailCus(String EmailCus) {
        this.EmailCus = EmailCus;
    }

    public String getPasswordCus() {
        return PasswordCus;
    }

    public void setPasswordCus(String PasswordCus) {
        this.PasswordCus = PasswordCus;
    }

    public String getPayment_MethodCus() {
        return Payment_MethodCus;
    }

    public void setPayment_MethodCus(String Payment_MethodCus) {
        this.Payment_MethodCus = Payment_MethodCus;
    }

    public double getSalaryCus() {
        return SalaryCus;
    }

    public void setSalaryCus(double SalaryCus) {
        this.SalaryCus = SalaryCus;
    }

    public int getPhoneCus() {
        return PhoneCus;
    }

    public void setPhoneCus(int PhoneCus) {
        this.PhoneCus = PhoneCus;
    }

}
