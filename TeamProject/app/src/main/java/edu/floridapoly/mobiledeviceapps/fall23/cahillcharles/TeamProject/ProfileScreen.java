package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ProfileScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
    }
    public void toSavedWorkouts(View myView)
    {

        Intent intent = new Intent(ProfileScreen.this, SavedWorkouts.class);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(), "Proceeding to Saved Workouts Screen", Toast.LENGTH_SHORT);
        toast.show();

    }
}