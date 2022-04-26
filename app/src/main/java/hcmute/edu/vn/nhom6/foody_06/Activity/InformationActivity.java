package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import hcmute.edu.vn.nhom6.foody_06.Fragment.CartFragment;
import hcmute.edu.vn.nhom6.foody_06.Fragment.ProfileFragment;
import hcmute.edu.vn.nhom6.foody_06.R;

public class InformationActivity extends AppCompatActivity {
    ImageView btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        anhXa();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, AdminMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void anhXa(){
        btnReturn = (ImageView) findViewById(R.id.buttonReturn);
    }
}