package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        binding.back.setOnClickListener(v -> onBackPressed());
        binding.buttonSignUp.setOnClickListener(v -> {
            if(!isValidInfo())
                return;
            signUp();
            Toast.makeText(getApplicationContext(), "Đăng kí tài khoản thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void signUp() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        databaseAccess.signUp(
                binding.inputPhoneNumber.getText().toString().trim(),
                binding.inputPassword.getText().toString().trim(),
                binding.inputAddress.getText().toString().trim(),
                binding.inputName.getText().toString().trim());
        databaseAccess.close();
    }

    private boolean isValidInfo() {
        if(binding.inputName.getText().toString().trim().isEmpty() ||
                binding.inputPhoneNumber.getText().toString().trim().isEmpty() ||
                binding.inputAddress.getText().toString().trim().isEmpty()||
                binding.inputPassword.getText().toString().trim().isEmpty() ||
                binding.inputConfirmPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Hãy điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!binding.inputPassword.getText().toString().trim()
                .equals(binding.inputConfirmPassword.getText().toString().trim())){
            Toast.makeText(this, "Nhập lại mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }
}