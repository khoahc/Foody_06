package hcmute.edu.vn.nhom6.foody_06.Modal;

import java.io.Serializable;

public class Comment implements Serializable {
    private Integer id;
    private String content;
    private String timeCreate;
    private Integer idUser;
    private Integer idStore;

    public Comment(Integer id, String content, String timeCreate, Integer idUser, Integer idStore) {
        this.id = id;
        this.content = content;
        this.timeCreate = timeCreate;
        this.idUser = idUser;
        this.idStore = idStore;
    }

    public Comment(String content, Integer idUser, Integer idStore) {
        this.content = content;
        this.idUser = idUser;
        this.idStore = idStore;
    }

    public Comment(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    }
}
