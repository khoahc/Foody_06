package hcmute.edu.vn.nhom6.foody_06.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.nhom6.foody_06.R;

public class IntroActivity extends AppCompatActivity {
    Handler handler;
    final String DATABASE_NAME = "databases/foodydb_06.db";
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(IntroActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
