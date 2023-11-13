package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.graphics.Bitmap;

public class ProfileModelClass {

    // declare variables
    private String name, gender, email;
    private Bitmap image;

    // constructor
    public ProfileModelClass(String name, String email, Bitmap image) {
        this.name = name;
        this.email = email;
        this.image = image;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
