package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.R;

public class OrderActivity extends AppCompatActivity implements TransactStore {
    ImageView btnReturn;
    Store store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        store = (Store) intent.getSerializableExtra("infoStore");
        anhXa();
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(OrderActivity.this, StoreDetailActivity.class);
//                startActivity(intent);
                DataStore(store);
                finish();
            }
        });
    }

    private void anhXa(){
        btnReturn = (ImageView) findViewById(R.id.buttonReturn);

    }

    @Override
    public void DataStore(Store store) {
        Intent intent = new Intent(OrderActivity.this, StoreDetailActivity.class);
        intent.putExtra("infoStore", store);
        startActivity(intent);
        finish();
    }
}