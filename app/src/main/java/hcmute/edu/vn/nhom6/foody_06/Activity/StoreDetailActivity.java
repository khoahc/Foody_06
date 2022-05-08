package hcmute.edu.vn.nhom6.foody_06.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Adapter.CommentAdapter;
import hcmute.edu.vn.nhom6.foody_06.Adapter.FoodAdapter;
import hcmute.edu.vn.nhom6.foody_06.Adapter.StoreAdapter;
import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Fragment.HomeFragment;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Modal.Comment;
import hcmute.edu.vn.nhom6.foody_06.Modal.Food;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;

public class StoreDetailActivity extends AppCompatActivity{
    ListView listViewMenu, listViewComment;
    List<Food> listFood = new ArrayList<Food>();
    List<Comment> listComment = new ArrayList<Comment>();
    ImageView btnReturn, imageStore;
    TextView txtNameStore, txtAddressStore, txtTitleNameStore;
    EditText editTxtComment;
    Button btnOrder, btnComment;
    Store store;
    User user;
    CommentAdapter adapterComment;
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

        //get list menu, list comment
        readData();
        addControls();

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

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Return true if comment was successful
                boolean commentSuccess = comment(editTxtComment.getText().toString(), user.getId(), store.getId());

                if(commentSuccess){
                    Toast.makeText(StoreDetailActivity.this, "Bình luận thành công", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
                else {
                    Toast.makeText(StoreDetailActivity.this, "Bình luận thất bại", Toast.LENGTH_SHORT).show();
                }
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

        editTxtComment = (EditText) findViewById(R.id.editTextComment);
        btnComment = (Button) findViewById(R.id.buttonComment);

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

    private void addControls(){
        //adapter menu
        listViewMenu = findViewById(R.id.listViewMenu);
        FoodAdapter adapterMenu = new FoodAdapter(
                StoreDetailActivity.this,
                R.layout.viewholder_menu,
                listFood
        );

        listViewMenu.setAdapter(adapterMenu);

        //adapter comment
        listViewComment = findViewById(R.id.listViewComment);
        adapterComment = new CommentAdapter(
                StoreDetailActivity.this,
                R.layout.viewholder_comment,
                listComment
        );

        listViewComment.setAdapter(adapterComment);

    }

    private void readData() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(StoreDetailActivity.this);
        databaseAccess.open();

        listFood = databaseAccess.getListFood(store.getId());
        listComment = databaseAccess.getListComment(store.getId());
    }


    private boolean comment(String comment, int idUser, int idStore){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(StoreDetailActivity.this);
        databaseAccess.open();
        boolean addComment = databaseAccess.insertComment(comment, idUser, idStore);
        return addComment;
    }
}
