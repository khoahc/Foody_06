package hcmute.edu.vn.nhom6.foody_06.Modal;

import java.io.Serializable;

public class User implements Serializable {
    private String phoneNumber;
    private String password;
    private String address;
    private String fullName;

    public User(String phoneNumber, String password, String address, String fullName) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.fullName = fullName;
    }

    public User(String phoneNumber, String address, String fullName) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
