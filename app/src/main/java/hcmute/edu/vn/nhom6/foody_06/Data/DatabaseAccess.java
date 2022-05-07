package hcmute.edu.vn.nhom6.foody_06.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //open the database
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    //close the database conection
    public void close(){
        if(db != null) {
            this.db.close();
        }
    }

    //querry login user
    public String loginUser(String phoneNumber, String password) {
        String phoneNumberFind = "";
        Cursor c = db.rawQuery("SELECT phoneNumber FROM users WHERE users.phoneNumber = ? AND users.password = ?",
                new String[]{phoneNumber, password});

        if(c != null && c.getCount() == 1) {
            c.moveToFirst();
            phoneNumberFind = c.getString(0);

        }
        c.close();
        return phoneNumberFind;
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        Cursor c = db.rawQuery("SELECT * FROM users WHERE users.phoneNumber = ?",
                new String[]{phoneNumber});
        User user = null;
        if(c != null && c.getCount() == 1) {
            c.moveToFirst();
            String password = c.getString(2);
            String address = c.getString(3);
            String fullName = c.getString(4);
            user = new User(phoneNumber, password, address, fullName);
        }
        c.close();
        return user;
    }

    public boolean updateUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put("address", user.getAddress());
        cv.put("fullName", user.getFullName());
       return db.update("users", cv, "phoneNumber = ?", new String[]{user.getPhoneNumber()}) > 0;

    }

    //querry get list store
    public List<Store> getListStore() {
        List<Store> listStore = new ArrayList<Store>();

        Cursor c = db.rawQuery("SELECT * FROM stores", null);
        while (c.moveToNext()) {
            listStore.add(
                new Store(
                        c.getString(1),
                        c.getString(2),
                        c.getBlob(3),
                        c.getInt(4) == 1
                )
            );
        }
        c.close();
        return listStore;
    }



}
