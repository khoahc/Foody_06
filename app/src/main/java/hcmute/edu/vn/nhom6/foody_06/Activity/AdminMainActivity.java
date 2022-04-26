package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import hcmute.edu.vn.nhom6.foody_06.Domain.Store;
import hcmute.edu.vn.nhom6.foody_06.Fragment.AdminHomeFragment;
import hcmute.edu.vn.nhom6.foody_06.Fragment.CartFragment;
import hcmute.edu.vn.nhom6.foody_06.Fragment.ProfileFragment;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.ActivityAdminMainBinding;

public class AdminMainActivity extends AppCompatActivity implements TransactStore{
    private ActivityAdminMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        replaceFragment(new AdminHomeFragment());

        binding.navBottomView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    replaceFragment(new AdminHomeFragment());
                    break;
                case R.id.navigation_cart:
                    replaceFragment(new CartFragment());
                    break;
                case R.id.navigation_profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.nav_host_fragment_activity_admin_main, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void DataStore(Store store) {
        Intent intent = new Intent(AdminMainActivity.this, AdminStoreDetailActivity.class);
        intent.putExtra("infoStore", store);
        startActivity(intent);
        finish();
    }

}