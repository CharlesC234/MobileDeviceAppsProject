package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;
    private static String DB_NAME = "profile.db";
    private static int DB_VERSION = 1;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteImage;

    private static String createTable = "create table ProfileUser(name TEXT" +
            ",email TEXT" +
            ",currentCalorie INTEGER" +
            ",calorieGoal INTEGER" +
            ",image BLOB)";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void storeData(ProfileModelClass profileModelClass){
        SQLiteDatabase database = this.getWritableDatabase();
        Bitmap bitmapImage = profileModelClass.getImage();


        ContentValues contentValues = new ContentValues();
        contentValues.put("name", profileModelClass.getName());
        contentValues.put("email", profileModelClass.getEmail());
        contentValues.put("currentCalorie", profileModelClass.getCurrentCalorie());
        contentValues.put("calorieGoal", profileModelClass.getCalorieGoal());
        //contentValues.put("image", byteImage);


        long checkQuery = database.insert("ProfileUser", null, contentValues);

        if(checkQuery != -1){
            Toast.makeText(context, "User information is saved!",Toast.LENGTH_SHORT).show();
            database.close();
        } else{
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getUser(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM ProfileUser", null);
        return cursor;
    }
}
