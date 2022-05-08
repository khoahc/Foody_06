package hcmute.edu.vn.nhom6.foody_06.Modal;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Integer id;
    private String nameStore;
    private Float totalPrice;
    private String timeCreate;

    public CartItem(Integer id, String nameStore, Float totalPrice, String timeCreate) {
        this.id = id;
        this.nameStore = nameStore;
        this.totalPrice = totalPrice;
        this.timeCreate = timeCreate;
    }

    public CartItem(){}

    public CartItem(String nameStore, Float totalPrice, String timeCreate) {
        this.nameStore = nameStore;
        this.totalPrice = totalPrice;
        this.timeCreate = timeCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }
}
