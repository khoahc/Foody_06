package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import hcmute.edu.vn.nhom6.foody_06.Adapter.FoodSelectedAdapter;
import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Modal.Food;
import hcmute.edu.vn.nhom6.foody_06.Modal.FoodSelected;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;

public class OrderActivity extends AppCompatActivity {
    ImageView btnReturn;
    Store store;
    User user;
    EditText txtAddress, txtPhoneNumber;
    ListView listViewFoodSelected;
    TextView txtPrice;
    Button btnOrder;
    Float price = 0.0f;
    FoodSelectedAdapter adapterFoodSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        store = (Store) intent.getSerializableExtra("infoStore");
        user = (User) intent.getSerializableExtra("infoUser");
        HashMap<Food,Integer> mapFoodSelected = (HashMap<Food,Integer>) intent.getSerializableExtra("mapFoodSelected");

        Log.d("mapFoodSelected1", mapFoodSelected.toString());
        ArrayList<FoodSelected> listFoodSelected = new ArrayList<>();

        for (Food i: mapFoodSelected.keySet()) {
            FoodSelected foodSelected = new FoodSelected(i, mapFoodSelected.get(i));
            price += i.getUnitPrice() * mapFoodSelected.get(i);
            listFoodSelected.add(foodSelected);
        }

        Log.d("mapFoodSelected2", listFoodSelected.size() + "");

        anhXa();
        loadData();
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, StoreDetailActivity.class);
                putData(intent);
            }
        });

        listViewFoodSelected = findViewById(R.id.listViewFoodSelected);
        adapterFoodSelected = new FoodSelectedAdapter(
                OrderActivity.this,
                R.layout.viewholder_menu,
                listFoodSelected,
                price
        );

        listViewFoodSelected.setAdapter(adapterFoodSelected);
        txtPrice.setText(String.valueOf(price.intValue())+" VNĐ");

        btnOrder.setOnClickListener(v -> {
            order();
            onBackPressed();
        });

    }

    private void anhXa(){
        btnReturn = (ImageView) findViewById(R.id.buttonReturn);
        txtAddress = (EditText) findViewById(R.id.editTextAddress);
        txtPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        listViewFoodSelected = (ListView) findViewById(R.id.listViewFoodSelected);
        btnOrder = findViewById(R.id.buttonOrder);
        txtPrice = findViewById(R.id.textViewTotalMoney);
    }

    private void loadData(){
        txtAddress.setText(user.getAddress());
        txtPhoneNumber.setText(user.getPhoneNumber());
    }

    private void putData(Intent intent) {
        //put info store
        intent.putExtra("infoStore", store);
        //put info user
        intent.putExtra("infoUser", user);
        startActivity(intent);
    }

    private void order(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        long rowId = databaseAccess.addToCart(user.getId(), user.getAddress(), user.getPhoneNumber(), price);

        if(rowId != -1 ){
            for (FoodSelected i: adapterFoodSelected.getFoodSelectedList()) {
                databaseAccess.addToCartDetail(rowId, i.getInfoFood().getId(), i.getCount());
            }
            Toast.makeText(getApplicationContext(), "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
        }
        databaseAccess.open();
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}