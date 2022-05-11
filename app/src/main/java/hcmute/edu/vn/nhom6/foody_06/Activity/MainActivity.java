package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationBarView;

import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactUser;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;

public class MainActivity extends AppCompatActivity implements TransactStore, TransactUser {
    int navigation_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        System.out.println(intent.getSerializableExtra("navigation_id"));

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.cartFragment, R.id.profileFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationBarView nav_bar = findViewById(R.id.nav_bottom_view);
        NavigationUI.setupWithNavController(nav_bar, navController);

    }


    @Override
    public void DataStore(Store store) {
        Intent intent = new Intent(MainActivity.this, StoreDetailActivity.class);
        intent.putExtra("infoStore", store);
        startActivity(intent);
        finish();
    }

    @Override
    public User getDataUser() {
        Intent intent = getIntent();
        String phoneNumberUser = (String) intent.getSerializableExtra("phoneNumberUser");
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        User user = databaseAccess.findUserByPhoneNumber(phoneNumberUser);
        databaseAccess.close();
        return user;
    }


}