package app.john.com.simpletodo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gnuhc on 9/2/2017.
 */

// Adapter for Task & Priority List
public class CustomTasksAdapter extends ArrayAdapter {

    public CustomTasksAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        // Get the task for this position
        Task task = (Task) getItem(position);

        // Get view objects from view
        TextView itemTask = (TextView) convertView.findViewById(R.id.itemTask);
        TextView itemPriority = (TextView) convertView.findViewById(R.id.itemPriority);

        // Update task and priority field of Task object
        itemTask.setText(task.getTask());
        itemPriority.setText(task.getPriority());

        // Update colors for priority
        if(itemPriority.getText().equals("HIGH")){
            itemPriority.setTextColor(Color.RED);
        }else if(itemPriority.getText().equals("NORMAL")) {
            itemPriority.setTextColor(Color.parseColor("#FFA500"));
        }else{
            itemPriority.setTextColor(Color.parseColor("#efd510"));
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
