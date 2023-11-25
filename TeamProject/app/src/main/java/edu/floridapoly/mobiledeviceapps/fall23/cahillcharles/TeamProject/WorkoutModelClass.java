package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

public class WorkoutModelClass {

    // declare variables
    private String name/*, description*/;

    // constructor
    public WorkoutModelClass(String name/*, String description*/) {
        this.name = name;
        //this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public String getDescription() {
        return description;
    }

    public void setDescription(String email) {
        this.description = description;
    }
    */
}
