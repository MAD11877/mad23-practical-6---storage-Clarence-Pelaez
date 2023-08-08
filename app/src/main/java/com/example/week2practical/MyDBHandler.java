package com.example.week2practical;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "useDB.db";
    public static final String TABLE_USER = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOLLOW = "followed";

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_USER + " (" + COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FOLLOW + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,user.getName());
        contentValues.put(COLUMN_DESCRIPTION,user.getDescription());
        if(user.isFollowed()){
            contentValues.put(COLUMN_FOLLOW,1);
        }
        else{
            contentValues.put(COLUMN_FOLLOW,0);
        }
        SQLiteDatabase db =this.getWritableDatabase();

        db.insert(TABLE_USER,null,contentValues);
        db.close();
    }

    public User findUser(String userName){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " +
                COLUMN_NAME + " = \"" + userName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        User user = new User();

        if(cursor.moveToFirst()){
            user.setName(cursor.getString(0));
            user.setDescription(cursor.getString(1));
            user.setId(Integer.parseInt(cursor.getString(2)));
            if(cursor.getString(3).equals("1")){
                user.setFollowed(true);
            }
            else{
                user.setFollowed(false);
            }
        }
        else{user = null;}
        db.close();
        return user;
    }

    public void updateUser(String Oname, int follow){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FOLLOW,follow);

        db.update(TABLE_USER, values, "name=?", new String[]{Oname});
        db.close();
    }

    public ArrayList<User> getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER,null);

        ArrayList<User> userList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                userList.add(new User(
                        cursor.getString(0),
                        cursor.getString(1)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }
}
