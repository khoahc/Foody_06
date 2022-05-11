package hcmute.edu.vn.nhom6.foody_06.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Activity.OrderActivity;
import hcmute.edu.vn.nhom6.foody_06.Activity.StoreDetailActivity;
import hcmute.edu.vn.nhom6.foody_06.Modal.Food;
import hcmute.edu.vn.nhom6.foody_06.Modal.FoodSelected;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.MyFunction.MyFunction;
import hcmute.edu.vn.nhom6.foody_06.R;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Food> foodList;
    private OnFoodSelectedListener onFoodSelectedListener;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;

        try {
            this.onFoodSelectedListener = (OnFoodSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage());
        }

    }

    @Override
    public int getCount() {
        return foodList.size();
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

        TextView txtNameFood = (TextView) view.findViewById(R.id.textViewNameFood);
        txtNameFood.setText(foodList.get(i).getNameFood());

        ImageView imgImageFood = (ImageView) view.findViewById(R.id.imageViewFood);
        Bitmap bmImageStore = MyFunction.decodeImg(foodList.get(i).getsImage());
        imgImageFood.setImageBitmap(bmImageStore);

        TextView textViewNumberItem;
        TextView txtPriceFood = (TextView) view.findViewById(R.id.textViewPriceFood);
        int price = foodList.get(i).getUnitPrice().intValue();
        txtPriceFood.setText(String.valueOf(price + " VNĐ"));


        textViewNumberItem = (TextView) view.findViewById(R.id.textViewNumberItem);
        textViewNumberItem.setText("0");

        final int[] number = {Integer.parseInt(textViewNumberItem.getText().toString())};
        ImageView btnPlusFood = (ImageView) view.findViewById(R.id.btnPlusFood);
        btnPlusFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++number[0];
                textViewNumberItem.setText(String.valueOf(number[0]));
                Intent intent = new Intent();
                FoodSelected foodSelected = new FoodSelected(foodList.get(i), number[0]);

                intent.putExtra("foodSelected", foodList.get(i));
                intent.putExtra("isIncrease", true);
                onFoodSelectedListener.onFoodSelected(intent);
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
                    Intent intent = new Intent();
                    intent.putExtra("foodSelected", foodList.get(i));
                    intent.putExtra("isIncrease", false);
                    onFoodSelectedListener.onFoodSelected(intent);
                }

            }
        });
;
        return view;
    }

    public interface OnFoodSelectedListener {
        void onFoodSelected(Intent intent);
    }

}
