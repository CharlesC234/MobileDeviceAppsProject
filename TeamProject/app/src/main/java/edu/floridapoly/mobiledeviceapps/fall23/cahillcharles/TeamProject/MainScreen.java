package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainScreen extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    Button chatBtn, profileBtn, workoutBtn;
    TextView greetingTxt;
    TextView nmTxt;
    TextView calGoalTxt, finWorkoutsTxt,streakTxt, currCalTxt;
    ProgressBar pb;
    HorizontalScrollView scroll;
    private int goalCalories = 217;
    private static final String PREFERENCES_NAME = "MyAppPreferences";
    WorkoutDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        SharedPreferences prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        int defaultVal = 0;
        //set up shared pref variables
        String name = prefs.getString(getString(R.string.name_key)," ");
        int calories = prefs.getInt(getString(R.string.current_calorie_key), defaultVal);
        int calGoal = prefs.getInt(getString(R.string.calorie_key), defaultVal);
        int finishedWorkouts = prefs.getInt(getString(R.string.workouts_finished_key),defaultVal);
        int streak = prefs.getInt(getString(R.string.workout_streak), defaultVal);

        //chatBtn = (Button) findViewById(R.id.tochatbtn);

        //workoutBtn = (Button) findViewById(R.id.toWorkouts);
        //connecting elements
        nmTxt = findViewById(R.id.nameText);
        calGoalTxt = findViewById(R.id.calGoalsTextView);
        finWorkoutsTxt = findViewById(R.id.finishedWorkoutsTextView);
        streakTxt = findViewById(R.id.streakTextView);
        //putting values to UI

        nmTxt.setText(name);
        calGoalTxt.setText(String.valueOf(calGoal));
        finWorkoutsTxt.setText(String.valueOf(finishedWorkouts));
        streakTxt.setText(String.valueOf(streak));

        //setting greeting based off time
        greetingTxt = findViewById(R.id.greetingText);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY) ;

        if(hour>=12 && hour < 17)
        {
            greetingTxt.setText("Good Afternoon");
        }
        else if(hour >= 17 && hour < 21)
        {
            greetingTxt.setText("Good Evening");
        }
        else if(hour >= 21 && hour < 24)
        {
            greetingTxt.setText("Good Night");
        }
        else
        {
            greetingTxt.setText("Good Morning");
        }
        //update calorie widget
        updateCalorieCounter(calories, calGoal);

        //checks for workouts in DB
        TextView p1 = findViewById(R.id.title0);
        TextView p2 = findViewById(R.id.title1);
        TextView p3 = findViewById(R.id.title2);
        TextView p4 = findViewById(R.id.title3);
        ArrayList<TextView> titles = new ArrayList<>();
        titles.add(p1);
        titles.add(p2);
        titles.add(p3);
        titles.add(p4);
        p1 = findViewById(R.id.des0);
        p2 = findViewById(R.id.des1);
        p3 = findViewById(R.id.des2);
        p4 = findViewById(R.id.des3);
        ArrayList<TextView> descriptions = new ArrayList<>();
        descriptions.add(p1);
        descriptions.add(p2);
        descriptions.add(p3);
        descriptions.add(p4);
        db = new WorkoutDatabase(this);
        ArrayList<WorkoutModelClass> workouts = db.getAllWorkouts();
        scroll = findViewById(R.id.scroller);
        int i = 0;
        TextView view;
        if (workouts.size() != 0) {
            while (i < workouts.size() && i < 4) {
                WorkoutModelClass workout = workouts.get(i);
                String desc = workout.getDescription();
                String workoutName = workout.getName();
                view = titles.get(i);
                view.setText(workoutName);
                view = descriptions.get(i);
                view.setText(desc);
                i++;
            }
            if (i < 4)
            {
                while(i < 4)
                {
                    String desc = "No workouts...yet";
                    String workoutName = "";
                    view = titles.get(i);
                    view.setText(workoutName);
                    view = descriptions.get(i);
                    view.setText(desc);
                    i++;
                }
            }

        }
        else
        {
            while(i < 4)
            {
                String desc = "No workouts...yet";
                String workoutName = "";
                view = titles.get(i);
                view.setText(workoutName);
                view = descriptions.get(i);
                view.setText(desc);
                i++;
            }
        }
        //putting the nav bar into activity
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        setupBottomNavigation();

    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        startActivity(new Intent(MainScreen.this, ProfileScreen.class));
                        showToast("Proceeding to Profile Screen");
                        return true;
                    case R.id.home:
                        startActivity(new Intent(MainScreen.this, MainScreen.class));
                        showToast("Proceeding to Home Screen");
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(MainScreen.this, ChatScreen.class));
                        showToast("Proceeding to Chat Screen");
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
    public void finishedWorkout(View view)
    {
        //alert to maker sure the user actually wants to delete this workout
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Finished Workout?");
        builder.setMessage("Did you finish this Workout?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            //increase the workout finished function
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
                int finishedWorkouts = prefs.getInt(getString(R.string.workouts_finished_key),0);
                finishedWorkouts++;
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt(getString(R.string.workouts_finished_key),finishedWorkouts);
                editor.apply();
                finWorkoutsTxt.setText(Integer.toString(finishedWorkouts));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //nothing they just didn't delete the workout
            }
        });
        //refresh view

        //display alert dialog
        builder.create().show();
    }
    public void updateCalorieCounter(int current, int goal)
    {
        currCalTxt = findViewById(R.id.currentCalText);
        currCalTxt.setText(String.valueOf(current));
        pb = findViewById(R.id.progressBarWidget);
        int amt = (current * 100)/goal;
        pb.setProgress(amt);


    }
    public void goToChat(View myView)
    {
        Intent intent = new Intent(MainScreen.this, ChatScreen.class);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(), "Proceeding to Chat Screen", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void goToProfile(View myView)
    {
        Intent intent = new Intent(MainScreen.this, ProfileScreen.class);
        //intent.putExtra("goalCalories",goalCalories);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(), "Proceeding to Profile Screen", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void toSavedWorkouts(View view) {
        Intent intent = new Intent(MainScreen.this, SavedWorkouts.class);
        startActivity(intent);
        Toast.makeText(this, "Going to workouts", Toast.LENGTH_SHORT).show();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}