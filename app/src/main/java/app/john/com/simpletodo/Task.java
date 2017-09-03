package app.john.com.simpletodo;

/**
 * Created by gnuhc on 9/2/2017.
 */

// Task Object that holds both task and priority fields
public class Task {
    private String task;
    private String priority;

    public Task(String task, String priority){
        this.task = task;
        this.priority = priority;
    }

    // Getter for task
    public String getTask(){
        return task;
    }

    // Setter for task
    public void setTask(String newTask){
        task = newTask;
    }

    // Getter for priority
    public String getPriority(){
        return priority;
    }

    // Setter for priority
    public void setPriority(String newPriority){
        priority = newPriority;
    }
}
