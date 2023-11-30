package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.ViewHolder> {
    private ArrayList<WorkoutModelClass> workoutList;
    private Context context;
    WorkoutDatabase db;

    //create the adapter and set the context, get the list of workouts, and start the database
    public ExpandableAdapter(Context context, ArrayList<WorkoutModelClass> workoutList) {
        this.context = context;
        this.workoutList = workoutList;
        db = new WorkoutDatabase(context);
    }


    @NonNull
    @Override
    public ExpandableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the view so that the workouts mold to the specific model
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.workouts_recyclerview, parent, false);
        return new ExpandableAdapter.ViewHolder(view);
    }

    //each element in the list
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //get current workout
        final WorkoutModelClass workout = workoutList.get(position);

        //set the name and description fields for the layout
        holder.workoutNameView.setText(workout.getName());
        holder.workoutDescriptionText.setText(workout.getDescription());


        //if user clicks dropdown button
        holder.dropDownWorkoutsView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //if the view is expanded, make it disappear
                if(holder.workoutDescriptionHolder.getVisibility() == View.VISIBLE) {
                    holder.workoutDescriptionHolder.setVisibility(View.GONE);
                    holder.workoutDescriptionText.setVisibility(View.GONE);
                    //set arrow so that it points up indicating the user click it to make the description disappear
                    holder.dropDownWorkoutsView.setImageResource(R.drawable.dropdown_arrow_up);
                }
                //if view hasn't been expanded, expand
                else {
                    holder.workoutDescriptionHolder.setVisibility(View.VISIBLE);
                    holder.workoutDescriptionText.setVisibility(View.VISIBLE);
                    //switch arrow so that it points down indicating to the user that clicking it will display the description
                    holder.dropDownWorkoutsView.setImageResource(R.drawable.dropdown_arrow);
                }
            }
        });

        //user clicks the delete button
        holder.deleteWorkoutsView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //alert to maker sure the user actually wants to delete this workout
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Workout?");
                builder.setMessage("Are you sure you want to delete this workout?");
                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    //delete workout from database and  arraylist so it doesn't appear anywhere
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //remove from database
                        db.deleteWorkout(workout.getId());
                        //remove from arraylist
                        workoutList.remove(workout);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //nothing they just didn't delete the workout
                    }
                });

                //display alert dialog
                builder.create().show();
            }
        });
    }

    //for the recycler view to know how many items to display, will correspond to however many workouts we have
    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView deleteWorkoutsView;
        ImageView dropDownWorkoutsView ;
        TextView workoutNameView;
        TextView workoutDescriptionText;
        LinearLayoutCompat workoutDescriptionHolder;

        ViewHolder(View workoutView) {
            super(workoutView);
            //delete and dropdown buttons
            dropDownWorkoutsView = workoutView.findViewById(R.id.dropdown_workout_image);
            deleteWorkoutsView = workoutView.findViewById(R.id.delete_workout_image);

            //name of workout and description text fields
            workoutNameView = workoutView.findViewById(R.id.workout_name);
            workoutDescriptionText = workoutView.findViewById(R.id.workout_description_tview);
            workoutDescriptionHolder = workoutView.findViewById(R.id.workout_description);

            //initially the description will not appear, will wait for user to select dropdown
            workoutDescriptionHolder.setVisibility(View.GONE);
            workoutDescriptionText.setVisibility(View.GONE);
        }
    }
}
