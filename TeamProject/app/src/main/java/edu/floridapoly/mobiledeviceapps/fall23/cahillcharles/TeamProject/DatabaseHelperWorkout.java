package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DatabaseHelperWorkout extends SQLiteOpenHelper {
    Context context;
    private static String DB_NAME = "workout.db";
    private static int DB_VERSION = 1;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteImage;

    private static String createTable = "create table workouts(name TEXT" + ",description TEXT)";


    public DatabaseHelperWorkout(@Nullable Context context) {
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

    public void storeData(WorkoutModelClass wMC){
        SQLiteDatabase database = this.getWritableDatabase();

        /*
        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byteImage = byteArrayOutputStream.toByteArray();
         */

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", wMC.getName());
        contentValues.put("description", wMC.getDescription());
        //contentValues.put("image", byteImage);

        long checkQuery = database.insert("workouts", null, contentValues);

        if(checkQuery != -1){
            Toast.makeText(context, "User information is saved!",Toast.LENGTH_SHORT).show();
            database.close();
        } else{
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getUser(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM workouts", null);
        return cursor;
    }
}
