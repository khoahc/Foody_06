package hcmute.edu.vn.nhom6.foody_06.Modal;

import java.io.Serializable;

public class Store implements Serializable {
    private String name;
    private String addressStore;
    private byte[] image;
    private boolean state;

    public Store(String name, String address, byte[] image, boolean state) {
        this.name = name;
        this.addressStore = address;
        this.image = image;
        this.state = state;
    }

    public Store() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return addressStore;
    }

    public void setAddress(String address) {
        this.addressStore = address;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
