package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {
    Button chatBtn, profileBtn, workoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        chatBtn = (Button) findViewById(R.id.tochatbtn);

        workoutBtn = (Button) findViewById(R.id.toWorkouts);
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
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(), "Proceeding to Profile Screen", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void toSavedWorkouts(View view) {
        Intent intent = new Intent(MainScreen.this, SavedWorkouts.class);
        startActivity(intent);
        Toast.makeText(this, "Going to workouts", Toast.LENGTH_SHORT).show();
    }
}