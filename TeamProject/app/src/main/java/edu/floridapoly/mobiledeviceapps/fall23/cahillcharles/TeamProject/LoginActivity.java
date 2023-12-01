package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Variables for views
    EditText uploadNameTextView;
    EditText uploadHeightTextView;
    EditText uploadWeightTextView;
    EditText uploadCalorieTextView;
    RadioButton radioCut, radioGain, radioMaintain;
    Spinner genderSpinner;
    Button nextBtn;

    private SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME = "MyAppPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find views
        uploadNameTextView = findViewById(R.id.uploadName);
        uploadHeightTextView = findViewById(R.id.uploadHeight);
        uploadWeightTextView = findViewById(R.id.uploadWeight);
        uploadCalorieTextView = findViewById(R.id.uploadCalorie);
        nextBtn = findViewById(R.id.nextButton);
        radioCut = findViewById(R.id.radio_cut);
        radioGain = findViewById(R.id.radio_gain);
        radioMaintain = findViewById(R.id.radio_maintain);
        genderSpinner = findViewById(R.id.gender_spinner);

        // Shared preferences
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        // Retrieve values from shared preferences and set them to the respective TextViews
        uploadNameTextView.setText(sharedPreferences.getString(getString(R.string.name_key), ""));
        uploadHeightTextView.setText(String.valueOf(sharedPreferences.getString(Integer.toString(R.string.height_key), "")));
        uploadWeightTextView.setText(String.valueOf(sharedPreferences.getString(Integer.toString(R.string.weight_key), "")));
        uploadCalorieTextView.setText(String.valueOf(sharedPreferences.getString(Integer.toString(R.string.calorie_key), "")));

        // Create an ArrayAdapter for the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(this);

        // Set the background color of the spinner
        genderSpinner.setBackgroundColor(getResources().getColor(R.color.your_color));

        // Load the saved value from SharedPreferences
        String savedGender = sharedPreferences.getString(getString(R.string.spinner_key), "");

        // Set the default selection in the Spinner
        if (!TextUtils.isEmpty(savedGender)) {
            int position = getPositionForItem(genderSpinner, savedGender);
            if (position != -1) {
                genderSpinner.setSelection(position);
            }
        }

        // Set an onClickListener for the Next button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSave();
            }
        });

        // Set CompundButton onCheckedChangeListener for all RadioButtons
        CompoundButton.OnCheckedChangeListener radioCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Save the selected radio button to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(getString(R.string.radio_group), buttonView.getId());
                    editor.apply();
                }
            }
        };

    // Set the onCheckedChangeListener for each RadioButton
        radioCut.setOnCheckedChangeListener(radioCheckedChangeListener);
        radioGain.setOnCheckedChangeListener(radioCheckedChangeListener);
        radioMaintain.setOnCheckedChangeListener(radioCheckedChangeListener);

    // Retrieve and set the selected radio button from SharedPreferences
        int selectedRadioButtonId = sharedPreferences.getInt(getString(R.string.radio_group), -1);
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            if (selectedRadioButton != null) {
                selectedRadioButton.setChecked(true);
            } else {
                // Log an error or handle the case where the RadioButton is not found
                Log.d("RadioButtonTag", "RadioButton not found for ID: " + selectedRadioButtonId);
            }
        }
    }

    private void onSave() {
        // Validate input fields
        String name = String.valueOf(uploadNameTextView.getText()).trim();
        String heightStr = String.valueOf(uploadHeightTextView.getText()).trim();
        String weightStr = String.valueOf(uploadWeightTextView.getText()).trim();
        String calorieStr = String.valueOf(uploadCalorieTextView.getText()).trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(heightStr) ||
                TextUtils.isEmpty(weightStr) || TextUtils.isEmpty(calorieStr)) {
            // Display a Toast message if any field is empty
            Toast.makeText(this, "Please enter values for all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate radio button selection
        if (!radioCut.isChecked() && !radioGain.isChecked() && !radioMaintain.isChecked()) {
            // Display a Toast message if no radio button is selected
            Toast.makeText(this, "Please select a goal (Cut, Gain, or Maintain)", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate spinner selection
        if (genderSpinner.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
            // Display a Toast message if no gender is selected
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Clear existing values in SharedPreferences
            SharedPreferences.Editor clearEditor = sharedPreferences.edit();
            clearEditor.clear();
            clearEditor.apply();

            // Save user input to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.name_key), name);
            editor.putInt(getString(R.string.height_key), Integer.parseInt(heightStr));
            editor.putInt(getString(R.string.weight_key), Integer.parseInt(weightStr));
            editor.putInt(getString(R.string.calorie_key), Integer.parseInt(calorieStr));

            // Save the selected radio button to SharedPreferences
            int selectedRadioButtonId = getSelectedRadioButtonId();
            if (selectedRadioButtonId != -1) {
                editor.putInt(getString(R.string.radio_group), selectedRadioButtonId);
            }

            // Save the selected gender to SharedPreferences
            String selectedGender = genderSpinner.getSelectedItem().toString();
            editor.putString(getString(R.string.spinner_key), selectedGender);

            editor.apply();

            // Start the second activity
            Intent intent = new Intent(LoginActivity.this, MainScreen.class);
            startActivity(intent);
        } catch (NumberFormatException e) {
            // Handle the case where height, weight, or calorie cannot be parsed as integers
            Toast.makeText(this, "Invalid input for height, weight, or calorie", Toast.LENGTH_SHORT).show();
        }
    }



    // AdapterView to handle Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // Handle the selected item as needed
        ((TextView) parent.getChildAt(0)).setTextColor(0XFFFFFFFF);
        String selectedGender = parent.getItemAtPosition(pos).toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.spinner_key), selectedGender);
        editor.apply();
    }

    // function if nothing selected from the Spinner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }

    // Helper method to get the position of an item in the Spinner
    private int getPositionForItem(Spinner spinner, String item) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(item)) {
                return i;
            }
        }
        return -1; // Returns -1 if the item is not found
    }

    // Helper method to get the ID of the selected radio button
    private int getSelectedRadioButtonId() {
        if (radioCut.isChecked()) {
            return radioCut.getId();
        } else if (radioGain.isChecked()) {
            return radioGain.getId();
        } else if (radioMaintain.isChecked()) {
            return radioMaintain.getId();
        }
        return -1; // If no radio button is selected, return -1
    }

}
