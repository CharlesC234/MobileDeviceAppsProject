package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.graphics.Bitmap;

public class ProfileModelClass {

    // declare variables
    private String name, gender, email;
    private Bitmap image;
    private static int currentCalorie;
    private static int calorieGoal;

    // constructor
    public ProfileModelClass(String name, String email, int currentCalorie, int calorieGoal, Bitmap image) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.currentCalorie = currentCalorie;
        this.calorieGoal = calorieGoal;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static int getCurrentCalorie(){
        return currentCalorie;
    }

    public void setCurrentCalorie(int currentCalorie){
        this.currentCalorie = currentCalorie;
    }

    public static int getCalorieGoal(){
        return currentCalorie;
    }

    public void setCalorieGoal(int calorieGoal){
        this.calorieGoal = calorieGoal;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
