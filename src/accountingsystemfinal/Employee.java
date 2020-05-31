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
public class Employee {

    private int IDEmp;
    private String NameEmp;
    private String DOBEmp;
    private String EmaiEmpl;
    private String PasswordEmp;
    private double SalaryEmp;
    private int PhoneEmp;

    public Employee(int IDEmp, String NameEmp, String DOBEmp, String EmaiEmpl, String PasswordEmp, double SalaryEmp, int PhoneEmp) {
        this.IDEmp = IDEmp;
        this.NameEmp = NameEmp;
        this.DOBEmp = DOBEmp;
        this.EmaiEmpl = EmaiEmpl;
        this.PasswordEmp = PasswordEmp;
        this.SalaryEmp = SalaryEmp;
        this.PhoneEmp = PhoneEmp;
    }

    public int getIDEmp() {
        return IDEmp;
    }

    public void setIDEmp(int IDEmp) {
        this.IDEmp = IDEmp;
    }

    public String getNameEmp() {
        return NameEmp;
    }

    public void setNameEmp(String NameEmp) {
        this.NameEmp = NameEmp;
    }

    public String getDOBEmp() {
        return DOBEmp;
    }

    public void setDOBEmp(String DOBEmp) {
        this.DOBEmp = DOBEmp;
    }

    public String getEmaiEmpl() {
        return EmaiEmpl;
    }

    public void setEmaiEmpl(String EmaiEmpl) {
        this.EmaiEmpl = EmaiEmpl;
    }

    public String getPasswordEmp() {
        return PasswordEmp;
    }

    public void setPasswordEmp(String PasswordEmp) {
        this.PasswordEmp = PasswordEmp;
    }

    public double getSalaryEmp() {
        return SalaryEmp;
    }

    public void setSalaryEmp(double SalaryEmp) {
        this.SalaryEmp = SalaryEmp;
    }

    public int getPhoneEmp() {
        return PhoneEmp;
    }

    public void setPhoneEmp(int PhoneEmp) {
        this.PhoneEmp = PhoneEmp;
    }

}
