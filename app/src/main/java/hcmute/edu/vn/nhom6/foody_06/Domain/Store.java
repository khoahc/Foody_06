package hcmute.edu.vn.nhom6.foody_06.Domain;

import android.content.Context;
import android.view.LayoutInflater;

public class Store {
    private String name;
    private Integer image = 0;
    private String lastComment;

    public Store(String name, String lastComment, Integer image) {
        this.name = name;
        this.image = image;
        this.lastComment = lastComment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getLastComment() {
        return lastComment;
    }

    public void setLastComment(String lastComment) {
        this.lastComment = lastComment;
    }
}
