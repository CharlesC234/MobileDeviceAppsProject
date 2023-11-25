package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ChatScreen extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    String responseText;
    String parsedText = "";
    StringBuffer response;
    URL url;
    String apiKey = "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";
    String model = "gpt-3.5-turbo";
    String urlStr = "https://api.openai.com/v1/chat/completions";
    Boolean workoutMealFlag = false;
    int mealPrefKeyNum = 0;

    // Declare button variables
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);


        // find navigation bar using Resource ID
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        setupBottomNavigation();

        final Button btnGetServerData = (Button) findViewById(R.id.send);
        EditText editText = findViewById(R.id.Input);
        String initialPrompt = "How are you today?";
        editText.setText(initialPrompt);

        Button btnGenerateMeals = findViewById(R.id.example1);
        Button btnGenerateWorkouts = findViewById(R.id.example2);
        TextView textViewOutput = findViewById(R.id.textOutput);

        SharedPreferences shGenerateWorkoutMeal = getSharedPreferences("MyAppPreferences", MODE_APPEND);
        String strName = shGenerateWorkoutMeal.getString("Name", "");
        int height = shGenerateWorkoutMeal.getInt("Height",0);
        int weight = shGenerateWorkoutMeal.getInt("Weight",0);
        int calorie = shGenerateWorkoutMeal.getInt("Calorie", 0);
        int calorieDecision = shGenerateWorkoutMeal.getInt("radioGroup",0);
        String strGender = shGenerateWorkoutMeal.getString("Gender", "");





        btnGenerateWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                workoutMealFlag = true;
               String prompt = "Provide workouts based off inputs:" +
                       "Weight: " + weight +
                       "Height: " + height +
                       "Gender: " + strGender +
                       "Calorie Goal: " + calorie +
                       "Give only name of the workout and none of the user information. " +
                       "No dates, no amount that needs to be done, no days, no descriptions, no types. " +
                       "Put it in one line with hyphens as delimiters with no numbers, no spaces, no bullet points, no quotes, no underscores.";

               /*
               Provide workouts based off inputs:
                       Weight: 160
                       Height: 6ft
                       Gender: Male
                       Calorie Goal: 2000
                Put in JSON format. Give only name of the workout in the JSON file and none of the user information.
                No dates, no amount that needs to be done, no days, no descriptions, no types
                */



                //String prompt = "say something";

                new GetServerData().execute(prompt);
               //textViewOutput.setText(parsedText);


            }
        });

        btnGenerateMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                workoutMealFlag = false;



                String strCalorieDecision = "";

                switch (calorieDecision)
                {
                    case 2131362107:
                       strCalorieDecision = "cut";
                        break;
                    case 2131362108:
                        strCalorieDecision = "gain";
                        break;
                    case 2131362109:
                        strCalorieDecision = "maintain";
                        break;
                    default:
                        strCalorieDecision = "cut";
                        break;

                }

                String prompt = "Provide meals based off inputs:" +
                        "Weight: " + weight +
                        "Height: " + height +
                        "Gender: " + strGender +
                        "Calorie Goal: " + calorie +
                        "Calorie Decision: " + calorieDecision +
                        "Give only name of the meal and none of the user information. " +
                        "No dates, no amount that needs to be done, no days, no descriptions, no types. " +
                        "Put it in one line with hyphens as delimiters with no numbers, no spaces, no bullet points, no quotes, no underscores.";



                //String prompt = "say something";

                new GetServerData().execute(prompt);
                //textViewOutput.setText(parsedText);

                //String strSecondParseJSON = extractMessageFromJSONResponse(parsedText);
                //textViewOutput.setText(strSecondParseJSON);

            }
        });

        btnGetServerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText editText = findViewById(R.id.Input);
                String prompt = editText.getText().toString();
                Log.i("WebService", "WebService URL: " + prompt);
                // Use AsyncTask execute Method To Prevent ANR Problem
                new GetServerData().execute(prompt);
            }
        });

    }


    class GetServerData extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            return getWebServiceResponseData((String) objects[0]);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            //TextView textResponse = findViewById(R.id.textOutput);
            //textResponse.setText(responseText);
            String[] strHoldWorkoutMeal = parsedText.split("-");

            TextView textParsed = findViewById(R.id.txtJSONParsed);
            textParsed.setText("");

            SharedPreferences mealPref = getSharedPreferences("mealPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mealPref.edit();


            for (String workoutMealString : strHoldWorkoutMeal) {

                if(!workoutMealString.isEmpty())
                {

                    textParsed.append(workoutMealString + "\n");

                }

            }

            DatabaseHelperWorkout dbWrkout = new DatabaseHelperWorkout(getApplicationContext());

            for (String workoutMeal : strHoldWorkoutMeal) {

                if(workoutMealFlag)
                {

                    WorkoutModelClass wkmClass = new WorkoutModelClass(workoutMeal);
                    dbWrkout.storeData(wkmClass);


                }
                else
                {

                    editor.putString("meal-" + mealPrefKeyNum, workoutMeal);
                    mealPrefKeyNum++;

                }

            }

            editor.apply();



        }
    }

    protected String getWebServiceResponseData(String prompt) {

        try {
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setReadTimeout(5000);
            //connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // Form the request body
            String requestBody = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(requestBody);
            writer.flush();
            writer.close();

            int responseCode = connection.getResponseCode();

            Log.d("WebService", "Response code: " + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                // Reading response from input Stream
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String output;
                response = new StringBuffer();

                while ((output = br.readLine()) != null) {
                    response.append(output);
                }
                br.close();
                responseText = response.toString();
                Log.i("WebService", responseText);
                parsedText = extractMessageFromJSONResponse(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String extractMessageFromJSONResponse(String response) {
        String contentStr = "";
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray jsonArrayList = jsonResponse.getJSONArray("choices");
            JSONObject firstItem = jsonArrayList.getJSONObject(0);
            JSONObject message  = firstItem.getJSONObject("message");
            contentStr = message.getString("content");
            /*
{
    "id": "chatcmpl-8G7cWQeHrj01ygHvvDG9T7ogkY2Nj",
    "object": "chat.completion",
    "created": 1698853232,
    "model": "gpt-3.5-turbo-0613",
    "choices": [
        {
            "index": 0,
            "message": {
                "role": "assistant",
                "content": "As an AI, I don't have feelings, but I'm here to assist you with any questions or tasks you have. How can I help you today?"
            },
            "finish_reason": "stop"
        }
    ],
    "usage": {
        "prompt_tokens": 12,
        "completion_tokens": 32,
        "total_tokens": 44
    }
}
             */


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return contentStr;
    }



    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        startActivity(new Intent(ChatScreen.this, ProfileScreen.class));
                        showToast("Proceeding to Profile Screen");
                        return true;
                    case R.id.home:
                        startActivity(new Intent(ChatScreen.this, MainScreen.class));
                        showToast("Proceeding to Home Screen");
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(ChatScreen.this, ChatScreen.class));
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