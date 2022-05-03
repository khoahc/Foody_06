package hcmute.edu.vn.nhom6.foody_06.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseHelper;
import hcmute.edu.vn.nhom6.foody_06.R;

public class IntroActivity extends AppCompatActivity {
    Handler handler;
    //DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

//        databaseHelper = new DatabaseHelper(IntroActivity.this);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
