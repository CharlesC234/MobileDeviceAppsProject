package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "workout_db";

    public static final String TABLE_NAME = "workouts";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " workout_name TEXT,"
            + " description TEXT)";

    public WorkoutDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //insert workout name and description to the database
    //each pair in a unique element inside database
    public void insertWorkout(String workout_name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("workout_name", workout_name);
        values.put("description", description);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    //returns an arraylist with all of the workouts
    @SuppressLint("Range")
    public ArrayList<WorkoutModelClass> getAllWorkouts() {
        ArrayList<WorkoutModelClass> workouts = new ArrayList<>();

        //Select Query to get workout information from list
        String query = "SELECT id, workout_name, description FROM " + TABLE_NAME;
        String workout_name = "";
        String description = "";
        int id = -1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //move the cursor through the database adding each element the the arraylist
        if(cursor.moveToFirst()) {
            do {
                workout_name = cursor.getString(cursor.getColumnIndex("workout_name"));
                description = cursor.getString(cursor.getColumnIndex("description"));
                id = cursor.getInt(cursor.getColumnIndex("id"));
                workouts.add(new WorkoutModelClass(id, workout_name, description));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return workouts;
    }

    public void deleteWorkout(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE ID=" + id);
    }
}