package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import hcmute.edu.vn.nhom6.foody_06.Fragment.CartFragment;
import hcmute.edu.vn.nhom6.foody_06.Fragment.HomeFragment;
import hcmute.edu.vn.nhom6.foody_06.Fragment.ProfileFragment;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        replaceFragment(new HomeFragment());

        binding.navBottomView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
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

}