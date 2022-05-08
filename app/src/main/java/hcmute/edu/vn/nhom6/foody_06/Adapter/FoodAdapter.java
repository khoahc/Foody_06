package hcmute.edu.vn.nhom6.foody_06.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Modal.Food;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.R;

public class FoodAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Food> foodList;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
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
        Bitmap bmImageStore = BitmapFactory.decodeByteArray(foodList.get(i).getImage(),
                0, foodList.get(i).getImage().length);
        imgImageFood.setImageBitmap(bmImageStore);

        TextView txtPriceFood = (TextView) view.findViewById(R.id.textViewPriceFood);
        int price = foodList.get(i).getUnitPrice().intValue();
        txtPriceFood.setText(price + " VNĐ");

        TextView textViewNumberItem;
        textViewNumberItem = (TextView) view.findViewById(R.id.textViewNumberItem);
        textViewNumberItem.setText("0");

        ImageView btnPlusFood = (ImageView) view.findViewById(R.id.btnPlusFood);
        btnPlusFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(textViewNumberItem.getText().toString());
                textViewNumberItem.setText(++number + "");
            }
        });

        ImageView btnMinusFood = (ImageView) view.findViewById(R.id.btnMinusFood);
        btnMinusFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(textViewNumberItem.getText().toString());
                if(number <= 0) {
                    textViewNumberItem.setText("0");
                } else {
                    textViewNumberItem.setText(--number + "");
                }
            }
        });

        return view;
    }
}
