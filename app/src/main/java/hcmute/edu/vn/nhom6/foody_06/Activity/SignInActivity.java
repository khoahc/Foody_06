package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Fragment.HomeFragment;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.R;

public class SignInActivity extends AppCompatActivity {

    private MaterialButton btnSignIn, btnSignInAdmin;
    private EditText phoneNumber, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn = (MaterialButton) findViewById(R.id.buttonSignIn);
        btnSignInAdmin = (MaterialButton) findViewById(R.id.buttonSignInAdmin);
        phoneNumber = (EditText) findViewById(R.id.inputPhoneNumber);
        password = (EditText) findViewById(R.id.inputPassword);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();

                String phoneUserLogin = databaseAccess.loginUser(phoneNumber.getText().toString(),
                        password.getText().toString());
                databaseAccess.close();
                if(!phoneUserLogin.equals("")){
                    Toast.makeText(SignInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    intent.putExtra("phoneNumberUser", phoneUserLogin);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(SignInActivity.this, "Tài khoản không chính xác", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSignInAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, AdminMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}