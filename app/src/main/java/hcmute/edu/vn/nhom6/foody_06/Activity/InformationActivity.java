package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Fragment.CartFragment;
import hcmute.edu.vn.nhom6.foody_06.Fragment.ProfileFragment;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.ActivityInformationBinding;

public class InformationActivity extends AppCompatActivity {
    User user;
    ActivityInformationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("infoUser");

        loadData(user);

        binding.buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.buttonSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfoUser();
                finish();
            }
        });
    }

    private void loadData(User user) {
        binding.editTextFullName.setText(user.getFullName());
        binding.editTextAddress.setText(user.getAddress());
        binding.textViewPhoneNumber.setText(user.getPhoneNumber());
    }

    private void saveInfoUser() {
        String fullName = binding.editTextFullName.getText().toString();
        String address = binding.editTextAddress.getText().toString();
        String phoneNumber = user.getPhoneNumber();
        User user = new User(phoneNumber, address, fullName);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        if(databaseAccess.updateUser(user)){
            Toast.makeText(InformationActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            Log.d("updateuser", "thanhcong");
            user.setAddress(address);
            user.setFullName(fullName);
            Intent intent = getIntent();
            intent.putExtra("infoUser", user);
        } else {
            Toast.makeText(InformationActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            Log.d("updateuser", "thatbai");
        }
        databaseAccess.close();
    }
}