package hcmute.edu.vn.nhom6.foody_06.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.R;

public class StoreDetailActivity extends AppCompatActivity implements TransactStore {
    ImageView btnReturn;
    TextView txtNameStore, txtAddressStore, txtTitleNameStore;
    Button btnOrder;
    Store store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        Intent intent = getIntent();

        store = (Store) intent.getSerializableExtra("infoStore");
        anhXa();
        setInfo(store);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataStore(store);
//                Intent intent = new Intent(StoreDetailActivity.this, OrderActivity.class);
//                startActivity(intent);
                finish();
            }
        });

    }

    public void setInfo(Store store) {
        String name = store.getName();
        txtNameStore.setText(name);
        txtTitleNameStore.setText(name);
        txtAddressStore.setText(store.getAddress());
    }

    private void anhXa(){
        txtNameStore = (TextView) findViewById(R.id.textViewNameStore);
        btnReturn = (ImageView) findViewById(R.id.buttonReturn);
        btnOrder = (Button) findViewById(R.id.buttonOrder);
        txtTitleNameStore = (TextView) findViewById(R.id.textViewTitleNameStore);
        txtAddressStore = (TextView) findViewById(R.id.textViewAddressStore);
    }

    @Override
    public void DataStore(Store store) {
        Intent intent = new Intent(StoreDetailActivity.this, OrderActivity.class);
        intent.putExtra("infoStore", store);
        startActivity(intent);
        finish();
    }
}
