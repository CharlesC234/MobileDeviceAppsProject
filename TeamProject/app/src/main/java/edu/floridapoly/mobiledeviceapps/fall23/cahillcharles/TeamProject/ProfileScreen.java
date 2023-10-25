package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileScreen extends AppCompatActivity {

    // declare variables
    private TextView nameTextView, emailTextView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        ImageView imgView = findViewById(R.id.profileImg);
        imgView.setImageResource(R.drawable.avatar);

        // Initialize your databaseHelper
        databaseHelper = new DatabaseHelper(this);

        // referencing the name, email TextView
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTexView);


        // get user data from database
        Cursor cursor = databaseHelper.getUser();

        // Grabbing the first name on the database
        if(cursor.moveToLast()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));


            // Update the name Textview
            nameTextView.setText(name);

            // Update the email Textview
            emailTextView.setText(email);
        }
        // closing the cursor
        cursor.close();

    }
    public void toSavedWorkouts(View myView)
    {

        Intent intent = new Intent(ProfileScreen.this, SavedWorkouts.class);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(), "Proceeding to Saved Workouts Screen", Toast.LENGTH_SHORT);
        toast.show();

    }
}