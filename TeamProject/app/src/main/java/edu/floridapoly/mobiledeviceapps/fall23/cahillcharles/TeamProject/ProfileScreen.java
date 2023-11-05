package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileScreen extends AppCompatActivity {

    // declare variables
    private TextView nameTextView;
    private DatabaseHelper databaseHelper;
    //declare progress bar variables
    private ProgressBar progressBar;
    private TextView progressText;
    //declaring variables for changing calorie count
    private Button calChangeBtn;
    private LinearLayout mondayStreak;
    //declaring varibles for calorie counters
    private int currentCalories = 50;
    private int goalCalories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        ImageView imgView = findViewById(R.id.profileImg);
        imgView.setImageResource(R.drawable.avatar);
        //testing streak activity
        mondayStreak = findViewById(R.id.monday_progressLayout);
        mondayStreak.setBackgroundResource(R.drawable.streak_active);



        // Initialize your databaseHelper
        databaseHelper = new DatabaseHelper(this);

        // referencing the name, email TextView
        nameTextView = findViewById(R.id.nameTextView);


        // get user data from database
        Cursor cursor = databaseHelper.getUser();

        // Grabbing the first name on the database
        if(cursor.moveToLast()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));


            // Update the name Textview
            nameTextView.setText("Welcome Back, "+ name +"!");

            // Update the email Textview
        }
        // closing the cursor
        cursor.close();
        //update the calories
        updateCalorieCounter();
    }
    public void toSavedWorkouts(View myView)
    {

        Intent intent = new Intent(ProfileScreen.this, SavedWorkouts.class);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(), "Proceeding to Saved Workouts Screen", Toast.LENGTH_SHORT);
        toast.show();

    }
    //to update the progress bar to the users current calorie intake
    public void updateCalorieCounter()
    {
        //goalCalories = getIntent().getExtras().getString("goalCalories");
       /* progressBar = findViewById(R.id.progressBar);
        int amt = (currentCalories * 100)/goalCalories;
        progressBar.setProgress(amt);
        progressText = findViewById(R.id.progress_text);
        progressText.setText(amt);
        */

    }
    //onClick method for the change Calories when pressing the number in between the progress bar
    public void changeCalories(View myView) //on click method for updatingCalories Btn
    {
        showDialog();
    }
    private void showDialog()
    {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.update_calorie_dialogbox);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.popup_bg_window);
        dialog.show();
    }
}