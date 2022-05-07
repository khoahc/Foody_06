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

public class InformationActivity extends AppCompatActivity {
    ImageView btnReturn;
    EditText txtFullName, txtAddress;
    TextView textViewPhoneNumber;
    Button btnSaveInfo;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("infoUser");
        System.out.println(user.getAddress());

        anhXa();
        loadData(user);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                intent.putExtra("navigation_id", 3);
                intent.putExtra("infoUser", user);
                intent.putExtra("phoneNumberUser", user.getPhoneNumber());
                startActivity(intent);
                finish();
            }
        });

        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfoUser();
                Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                intent.putExtra("navigation_id", 3);
                intent.putExtra("phoneNumberUser", user.getPhoneNumber());
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadData(User user) {
        txtFullName.setText(user.getFullName());
        txtAddress.setText(user.getAddress());
        textViewPhoneNumber.setText(user.getPhoneNumber());
    }
    private void anhXa(){
        textViewPhoneNumber = (TextView) findViewById(R.id.textViewPhoneNumber);
        txtAddress = (EditText) findViewById(R.id.editTextAddress);
        txtFullName = (EditText) findViewById(R.id.editTextFullName);
        btnReturn = (ImageView) findViewById(R.id.buttonReturn);
        btnSaveInfo = (Button) findViewById(R.id.buttonSaveInfo);
    }

    private void saveInfoUser() {
        String fullName = txtFullName.getText().toString();
        String address = txtAddress.getText().toString();
        String phoneNumber = user.getPhoneNumber();
        User user = new User(phoneNumber, address, fullName);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        if(databaseAccess.updateUser(user)){
            Toast.makeText(InformationActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            Log.d("updateuser", "thanhcong");
        } else {
            Toast.makeText(InformationActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            Log.d("updateuser", "thatbai");
        }
        databaseAccess.close();

    }
}