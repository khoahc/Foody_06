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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hcmute.edu.vn.nhom6.foody_06.Adapter.CommentAdapter;
import hcmute.edu.vn.nhom6.foody_06.Adapter.FoodAdapter;
import hcmute.edu.vn.nhom6.foody_06.Adapter.StoreAdapter;
import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Fragment.HomeFragment;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Modal.Comment;
import hcmute.edu.vn.nhom6.foody_06.Modal.Food;
import hcmute.edu.vn.nhom6.foody_06.Modal.FoodSelected;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.MyFunction.MyFunction;
import hcmute.edu.vn.nhom6.foody_06.R;

public class StoreDetailActivity extends AppCompatActivity implements FoodAdapter.OnFoodSelectedListener{
    ListView listViewMenu, listViewComment;
    List<Food> listFood = new ArrayList<Food>();
    HashMap<Food, Integer> mapFoodSelected = new HashMap<Food, Integer>();
    List<Comment> listComment = new ArrayList<Comment>();
    ImageView btnReturn, imageStore;
    TextView txtNameStore, txtAddressStore, txtTitleNameStore;
    EditText editTxtComment;
    Button btnOrder, btnComment;
    Store store;
    User user;
    CommentAdapter adapterComment;
    DatabaseAccess databaseAccess;
    List<FoodSelected> listFoodSelected = new ArrayList<FoodSelected>();
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
                    adapterComment.setCommentList(databaseAccess.getListComment(store.getId()));
                    adapterComment.notifyDataSetChanged();
                    editTxtComment.setText(null);
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
        Bitmap bmImageStore = MyFunction.decodeImg(store.getImage());
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

       //put list food selected
       intent.putExtra("mapFoodSelected", mapFoodSelected);

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
        databaseAccess = DatabaseAccess.getInstance(StoreDetailActivity.this);
        databaseAccess.open();

        listFood = databaseAccess.getListFood(store.getId());
        listComment = databaseAccess.getListComment(store.getId());

//        List<Food> allFood = databaseAccess.getAllFood();
//        for (Food i: allFood) {
//            Bitmap bitmap = MyFunction.decodeImg(i.getsImage());
//            i.setsImage(MyFunction.encodeImg(bitmap, 250));
//            databaseAccess.updateFood(i);
//        }
//
//        List<Store> allStore = databaseAccess.getListStore();
//        for (Store i: allStore) {
//
//            Bitmap bitmap = MyFunction.decodeImg(i.getImage());
//            i.setImage(MyFunction.encodeImg(bitmap, 400));
//            databaseAccess.updateStore(i);
//        }

    }

    private boolean comment(String comment, int idUser, int idStore){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(StoreDetailActivity.this);
        databaseAccess.open();
        boolean addComment = databaseAccess.insertComment(comment, idUser, idStore);
        return addComment;
    }

    @Override
    public void onFoodSelected(Intent intent) {
        Food foodSelected = (Food) intent.getSerializableExtra("foodSelected");
        boolean isIncrease = intent.getBooleanExtra("isIncrease", false);

        if(mapFoodSelected.get(foodSelected) != null) {
            if(isIncrease) {
                mapFoodSelected.put(foodSelected, mapFoodSelected.get(foodSelected) + 1);
            } else {
                int count = mapFoodSelected.get(foodSelected) - 1;
                if(count > 0) {
                    mapFoodSelected.put(foodSelected, mapFoodSelected.get(foodSelected) - 1);
                } else {
                    mapFoodSelected.remove(foodSelected);
                }
            }
        } else {
            mapFoodSelected.put(foodSelected, 1);
        }
        Log.d("mapFoodSelected", mapFoodSelected.toString());

//        //update list food selected
//        if(listFoodSelected.size() > 0) {
//            for (int i = 0; i < listFoodSelected.size(); i++) {
//                if (listFoodSelected.get(i).getInfoFood().getId().equals(foodSelected.getId())) {
//                    if (isIncrease) {
//                        listFoodSelected.get(i).setCount(listFoodSelected.get(i).getCount() + 1);
//                    } else {
//                        int count = listFoodSelected.get(i).getCount() - 1;
//                        if (count > 0) {
//                            listFoodSelected.get(i).setCount(listFoodSelected.get(i).getCount() - 1);
//                        } else {
//                            listFoodSelected.remove(i);
//                        }
//                    }
//                } else {
//                    if (isIncrease) {
//                        listFoodSelected.add(new FoodSelected(foodSelected, 1));
//                    } else {
//                        listFoodSelected.add(new FoodSelected(foodSelected, 0));
//                    }
//                }
//            }
//        } else {
//            if (isIncrease) {
//                listFoodSelected.add(new FoodSelected(foodSelected, 1));
//            } else {
//                listFoodSelected.add(new FoodSelected(foodSelected, 0));
//            }
//        }

    }
}
