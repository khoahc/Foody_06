package hcmute.edu.vn.nhom6.foody_06.Modal;

import java.io.Serializable;

public class Store implements Serializable {
    private Integer id;
    private String name;
    private String addressStore;
    private String image;
    private boolean state;

    public Store(Integer id, String name, String address, String image, boolean state) {
        this.id = id;
        this.name = name;
        this.addressStore = address;
        this.image = image;
        this.state = state;
    }

    public Store(String name, String address, String image, boolean state) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
