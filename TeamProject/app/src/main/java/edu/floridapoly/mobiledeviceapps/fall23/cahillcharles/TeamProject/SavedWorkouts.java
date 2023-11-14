package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class SavedWorkouts extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    TextView tView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workouts);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        setupBottomNavigation();

        //declare button for displaying saved workouts
        Button button = (Button) findViewById(R.id.workoutButton);

        //declare button for going home
        Button workoutToHomeBtn = (Button) findViewById(R.id.workoutToHomeButton);

        tView = findViewById(R.id.workoutTView);
        tView.setVisibility(View.INVISIBLE);
    }

    //Function that sets the textview below the button to this text. Will be replace by fragments and generalized when we get to that step
    public void displayWorkout(View view) {

        //if displaying workout and button is clicked, hide workout
        //else display workout
        if(tView.getVisibility() == View.VISIBLE)
        {
            tView.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "hides workout", Toast.LENGTH_SHORT).show();
        }
        else
        {
            tView.setText("I dunno some workout\nLift big weight\nDo something");
            Toast.makeText(this, "Displays whatever workout was saved", Toast.LENGTH_SHORT).show();
            tView.setVisibility(View.VISIBLE);
        }
    }

    //Button to navigate to home screen
    //will be replaced by navigation bar when we get to that step
    public void workoutToHome(View view) {
        Intent intent = new Intent(SavedWorkouts.this, MainScreen.class);
        Toast.makeText(this, "There's no place like home", Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        startActivity(new Intent(SavedWorkouts.this, ProfileScreen.class));
                        showToast("Proceeding to Profile Screen");
                        return true;
                    case R.id.home:
                        startActivity(new Intent(SavedWorkouts.this, MainScreen.class));
                        showToast("Proceeding to Home Screen");
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(SavedWorkouts.this, ChatScreen.class));
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