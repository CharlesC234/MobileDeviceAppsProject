package edu.floridapoly.mobiledeviceapps.fall23.cahillcharles.TeamProject;

import android.content.Context;
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

    public ExpandableAdapter(Context context, ArrayList<WorkoutModelClass> workoutList) {
        this.context = context;
        this.workoutList = workoutList;
    }


    @NonNull
    @Override
    public ExpandableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.workouts_recyclerview, parent, false);
        return new ExpandableAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WorkoutModelClass workout = workoutList.get(position);

        holder.workoutNameView.setText(workout.getName());
        holder.workoutDescriptionText.setText(workout.getDescription());


        holder.dropDownWorkoutsView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(holder.workoutDescriptionHolder.getVisibility() == View.VISIBLE) {
                    holder.workoutDescriptionHolder.setVisibility(View.GONE);
                    holder.workoutDescriptionText.setVisibility(View.GONE);
                }
                else {
                    holder.workoutDescriptionHolder.setVisibility(View.VISIBLE);
                    holder.workoutDescriptionText.setVisibility(View.VISIBLE);
                }
            }
        });
    }

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
            dropDownWorkoutsView = workoutView.findViewById(R.id.dropdown_workout_image);
            deleteWorkoutsView = workoutView.findViewById(R.id.delete_workout_image);
            workoutNameView = workoutView.findViewById(R.id.workout_name);
            workoutDescriptionText = workoutView.findViewById(R.id.workout_description_tview);
            workoutDescriptionHolder = workoutView.findViewById(R.id.workout_description);
            workoutDescriptionHolder.setVisibility(View.GONE);
            workoutDescriptionText.setVisibility(View.GONE);
        }
    }
}
