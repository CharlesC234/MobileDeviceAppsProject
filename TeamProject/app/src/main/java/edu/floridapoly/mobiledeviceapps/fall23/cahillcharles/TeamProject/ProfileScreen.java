package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class ProfileScreen extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;

    // declare variables
    private TextView nameTextView;
    private DatabaseHelper databaseHelper;
    //declare progress bar variables
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView streakCounter;
    //declaring variables for changing calorie count
    private Button calChangeBtn;
    private LinearLayout mondayStreak;
    //declaring varibles for calorie counters
    private int currentCalories = 50;
    private int goalCalories;
    private static final String PREFERENCES_NAME = "MyAppPreferences";
    SharedPreferences prefs;
    int defaultVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_profile_screen);
        ImageView imgView = findViewById(R.id.profileImg);
        imgView.setImageResource(R.drawable.avatar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        setupBottomNavigation();

        //setting up the preferences
        String name = prefs.getString(getString(R.string.name_key)," ");
        int calories = prefs.getInt(getString(R.string.current_calorie_key), defaultVal);
        int calGoal = prefs.getInt(getString(R.string.calorie_key), defaultVal);
        int streak = prefs.getInt(getString(R.string.workout_streak), defaultVal);
        //referencing the name, email TextView
        nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(name);
        updateStreak();
        updateCalorieCounter();
        //updateCalorieCounter();
    }
    public void toSavedWorkouts(View myView)
    {

        Intent intent = new Intent(ProfileScreen.this, SavedWorkouts.class);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(), "Proceeding to Saved Workouts Screen", Toast.LENGTH_SHORT);
        toast.show();

    }
    public void updateStreak()
    {
        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        int streak = prefs.getInt(getString(R.string.workout_streak), defaultVal);
        streakCounter = findViewById(R.id.streak_count);
        streakCounter.setText(Integer.toString(streak));
    }
    //to update the progress bar to the users current calorie intake
    public void updateCalorieCounter()
    {
        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        int cal = prefs.getInt(getString(R.string.current_calorie_key), defaultVal);
        int goal = prefs.getInt(getString(R.string.calorie_key), defaultVal);
        progressBar = findViewById(R.id.progressBar);
        int amt = (cal * 100)/goal;
        progressBar.setProgress(amt);
        progressText = findViewById(R.id.progress_text);
        progressText.setText(Integer.toString(amt) + "%");
        Toast toast = Toast.makeText(getApplicationContext(), "Updated Counter", Toast.LENGTH_SHORT);
    }
    //onClick method for the change Calories when pressing the number in between the progress bar
    public void changeCalories(View myView) //on click method for updatingCalories Btn
    {
        showDialog();

        Toast toast = Toast.makeText(getApplicationContext(), "Updated Calories", Toast.LENGTH_SHORT);
    }
    //onClick method for the daily streaks
    public void setStreak(View MyView)
    {
        int viewId = MyView.getId();
        LinearLayout itemStreak = null;
        if (viewId == R.id.monday_progressLayout)
        {
            //testing streak activity
            itemStreak = findViewById(R.id.monday_progressLayout);
            if(itemStreak.getBackground() == null)
            {
                itemStreak.setBackgroundResource(R.drawable.streak_active);
            }
            else
            {
                itemStreak.setBackground(null);
            }
        }
        else if (viewId == R.id.tuesday_progressLayout)
        {
            //testing streak activity
            itemStreak = findViewById(R.id.tuesday_progressLayout);
            if(itemStreak.getBackground() == null)
            {
                itemStreak.setBackgroundResource(R.drawable.streak_active);
            }
            else
            {
                itemStreak.setBackground(null);
            }
        }
        else if (viewId == R.id.wednesday_progressLayout)
        {
            //testing streak activity
            itemStreak = findViewById(R.id.wednesday_progressLayout);
            if(itemStreak.getBackground() == null)
            {
                itemStreak.setBackgroundResource(R.drawable.streak_active);
            }
            else
            {
                itemStreak.setBackground(null);
            }
        }
        else if (viewId == R.id.thursday_progressLayout)
        {
            //testing streak activity
            itemStreak = findViewById(R.id.thursday_progressLayout);
            if(itemStreak.getBackground() == null)
            {
                itemStreak.setBackgroundResource(R.drawable.streak_active);
            }
            else
            {
                itemStreak.setBackground(null);
            }
        }
        else if (viewId == R.id.friday_progressLayout)
        {
            //testing streak activity
            itemStreak = findViewById(R.id.friday_progressLayout);
            if(itemStreak.getBackground() == null)
            {
                itemStreak.setBackgroundResource(R.drawable.streak_active);
            }
            else
            {
                itemStreak.setBackground(null);
            }
        }
        else if (viewId == R.id.saturday_progressLayout)
        {
            //testing streak activity
            itemStreak = findViewById(R.id.saturday_progressLayout);
            if(itemStreak.getBackground() == null)
            {
                itemStreak.setBackgroundResource(R.drawable.streak_active);
            }
            else
            {
                itemStreak.setBackground(null);
            }
        }
        else if (viewId == R.id.sunday_progressLayout)
        {
            //testing streak activity
            itemStreak = findViewById(R.id.sunday_progressLayout);
            if(itemStreak.getBackground() == null)
            {
                itemStreak.setBackgroundResource(R.drawable.streak_active);
            }
            else
            {
                itemStreak.setBackground(null);
            }
        }
        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        int streak = prefs.getInt(getString(R.string.workout_streak), defaultVal) +1;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(getString(R.string.workout_streak),streak);
        editor.apply();
        updateStreak();

    }
    private void showDialog()
    {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.update_calorie_dialogbox);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.popup_bg_window);
        Button update = dialog.findViewById(R.id.submitCalorieUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText updateCalEditTxt = dialog.findViewById(R.id.currentCalAmtInput);
                EditText updateCalGoalsEditText = dialog.findViewById(R.id.calorieGoalInput);


                SharedPreferences.Editor editor = prefs.edit();
                if (TextUtils.isEmpty(updateCalEditTxt.getText().toString()) || TextUtils.isEmpty(updateCalGoalsEditText.getText().toString()))
                {
                    if (TextUtils.isEmpty(updateCalEditTxt.getText().toString()) )
                    {
                        Log.d("variable update", "cal is empty");
                        int updateCalGoals = Integer.parseInt(String.valueOf(updateCalGoalsEditText.getText()));
                        editor.putInt(getString(R.string.calorie_key),updateCalGoals);
                    }
                    else if(TextUtils.isEmpty(updateCalGoalsEditText.getText().toString()))
                    {
                        Log.d("variable update", "cal goal is empty");
                        int updateCal = Integer.parseInt(String.valueOf(updateCalEditTxt.getText()));
                        editor.putInt(getString(R.string.current_calorie_key),updateCal);
                    }
                    else
                    {
                        Log.d("variable update", "both variables are filled");
                        int updateCal = Integer.parseInt(String.valueOf(updateCalEditTxt.getText()));
                        int updateCalGoals = Integer.parseInt(String.valueOf(updateCalGoalsEditText.getText()));
                        editor.putInt(getString(R.string.calorie_key),updateCalGoals);
                        editor.putInt(getString(R.string.current_calorie_key),updateCal);
                    }
                    editor.apply();
                    updateCalorieCounter();
                    dialog.dismiss();
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Need to change at least one input", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        dialog.show();
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        startActivity(new Intent(ProfileScreen.this, ProfileScreen.class));
                        showToast("Proceeding to Profile Screen");
                        return true;
                    case R.id.home:
                        startActivity(new Intent(ProfileScreen.this, MainScreen.class));
                        showToast("Proceeding to Home Screen");
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(ProfileScreen.this, ChatScreen.class));
                        showToast("Proceeding to Chat Screen");
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}