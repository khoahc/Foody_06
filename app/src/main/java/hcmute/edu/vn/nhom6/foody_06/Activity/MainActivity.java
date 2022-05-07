package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseHelper;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactUser;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Fragment.CartFragment;
import hcmute.edu.vn.nhom6.foody_06.Fragment.HomeFragment;
import hcmute.edu.vn.nhom6.foody_06.Fragment.ProfileFragment;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements TransactStore, TransactUser {
    private ActivityMainBinding binding;
    DatabaseHelper databaseHelper;
    int navigation_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        System.out.println(intent.getSerializableExtra("navigation_id"));
        if (intent.getSerializableExtra("navigation_id") != null) {
            navigation_id = (int) intent.getSerializableExtra("navigation_id");
        }
        switch (navigation_id){
            case 1:
                replaceFragment(new HomeFragment());
                break;
            case 2:
                replaceFragment(new CartFragment());
                break;
            case 3:
                replaceFragment(new ProfileFragment());
                break;
            default:
                replaceFragment(new HomeFragment());
        }
        //replaceFragment(new HomeFragment());
        BottomNavigationView navBottomView = findViewById(R.id.nav_bottom_view);
        navBottomView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.navigation_cart:
                        replaceFragment(new CartFragment());
                        break;
                    case R.id.navigation_profile:
                        replaceFragment(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.nav_host_fragment_activity_main, fragment);
        fragmentTransaction.commit();
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