package com.example.workingwitsqlite;

public class PhoneBook{
    private int ID;
    private String phoneNumber ;
    private String userNamephone;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserNamephone() {
        return userNamephone;
    }

    public void setUserNamephone(String userNamephone) {
        this.userNamephone = userNamephone;
    }

    public PhoneBook(int ID, String phoneNumber, String userNamephone) {
        this.ID = ID;
        this.phoneNumber = phoneNumber;
        this.userNamephone = userNamephone;
    }
    public PhoneBook() {};
}
