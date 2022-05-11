package hcmute.edu.vn.nhom6.foody_06.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Activity.OrderActivity;
import hcmute.edu.vn.nhom6.foody_06.Modal.Food;
import hcmute.edu.vn.nhom6.foody_06.Modal.FoodSelected;
import hcmute.edu.vn.nhom6.foody_06.MyFunction.MyFunction;
import hcmute.edu.vn.nhom6.foody_06.R;

public class FoodSelectedAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<FoodSelected> foodSelectedList;
    Float price;
    TextView txtPrice;

    public FoodSelectedAdapter(Context context, int layout, List<FoodSelected> foodSelectedList, Float price) {
        this.context = context;
        this.layout = layout;
        this.foodSelectedList = foodSelectedList;
        txtPrice = ((OrderActivity) context).findViewById(R.id.textViewTotalMoney);
        this.price = price;
    }

    @Override
    public int getCount() {
        return foodSelectedList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        Food food = foodSelectedList.get(i).getInfoFood();

        TextView txtNameFood = (TextView) view.findViewById(R.id.textViewNameFood);
        txtNameFood.setText(food.getNameFood());

        ImageView imgImageFood = (ImageView) view.findViewById(R.id.imageViewFood);
        Bitmap bmImageStore = MyFunction.decodeImg(food.getsImage());
        imgImageFood.setImageBitmap(bmImageStore);

        TextView txtPriceFood = (TextView) view.findViewById(R.id.textViewPriceFood);
        txtPriceFood.setText(String.valueOf(food.getUnitPrice().intValue() + " VNĐ"));

        TextView textViewNumberItem;
        textViewNumberItem = (TextView) view.findViewById(R.id.textViewNumberItem);
        textViewNumberItem.setText(String.valueOf(foodSelectedList.get(i).getCount()));

        ImageView btnPlusFood = (ImageView) view.findViewById(R.id.btnPlusFood);

        btnPlusFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodSelectedList.get(i).setCount(foodSelectedList.get(i).getCount() + 1);
                price += foodSelectedList.get(i).getInfoFood().getUnitPrice();
                ((OrderActivity)context).setPrice(price);
                notifyDataSetChanged();
            }
        });

        ImageView btnMinusFood = (ImageView) view.findViewById(R.id.btnMinusFood);
        btnMinusFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(foodSelectedList.get(i).getCount() <= 0) {
                    textViewNumberItem.setText("0");
                } else {
                    foodSelectedList.get(i).setCount(foodSelectedList.get(i).getCount() - 1);
                    price -= foodSelectedList.get(i).getInfoFood().getUnitPrice();
                    ((OrderActivity)context).setPrice(price);
                    notifyDataSetChanged();
                }
            }
        });

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        int priceInt = price.intValue();
        txtPrice.setText(String.valueOf(priceInt)+" VNĐ");
    }

    public List<FoodSelected> getFoodSelectedList() {
        return foodSelectedList;
    }
}
