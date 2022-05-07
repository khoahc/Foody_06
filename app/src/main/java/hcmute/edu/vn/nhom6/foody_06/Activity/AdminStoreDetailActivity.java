package hcmute.edu.vn.nhom6.foody_06.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;

public class AdminStoreDetailActivity extends AppCompatActivity implements TransactStore {
    ImageView btnReturn;
    EditText editNameStore, editAddressStore;
    TextView txtTitleNameStore;
    Store store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_store_detail);

        Intent intent = getIntent();

        store = (Store) intent.getSerializableExtra("infoStore");
        anhXa();
        setInfo(store);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminStoreDetailActivity.this, AdminMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void setInfo(Store store) {
        String name = store.getName();
        editNameStore.setText(name);
        txtTitleNameStore.setText(name);
        editAddressStore.setText(store.getAddress());
    }

    private void anhXa(){
        editNameStore = (EditText) findViewById(R.id.editTextNameStore);
        btnReturn = (ImageView) findViewById(R.id.buttonReturn);
        txtTitleNameStore = (TextView) findViewById(R.id.textViewTitleNameStore);
        editAddressStore = (EditText) findViewById(R.id.editTextAddressStore);

    }

    @Override
    public void DataStore(Store store) {
        Intent intent = new Intent(AdminStoreDetailActivity.this, OrderActivity.class);
        intent.putExtra("infoStore", store);
        startActivity(intent);
        finish();
    }


}
