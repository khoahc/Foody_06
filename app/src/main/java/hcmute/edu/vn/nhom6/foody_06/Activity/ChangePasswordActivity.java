package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    String oldPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        oldPwd = getIntent().getStringExtra("oPwd");
        setListeners();

    }

    private void setListeners(){
        binding.buttonReturn.setOnClickListener(v -> onBackPressed());
        binding.buttonSaveInfo.setOnClickListener(v ->{
            if(!isValidInfo())
                return;
            changePassword();
            Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private boolean isValidInfo() {
        if(binding.editOldPwd.getText().toString().trim().isEmpty() ||
                binding.editPwd.getText().toString().trim().isEmpty() ||
                binding.editConfirmPwd.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Hãy điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!binding.editPwd.getText().toString().trim()
                .equals(binding.editConfirmPwd.getText().toString().trim())){
            Toast.makeText(this, "Nhập lại mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!binding.editOldPwd.getText().toString().trim().equals(oldPwd)){
            Toast.makeText(this, "Sai mật khẩu!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }

    private void changePassword(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        databaseAccess.updateUserPassword(getIntent().getIntExtra("idUser", -1), binding.editPwd.getText().toString().trim());
        databaseAccess.close();
    }

}