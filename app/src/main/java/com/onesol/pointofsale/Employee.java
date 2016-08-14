package com.onesol.pointofsale;

/**
 * Created by Neal on 8/14/2016.
 */
public class Employee {
    private int EmployeeID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String SSN;
    private String PIN;
    private String dateCreated;
    private boolean activate;

    public Employee(int ID, String first, String last, String address, String phoneNumber,
                    String SSN, String PIN, String dateCreated, int activated)
    {
        setEmployeeID(ID);
        setFirstName(first);
        setLastName(last);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setSSN(SSN);
        setPIN(PIN);
        setDateCreated(dateCreated);
        setActivate(activated == 0? false:true);
    }

    public Employee() {

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }
}
