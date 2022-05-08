package hcmute.edu.vn.nhom6.foody_06.Modal;

import java.io.Serializable;

public class FoodSelected implements Serializable {
    private Integer id;
    private Food infoFood;
    private int count;

    public FoodSelected(Integer id, Food infoFood, int count) {
        this.id = id;
        this.infoFood = infoFood;
        this.count = count;
    }

    public FoodSelected(){}

    public FoodSelected(Food infoFood, int count) {
        this.infoFood = infoFood;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Food getInfoFood() {
        return infoFood;
    }

    public void setInfoFood(Food infoFood) {
        this.infoFood = infoFood;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
