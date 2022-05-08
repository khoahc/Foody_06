package hcmute.edu.vn.nhom6.foody_06.Modal;

import java.io.Serializable;

public class Cart implements Serializable {
    private Integer id;
    private String deliveryAddress;
    private String phoneNumber;
    private Float totalPrice;
    private String timeCreate;
    private Integer idUser;

    public Cart(Integer id, String deliveryAddress, String phoneNumber, Float totalPrice, String timeCreate, Integer idUser) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
        this.timeCreate = timeCreate;
        this.idUser = idUser;
    }

    public Cart(){
    }

    public Cart(String deliveryAddress, String phoneNumber, Float totalPrice, String timeCreate, Integer idUser) {
        this.deliveryAddress = deliveryAddress;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
        this.timeCreate = timeCreate;
        this.idUser = idUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
