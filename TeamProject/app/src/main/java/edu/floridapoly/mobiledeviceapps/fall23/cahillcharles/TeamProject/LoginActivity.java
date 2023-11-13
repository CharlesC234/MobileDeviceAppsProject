package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // Declare button variables
    EditText uploadName, uploadEmail;
    Button signInBtn;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uploadName = findViewById(R.id.uploadName);
        uploadEmail= findViewById(R.id.uploadEmail);
        signInBtn = findViewById(R.id.signInButton);
        databaseHelper = new DatabaseHelper(this);

        // to save user information in database
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeInfo();
            }
        });

    }

    // Function to store user info in data
    private void storeInfo(){
        if(!uploadName.getText().toString().isEmpty() && !uploadEmail.getText().toString().isEmpty()){
            databaseHelper.storeData(new ProfileModelClass(uploadName.getText().toString(),uploadEmail.getText().toString(),null));
            Intent intent = new Intent(LoginActivity.this, MainScreen.class);
            startActivity(intent);

            // Toast to show navigation to new screen
            Toast toast = Toast.makeText(this, "Proceeding to Home Screen", Toast.LENGTH_SHORT);
            toast.show();

        } else{
            // If fields are empty
            Toast.makeText(this, "Please enter your information",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed(){
        onBackPressed();
    }
}