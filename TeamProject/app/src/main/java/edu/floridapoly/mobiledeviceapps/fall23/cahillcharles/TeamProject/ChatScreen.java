package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChatScreen extends AppCompatActivity {

    // Declare button variables
    Button example1btn, example2btn, sendbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        // login button listener
        example1btn = (Button) findViewById(R.id.example1);
        example2btn = (Button) findViewById(R.id.example2);
        sendbtn = (Button) findViewById(R.id.send);
        example1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(getApplicationContext(), "AI should suggest meals", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        example2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(getApplicationContext(), "AI should generate a few workouts", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(getApplicationContext(), "Send prompt currently in input", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}