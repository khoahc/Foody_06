package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;

public class OrderActivity extends AppCompatActivity {
    ImageView btnReturn;
    Store store;
    User user;
    EditText txtAddress, txtPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        store = (Store) intent.getSerializableExtra("infoStore");
        user = (User) intent.getSerializableExtra("infoUser");
        Log.d("user", user.toString());
        anhXa();
        loadData();
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, StoreDetailActivity.class);
                putData(intent);
            }
        });
    }

    private void anhXa(){
        btnReturn = (ImageView) findViewById(R.id.buttonReturn);
        txtAddress = (EditText) findViewById(R.id.editTextAddress);
        txtPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
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

}