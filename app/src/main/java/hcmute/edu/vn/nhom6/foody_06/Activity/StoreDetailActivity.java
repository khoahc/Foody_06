package hcmute.edu.vn.nhom6.foody_06.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;

public class StoreDetailActivity extends AppCompatActivity{
    ImageView btnReturn, imageStore;
    TextView txtNameStore, txtAddressStore, txtTitleNameStore;
    Button btnOrder;
    Store store;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        Intent intent = getIntent();
        //get data store
        store = (Store) intent.getSerializableExtra("infoStore");
        //get data user
        user = (User) intent.getSerializableExtra("infoUser");

        anhXa();
        setInfo(store);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreDetailActivity.this, MainActivity.class);
                intent.putExtra("phoneNumberUser", user.getPhoneNumber());
                startActivity(intent);
                finish();
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreDetailActivity.this, OrderActivity.class);
                putData(intent);
            }
        });

    }

    public void setInfo(Store store) {
        String name = store.getName();
        txtNameStore.setText(name);
        txtTitleNameStore.setText(name);
        txtAddressStore.setText(store.getAddress());
        Bitmap bmImageStore = BitmapFactory.decodeByteArray(store.getImage(),
                0, store.getImage().length);
        imageStore.setImageBitmap(bmImageStore);
    }

    private void anhXa(){
        txtNameStore = (TextView) findViewById(R.id.textViewNameStore);
        btnReturn = (ImageView) findViewById(R.id.buttonReturn);
        btnOrder = (Button) findViewById(R.id.buttonOrder);
        txtTitleNameStore = (TextView) findViewById(R.id.textViewTitleNameStore);
        txtAddressStore = (TextView) findViewById(R.id.textViewAddressStore);
        imageStore = (ImageView) findViewById(R.id.imageViewStore);
    }

   private void putData(Intent intent) {
       //put info store
       intent.putExtra("infoStore", store);
       //put info user
       intent.putExtra("infoUser", user);
       startActivity(intent);
   }

}
