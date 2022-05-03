package hcmute.edu.vn.nhom6.foody_06.Modal;

import java.io.Serializable;

public class Store implements Serializable {
    private String name;
    private String address;
    private String image = "";
    private String lastComment;
    private boolean state;

    public Store(String name, String address, String image, String lastComment, boolean state) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.lastComment = lastComment;
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
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLastComment() {
        return lastComment;
    }

    public void setLastComment(String lastComment) {
        this.lastComment = lastComment;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
