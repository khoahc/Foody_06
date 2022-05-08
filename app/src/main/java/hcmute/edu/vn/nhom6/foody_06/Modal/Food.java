package hcmute.edu.vn.nhom6.foody_06.Modal;

import java.io.Serializable;

public class Food implements Serializable {
    private Integer id;
    private String nameFood;
    private Float unitPrice;
    private byte[] image;
    private boolean state;
    private Integer idStore;

    public Food(Integer id, String nameFood, Float unitPrice, byte[] image, boolean state, Integer idStore) {
        this.id = id;
        this.nameFood = nameFood;
        this.unitPrice = unitPrice;
        this.image = image;
        this.state = state;
        this.idStore = idStore;
    }

    public Food(String nameFood, Float unitPrice, byte[] image, boolean state, Integer idStore) {
        this.nameFood = nameFood;
        this.unitPrice = unitPrice;
        this.image = image;
        this.state = state;
        this.idStore = idStore;
    }

    public Food() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
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

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    }
}
