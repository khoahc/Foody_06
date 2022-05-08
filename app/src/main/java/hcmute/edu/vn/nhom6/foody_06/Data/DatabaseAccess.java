package hcmute.edu.vn.nhom6.foody_06.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Modal.Comment;
import hcmute.edu.vn.nhom6.foody_06.Modal.Food;
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

    public String getFullNameUserById(Integer idUser) {
        Cursor c = db.rawQuery("SELECT fullName FROM users WHERE users.id = ?",
                new String[]{idUser.toString()});
        String fullNameUser = "";
        if(c != null && c.getCount() == 1) {
            c.moveToFirst();
            fullNameUser = c.getString(0);
        }
        c.close();
        return fullNameUser;
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        Cursor c = db.rawQuery("SELECT * FROM users WHERE users.phoneNumber = ?",
                new String[]{phoneNumber});
        User user = null;
        if(c != null && c.getCount() == 1) {
            c.moveToFirst();
            Integer id = c.getInt(0);
            String password = c.getString(2);
            String address = c.getString(3);
            String fullName = c.getString(4);
            user = new User(id, phoneNumber, password, address, fullName);
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
    //STORE
    //query get list store
    public List<Store> getListStore() {
        List<Store> listStore = new ArrayList<Store>();

        Cursor c = db.rawQuery("SELECT * FROM stores", null);
        while (c.moveToNext()) {
            listStore.add(
                new Store(
                        c.getInt(0),
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

    //query find store
    public List<Store> findStore(String keyword) {
        List<Store> listStore = new ArrayList<Store>();

        Cursor c = db.rawQuery("SELECT DISTINCT stores.id, stores.nameStore, stores.addressStore, stores.image, stores.state FROM stores\n" +
                "INNER JOIN foods on foods.idStore = stores.id\n" +
                "WHERE stores.nameStore LIKE \"%" + keyword + "%\" OR foods.nameFood LIKE \"%" + keyword + "%\" ", new String[]{});

        while (c.moveToNext()) {
            listStore.add(
                    new Store(
                            c.getInt(0),
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

    //querry get list food
    public List<Food> getListFood(Integer idStore) {
        List<Food> listFood = new ArrayList<Food>();

        Cursor c = db.rawQuery("SELECT * FROM foods WHERE foods.idStore = ?", new String[]{idStore.toString()});
        while (c.moveToNext()) {
            listFood.add(
                    new Food(
                            c.getInt(0),
                            c.getString(1),
                            c.getFloat(2),
                            c.getBlob(3),
                            c.getInt(4) == 1,
                            c.getInt(5)
                    )
            );
        }
        c.close();
        return listFood;
    }


    //COMMENT
    //querry get list comment
    public List<Comment> getListComment(Integer idStore) {
        List<Comment> listComment = new ArrayList<Comment>();

        Cursor c = db.rawQuery("SELECT * FROM comments WHERE comments.idStore = ?", new String[]{idStore.toString()});
        while (c.moveToNext()) {
            listComment.add(
                    new Comment(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getInt(3),
                            c.getInt(4)
                    )
            );
        }
        c.close();
        return listComment;
    }

    public boolean insertComment(String content, int idUser, int idStore) {
        ContentValues cv = new ContentValues();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        cv.put("content", content);
        cv.put("timeCreate", dtf.format(now).toString());
        cv.put("idUser", idUser);
        cv.put("idStore", idStore);

        return db.insert("comments", null, cv) > 0;
    }
}
