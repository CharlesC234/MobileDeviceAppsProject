package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SavedWorkouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workouts);

        Button button = (Button) findViewById(R.id.workoutButton);

        Button workoutToHomeBtn = (Button) findViewById(R.id.workoutToHomeButton);
    }

    public void displayWorkout(View view) {
        TextView tView = findViewById(R.id.workoutTView);
        tView.setText("I dunno some workout\nLift big weight\nDo something");
    }
    public void workoutToHome(View view) {
        Intent intent = new Intent(SavedWorkouts.this, MainActivity.class);

        startActivity(intent);
    }
}