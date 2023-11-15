package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainScreen extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    Button chatBtn, profileBtn, workoutBtn;
    private int goalCalories = 217;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //chatBtn = (Button) findViewById(R.id.tochatbtn);

        //workoutBtn = (Button) findViewById(R.id.toWorkouts);

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