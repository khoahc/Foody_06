package hcmute.edu.vn.nhom6.foody_06.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Activity.StoreDetailActivity;
import hcmute.edu.vn.nhom6.foody_06.Modal.Food;
import hcmute.edu.vn.nhom6.foody_06.Modal.FoodSelected;
import hcmute.edu.vn.nhom6.foody_06.R;

public class FoodSelectedAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<FoodSelected> foodSelectedsList;

    public FoodSelectedAdapter(Context context, int layout, List<FoodSelected> foodSelectedsList) {
        this.context = context;
        this.layout = layout;
        this.foodSelectedsList = foodSelectedsList;
    }

    @Override
    public int getCount() {
        return foodSelectedsList.size();
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
        Food food = foodSelectedsList.get(i).getInfoFood();

        TextView txtNameFood = (TextView) view.findViewById(R.id.textViewNameFood);
        txtNameFood.setText(food.getNameFood());

        ImageView imgImageFood = (ImageView) view.findViewById(R.id.imageViewFood);
        Bitmap bmImageStore = BitmapFactory.decodeByteArray(food.getImage(),
                0, food.getImage().length);
        imgImageFood.setImageBitmap(bmImageStore);

        TextView txtPriceFood = (TextView) view.findViewById(R.id.textViewPriceFood);
        txtPriceFood.setText(String.valueOf(food.getUnitPrice() + " VNĐ"));

        TextView textViewNumberItem;
        textViewNumberItem = (TextView) view.findViewById(R.id.textViewNumberItem);
        textViewNumberItem.setText(String.valueOf(foodSelectedsList.get(i).getCount()));

        final int[] number = {Integer.parseInt(textViewNumberItem.getText().toString())};
        ImageView btnPlusFood = (ImageView) view.findViewById(R.id.btnPlusFood);
        btnPlusFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++number[0];
                textViewNumberItem.setText(String.valueOf(number[0]));
            }
        });

        ImageView btnMinusFood = (ImageView) view.findViewById(R.id.btnMinusFood);
        btnMinusFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number[0] <= 0) {
                    textViewNumberItem.setText("0");
                } else {
                    --number[0];
                    textViewNumberItem.setText(String.valueOf(number[0]));
                }
            }
        });

        return view;
    }
}
