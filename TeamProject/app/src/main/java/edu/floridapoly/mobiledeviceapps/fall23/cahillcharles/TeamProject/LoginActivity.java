package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    // varies for textView, RadioButton, and Spinner
    EditText uploadNameTextView;
    EditText uploadHeightTextView;
    EditText uploadWeightTextView;
    EditText uploadCalorieTextView;
    RadioButton radioCut, radioGain, radioMaintain;
    Spinner spinner;
    Button nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // find textViews
        uploadNameTextView = findViewById(R.id.uploadName);
        uploadHeightTextView = findViewById(R.id.uploadHeight);
        uploadWeightTextView = findViewById(R.id.uploadWeight);
        uploadCalorieTextView = findViewById(R.id.uploadCalorie);

        // find button
        nextBtn = findViewById(R.id.nextButton);

        // Initialize RadioButton variables
        radioCut = findViewById(R.id.radio_cut);
        radioGain = findViewById(R.id.radio_gain);
        radioMaintain = findViewById(R.id.radio_maintain);

        Spinner spinner = (Spinner) findViewById(R.id.gender_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);
        // Set the listener for item selection
        spinner.setOnItemSelectedListener(this);

        // Set the background color of the spinner
        spinner.setBackgroundColor(getResources().getColor(R.color.your_color));


        // to save user information in database
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });

    }

    // Implement the onItemSelected method of the OnItemSelectedListener interface
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).

        ((TextView) parent.getChildAt(0)).setTextColor(0XFFFFFFFF);

        String selectedGender = parent.getItemAtPosition(pos).toString();
        // Handle the selected item as needed

    }

    // Implement the onNothingSelected method of the OnItemSelectedListener interface
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }



    @Override
    public void onBackPressed(){
        onBackPressed();
    }


}