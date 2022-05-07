package hcmute.edu.vn.nhom6.foody_06.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {
//    // Logcat tag
//    private static final String LOG = "DatabaseHelper";
//
//    // Database Version
//    private static final int DATABASE_VERSION = 1;
//
//    // Database Name
//    private static final String DATABASE_NAME = "Foody_DB";
//
//    // Table Names
//    private static final String TABLE_USERS = "users";
//    private static final String TABLE_ROLES = "roles";
//    private static final String TABLE_STORES = "stores";
//    private static final String TABLE_FOODS = "foods";
//
//    // ROLES Table
//    private static final String ROLES_ID = "roles_id";
//    private static final String ROLES_NAME = "roles_name";
//
//    // USERS Table
//    private static final String USERS_ID = "users_id";
//    private static final String USERS_PHONE_NUMBER = "users_phone_number";
//    private static final String USERS_PASSWORD = "users_password";
//    private static final String USERS_FULL_NAME = "users_full_name";
//    private static final String USERS_ADDRESS = "users_address";
//    private static final String USERS_ID_ROLE = "users_id_role";
//
//    // STORES Table
//    private static final String STORES_ID = "stores_id";
//    private static final String STORES_NAME = "stores_name";
//    private static final String STORES_ADDRESS = "stores_address";
//    private static final String STORES_IMAGE = "stores_image";
//    private static final String STORES_LAST_COMMENT = "stores_last_comment";
//    private static final String STORES_STATE = "stores_state";
//    //private static final String STORES_DEFAULT_LAST_COMMENT = "";
//
//    // FOODS Table
//    private static final String FOODS_ID = "foods_id";
//    private static final String FOODS_NAME = "foods_name";
//    private static final String FOODS_COUNT = "foods_count";
//    private static final String FOODS_UNIT_PRICE = "foods_unit_price";
//    private static final String FOODS_IMAGE = "foods_image";
//    private static final String FOODS_STATE = "foods_state";
//    private static final String FOODS_ID_STORE = "foods_id_store";
//
//    // Table Create Statements
//    // Roles table create statement
//    private static final String CREATE_TABLE_ROLES = "CREATE TABLE "
//            + TABLE_ROLES + "(" + ROLES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                                + ROLES_NAME + " TEXT );";
//
//    // Users table create statement
//    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
//            + TABLE_USERS + "(" + USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                                + USERS_PHONE_NUMBER + " TEXT NOT NULL, "
//                                + USERS_PASSWORD + " TEXT NOT NULL, "
//                                + USERS_FULL_NAME + " TEXT, "
//                                + USERS_ADDRESS + " TEXT, "
//                                + USERS_ID_ROLE + " INTEGER, "
//                                + " FOREIGN KEY (" + USERS_ID_ROLE + ")" +
//                                    " REFERENCES " + TABLE_ROLES + "(" + ROLES_ID + ") );" ;
//
//    // Stores table create statement
//    private static final String CREATE_TABLE_STORES = "CREATE TABLE " + TABLE_STORES + " ( "
//            + STORES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + STORES_NAME + " TEXT NOT NULL DEFAULT '', "
//            + STORES_ADDRESS + " TEXT, "
////            + STORES_IMAGE + " BLOG NOT NULL DEFAULT '', "
//            + STORES_LAST_COMMENT + " TEXT DEFAULT '', "
//            + STORES_STATE + " INTEGER NOT NULL DEFAULT 0 );";
//
//    // Foods table create statement
//    private static final String CREATE_TABLE_FOODS = "CREATE TABLE "
//            + TABLE_FOODS + "(" + FOODS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + FOODS_NAME + " TEXT NOT NULL,"
//            + FOODS_COUNT + " INTEGER NOT NULL DEFAULT 0,"
//            + FOODS_UNIT_PRICE + " REAL NOT NULL DEFAULT 0,"
////            + FOODS_IMAGE + " BLOG, "
//            + FOODS_STATE + " INTEGER NOT NULL DEFAULT 0, "
//            + FOODS_ID_STORE + " INTEGER, "
//            + " FOREIGN KEY (" + FOODS_ID_STORE + ")" +
//            " REFERENCES " + TABLE_STORES + "(" + STORES_ID +") );";
//
//
//    public static SQLiteDatabase initDatabase(Activity activity, String databaseName){
//        try {
//            String outFileName = activity.getApplicationInfo().dataDir + "/databases/" + databaseName;
//            File f = new File(outFileName);
//            if(!f.exists()) {
//                InputStream e = activity.getAssets().open(databaseName);
//                File folder = new File(activity.getApplicationInfo().dataDir + "/databases/");
//                if (!folder.exists()) {
//                    folder.mkdir();
//                }
//                FileOutputStream myOutput = new FileOutputStream(outFileName);
//                byte[] buffer = new byte[1024];
//
//                int length;
//                while ((length = e.read(buffer)) > 0) {
//                    myOutput.write(buffer, 0, length);
//                }
//
//                myOutput.flush();
//                myOutput.close();
//                e.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
//    }
//
//    public DatabaseHelper(Context context)  {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(CREATE_TABLE_ROLES);
//        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
//        sqLiteDatabase.execSQL(CREATE_TABLE_STORES);
//        sqLiteDatabase.execSQL(CREATE_TABLE_FOODS);
//        createDefaultDataIfNeed();
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + CREATE_TABLE_ROLES + "'");
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + CREATE_TABLE_USERS + "'");
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + CREATE_TABLE_STORES + "'");
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + CREATE_TABLE_FOODS + "'");
//        onCreate(sqLiteDatabase);
//    }
//
//    public void createDefaultDataIfNeed()  {
//       // createDefaultRolesIfNeed();
//        //createDefaultUsersIfNeed();
//        createDefaultStoresIfNeed();
//        //createDefaultFoodsIfNeed();
//    }
//
//    // If Stores table has no data
//    // default, Insert 14 records.
//    public void createDefaultStoresIfNeed()  {
//        int count = this.getStoresCount();
//        Log.d("storesCount", count + "");
//        if( count == 0 ) {
//            Store store1 = new Store("Quán cơm chiên Đình Long", "400, Le Van Viet, Q9",
//                    "comchien", "quán nấu ngon", true );
//            Store store2 = new Store("Quán gà chiên Đỉnh Ký", "Q1",
//                    "R.drawable.gachien", "quán ok", true );
//            Store store3 = new Store("Quán hải sản Thủy Tề", "420, Le Van Viet, Q9",
//                    "R.drawable.haisan", "không ngon cho lắm", true );
//            Store store4 = new Store("Gà xối mỡ Thiên An", "440, Le Van Viet, Q9",
//                    "R.drawable.gaxoimo", "tạm được", true );
//            Store store5 = new Store("Quán phở Bình Minh", "120, Le Van Viet, Q9",
//                    "R.drawable.pho", "quán thoáng mát", true );
//            Store store6 = new Store("Cơm Phúc Lộc Thọ", "320, Le Van Viet, Q9",
//                    "R.drawable.quancom", "lần đầu ăn ở đây", true );
//            Store store7 = new Store("A Xìn - Chân gà rút xương", "27, Nguyễn Hới, Quận Bình Tân",
//                    "R.drawable.quancom", "ngonnnnnnn", true );
//            Store store8 = new Store("Chè Út LyLy", "384, Nguyễn Trọng Tuyển, Quận Tân Bình",
//                    "R.drawable.quancom", "khá là ngon", true );
//            Store store9 = new Store("Quán ốc sinh viên", "320, Le Van Viet, Q9",
//                    "R.drawable.quancom", "ngon", true );
//            Store store10 = new Store("TocoToco", "91, Nguyễn Sơn, Quận Tân Phú",
//                    "R.drawable.quancom", "Mình đã order trên app thấy khá là ngon", true );
//            Store store11 = new Store("KFC", "20, Võ Văn Ngân, Thủ Đức",
//                    "R.drawable.quancom", "mắc quá", true );
//            Store store12 = new Store("Quán cơm Trường Thọ", "120, Đặng Văn Bi, Thủ Đức",
//                    "R.drawable.quancom", "quán ổn, giá sinh viên", true );
//            Store store13 = new Store("Trà sữa Mie Tea", "400, Phạm Văn Đồng, Thủ Đức",
//                    "R.drawable.quancom", "đã uống nhiều lần, ngon dã man", true );
//            Store store14 = new Store("Cơm cháy kho quẹt", "220, Man Thiện, Q9",
//                    "R.drawable.quancom", "khá là ngon", true );
//            this.addStore(store1);
//            this.addStore(store2);
//            this.addStore(store3);
//            this.addStore(store4);
//            this.addStore(store5);
//            this.addStore(store6);
//            this.addStore(store7);
//            this.addStore(store8);
//            this.addStore(store9);
//            this.addStore(store10);
//            this.addStore(store11);
//            this.addStore(store12);
//            this.addStore(store13);
//            this.addStore(store14);
//        }
////        else {
////            deleteAllStore();
////        }
//
//    }
//
////    public void createDefaultRolesIfNeed()  {
////        int count = this.getRolesCount();
////        Log.d("rolesCount", count + "");
////        if( count == 0 ) {
////
////            this.addStore(store1);
////            this.addStore(store2);
////
////        }
//////        else {
//////            deleteAllStore();
//////        }
////
////    }
//
//
//
//    //QUERY STORES
//    public void addStore(Store store) {
//        Log.d("addStore", "MyDatabaseHelper.addStore ... " + store.getName());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(STORES_NAME, store.getName());
//        values.put(STORES_ADDRESS, store.getAddress());
//        //values.put(STORES_IMAGE, store.getImage());
//        values.put(STORES_LAST_COMMENT, store.getLastComment());
//        values.put(STORES_STATE, store.isState() ? 1 : 0);
//
//        // Inserting Row
//        long result = db.insert(TABLE_STORES, null, values);
//        if(result == -1) {
//            Log.d("loiinsert", values.toString());
//        } else {
//            Log.d("thanhcong", values.toString());
//        }
//        // Closing database connection
//        db.close();
//    }
//
//
//    public int getStoresCount() {
//        Log.d("getStoresCount", "MyDatabaseHelper.getStoresCount ... " );
//
//        String countQuery = "SELECT * FROM " + TABLE_STORES;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//
//        cursor.close();
//
//        return count;
//    }
//
//    public List<Store> getAllStores() {
//        Log.d("getAllStores", "MyDatabaseHelper.getAllNotes ... " );
//
//        List<Store> storeList = new ArrayList<Store>();
//        // Select All Query
//        String selectQuery = "SELECT * FROM " + TABLE_STORES;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Store store = new Store();
//                store.setName(cursor.getString(1));
//                store.setAddress(cursor.getString(2));
//               // store.setImage(cursor.getString(3));
//                store.setLastComment(cursor.getString(3));
//                store.setState(cursor.getInt(4) > 0);
//
//                // Adding store to list
//                storeList.add(store);
//            } while (cursor.moveToNext());
//        }
//
//        return storeList;
//    }
//
//    public void deleteStore(Store store) {
//        Log.d("delete", "MyDatabaseHelper.updateStore ... " + store.getName() );
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_STORES, STORES_NAME + " = ?",
//                new String[] { String.valueOf(store.getName()) });
//        db.close();
//    }
//
//    public void deleteAllStore() {
//        Log.d("deleteAll", "MyDatabaseHelper.deleteAllStore ... ");
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("delete from "+ TABLE_STORES);
//    }


//    //ROLES
//    public int getRolesCount() {
//        String countQuery = "SELECT * FROM " + TABLE_ROLES;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//
//        cursor.close();
//
//        return count;
//    }



    //--------------------------------
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "foodydb_06.db";

    public DatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


}
