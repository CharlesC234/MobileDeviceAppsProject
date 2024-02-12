package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ChatScreen extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    private ListView listView;
    private ChatArrayAdapter chatArrayAdapter;
    private boolean side = false;
    private EditText chatText;

    String responseText;
    String parsedText = "";
    StringBuffer response;
    URL url;
    String apiKey = "INSERT OPEN AI API KEY";
    String model = "gpt-3.5-turbo";
    String urlStr = "https://api.openai.com/v1/chat/completions";
    Boolean workoutMealFlag = false;
    int mealPrefKeyNum = 0;

    String prompt = "";

    // Declare button variables
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        listView = (ListView) findViewById(R.id.msgview);
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.right);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.Input);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });


        // find navigation bar using Resource ID
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        setupBottomNavigation();

        final Button btnGetServerData = (Button) findViewById(R.id.send);
        String initialPrompt = "How are you today?";
        chatText.setText(initialPrompt);

        Button btnGenerateWorkouts = findViewById(R.id.example2);

        SharedPreferences shGenerateWorkoutMeal = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        String strName = shGenerateWorkoutMeal.getString("Name", "");
        int height = shGenerateWorkoutMeal.getInt("Height",0);
        int weight = shGenerateWorkoutMeal.getInt("Weight",0);
        int calorie = shGenerateWorkoutMeal.getInt("Calorie", 0);
        int calorieDecision = shGenerateWorkoutMeal.getInt("radioGroup",0);
        String strGender = shGenerateWorkoutMeal.getString("Gender", "");




        btnGenerateWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendWorkout("Generate Workout");
                String strHoldCalorieDecision = "";

                switch (calorieDecision){
                    case 2131362115:
                        strHoldCalorieDecision = "gain";
                        break;
                    case 2131362116:
                        strHoldCalorieDecision = "maintain";
                        break;
                    default:
                        strHoldCalorieDecision = "cut";
                        break;

                }

                //lengthy prompt that basically just gets the ai to respond in a way that is similar each time
                String workoutPrompt = "pretend you are ae personal trainer. I am " + height + " centimeters tall " + strGender + " and weigh " + weight + " pounds";
                workoutPrompt += "I have a caloric intake goal of " + calorie + " and am looking to " + strHoldCalorieDecision + " weight.";
                workoutPrompt += "generate me a string of workouts. Provide each workout with a unique name and a description of the workout." +
                                 "each workout should be formatted as [WorkoutName]:[WorkoutDescription]"+
                                 "delimit each name from the each description with a colon and each unique workout with a new line. " +
                                 "do NOT include colons within the description. only use a colon to delimit workout names from their descriptions. " +
                                 "there should be NO numbered lists. only separate workouts defined by their workout name and the description. workouts must be separated by new lines characters, workout names and descriptions separated by colons. " +
                                 "do NOT include dates, bullet points, underscores, or quotes. only names and descriptions. you do not need to provide an overall name for the workout plan. just individual workouts."+
                                 "please only provide no more than 5 workouts.";

                workoutMealFlag = true;
                /*String workoutPrompt = "Provide workouts with descriptions of the workout based off inputs:" +
                        "Weight: " + weight+
                        "Height: " + height+
                        "Gender: " + strGender+
                        "Calorie Goal: " + calorie+
                        "Give only names of the workouts and descriptions of the workouts, and sets/reps that need to be done, if applicable. No user information. " +
                        "No dates, no days, no types. " +
                        "Put it in one line with hyphens as delimiters with no numbers, no spaces, no bullet points, no quotes, no underscores."+
                        "Use this for an example response. Don't reformat example. Don't include the example word for word in the response."+
                        "Example: " +
                        "CardioInterval: Alternating between sprinting and jogging to elevate heart rate.-" +
                        "StrengthTraining: Compound exercises like squats, deadlifts, and bench press.-" +
                        "HIIT: High intensity interval training with bodyweight exercises.-" +
                        "Yoga: Dynamic yoga flow incorporating strength and flexibility.";*/

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

                new GetServerData().execute(workoutPrompt);
                //textViewOutput.setText(parsedText);


            }
        });



        btnGetServerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String holdIntitialPrompt = "";
                holdIntitialPrompt = chatText.getText().toString();
                //String prompt = editText.getText().toString();
                Log.i("WebService", "WebService URL: " + holdIntitialPrompt);

                workoutMealFlag = false;
                String strHoldCalorieDecision = "";

                switch (calorieDecision){
                    case 2131362115:
                        strHoldCalorieDecision = "gain";
                        break;
                    case 2131362116:
                        strHoldCalorieDecision = "maintain";
                        break;
                    default:
                        strHoldCalorieDecision = "cut";
                        break;

                }

                String userInputWorkoutPrompt = "pretend you are a personal trainer. I am a " + height + "cm tall " + strGender + " who is " + weight + " pounds and looking to " + strHoldCalorieDecision + " weight. " +
                        holdIntitialPrompt;



                // Use AsyncTask execute Method To Prevent ANR Problem
                new GetServerData().execute(userInputWorkoutPrompt);
                sendChatMessage();
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

            if (workoutMealFlag) {
                //Create arrays to parse data
                //ai responds with two new line characters in between workouts
                String[] strHoldWorkoutDescription = parsedText.split("\n\n");
                ArrayList<String> strHoldDescription = new ArrayList<String>();
                ArrayList<String> strHoldWorkout = new ArrayList<String>();

                //Extract descriptions into
                for (String i : strHoldWorkoutDescription) {
                    //split workout into name and description
                    String[] parts = i.split(":", 2);

                    if (parts.length == 2) {
                        //specific formatting with response, just gets name and description
                        strHoldWorkout.add(parts[0]);
                        strHoldDescription.add(parts[1]);
                    } else {
                        //error in input.
                        //do nothing with input
                    }
                }





                //Print out workout array
                for (String workoutMealString : strHoldWorkoutDescription) {

                    if (!workoutMealString.isEmpty()) {
                        sendChatResponse(workoutMealString);

                    }

                }


                WorkoutDatabase dbWorkout = new WorkoutDatabase(getApplicationContext());

                //Save each workout to the database


                if (strHoldWorkout.size() == strHoldDescription.size()) {

                    for (int i = 0; i < strHoldWorkout.size(); i++) {

                        dbWorkout.insertWorkout(strHoldWorkout.get(i), strHoldDescription.get(i));

                    }

                }


            }
            else
            {

                //TextView textResponse = findViewById(R.id.textOutput);
                //textResponse.setText(responseText);
                String[] strHoldWorkoutMeal = parsedText.split("/");

                SharedPreferences mealPref = getSharedPreferences("mealPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mealPref.edit();


                for (String workoutMealString : strHoldWorkoutMeal) {

                    if (!workoutMealString.isEmpty()) {
                        sendChatResponse(workoutMealString);

                    }

                }

                editor.apply();

            }
        }
    }

    protected String getWebServiceResponseData(String pPrompt) {

        try {
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setReadTimeout(5000);
            //connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // Form the request body
            String requestBody = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + pPrompt + "\"}]}";
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

        Log.i("JSON Content", contentStr);
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

    private boolean sendChatMessage() {
        chatArrayAdapter.add(new ChatMessage(true, chatText.getText().toString()));
        chatText.setText("");
        side = true;
        return true;
    }
    private boolean sendWorkout(String res) {
        chatArrayAdapter.add(new ChatMessage(true, res));
        chatText.setText("");
        side = true;
        return true;
    }
    private boolean sendChatResponse(String res) {
        chatArrayAdapter.add(new ChatMessage(false, res));
        chatText.setText("");
        side = false;
        return true;
    }
}