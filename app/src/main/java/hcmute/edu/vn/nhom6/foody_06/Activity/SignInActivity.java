package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

import hcmute.edu.vn.nhom6.foody_06.Fragment.HomeFragment;
import hcmute.edu.vn.nhom6.foody_06.R;

public class SignInActivity extends AppCompatActivity {

    private MaterialButton btnSignIn, btnSignInAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn = (MaterialButton) findViewById(R.id.buttonSignIn);
        btnSignInAdmin = (MaterialButton) findViewById(R.id.buttonSignInAdmin);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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