package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SavedWorkouts extends AppCompatActivity {

    TextView tView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workouts);


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
}