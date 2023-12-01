package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SavedWorkouts extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    WorkoutDatabase db;
    TextView tView;
    private BottomNavigationView bottomNavigationView;
    private ListView workout_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workouts);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        setupBottomNavigation();

        db = new WorkoutDatabase(this); //start database
        ArrayList<WorkoutModelClass> workouts = db.getAllWorkouts(); //get all workouts from database
        for(int i = 0; i < workouts.size(); i++) {
            Log.i("workouts: ", workouts.get(i).getName() + " ---- " + workouts.get(i).getDescription());
        }

        //Finish setting up recycler view and dynamic expandable list
        RecyclerView recyclerView = findViewById(R.id.workouts_recycler_view);
        ExpandableAdapter adapter = new ExpandableAdapter(this, workouts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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