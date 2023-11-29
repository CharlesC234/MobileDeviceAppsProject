package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import java.util.Date;

public class WorkoutModelClass {

    // declare variables
    private String name, description;
    private Date lastCompleted;
    private int id;

    // constructor
    public WorkoutModelClass(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lastCompleted = null;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String email) {
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setLastCompleted(Date newDate) {
        lastCompleted = newDate;
    }
    public Date getLastCompleted() {
        return lastCompleted;
    }
}
