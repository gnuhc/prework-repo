package app.john.com.simpletodo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

// Main Activity Controller
public class MainActivity extends AppCompatActivity
        implements EditTaskDialogFragment.OnCompleteListener,
                   EditTaskDialogFragment1.OnCompleteListener1{

    private ArrayList<Task> items;
    private CustomTasksAdapter itemsAdapter;
    private ListView lvItems;

    private String previousTask; //task that was edited
    private String deletedTask; //task that was deleted

    SQLiteDatabase db; //database object

    // Inflate view, set up database, and setup array
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<Task>();
        itemsAdapter = new CustomTasksAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);

        //items.add(new Task("DOO", "HIGH"));

        db=openOrCreateDatabase("TaskDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tasks(task VARCHAR,priority VARCHAR);");

        Cursor c = db.rawQuery("SELECT * FROM tasks", null);
        while(c.moveToNext())
        {
            items.add(new Task(c.getString(0),c.getString(1)));
        }
        itemsAdapter.notifyDataSetChanged();
        setupListViewListener();

    }

    // Show dialog for adding new tasks
    public void onAddItem(View v){
        showDialog();
    }

    // Handles deleting of tasks and showing dialog for editing tasks
    private void setupListViewListener(){

        //delete item
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        deletedTask = items.get(pos).getTask();
                        items.remove(pos);
                        Cursor c = db.rawQuery("SELECT * FROM tasks WHERE task='"+deletedTask+"'", null);
                        if(c.moveToFirst())
                        {
                            db.execSQL("DELETE FROM tasks WHERE task='"+deletedTask+"'");
                        }
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );

        //edit item
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                               View item, int pos, long id){
                        showDialogEdit(pos);
                    }
                }
        );
    }

    // Show dialog for adding task
    private void showDialog(){
        FragmentManager fm = getSupportFragmentManager();
        EditTaskDialogFragment fg = EditTaskDialogFragment.newInstance("Add Task");
        fg.show(fm, "fragment_edit_task");
    }

    // Show dialog for editing task
    private void showDialogEdit(int pos){
        previousTask = items.get(pos).getTask();
        FragmentManager fm = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("eText", items.get(pos).getTask());
        bundle.putInt("position", pos);

        EditTaskDialogFragment1 fg = EditTaskDialogFragment1.newInstance("Edit Task");
        fg.setArguments(bundle);

        fg.show(fm, "fragment_edit_task1");
    }

    // Handles after add dialog has been closed and information has been updated
    public void onComplete(String task, String priority) {
        itemsAdapter.add(new Task(task, priority));
        db.execSQL("INSERT INTO tasks VALUES('"+task+"','"+priority+"');");
        itemsAdapter.notifyDataSetChanged();
    }

    // Handles after edit dialog has been closed and information has been updated
    public void onComplete(String task, String priority, int pos){
        items.get(pos).setTask(task);
        items.get(pos).setPriority(priority);
        Cursor c = db.rawQuery("SELECT * FROM tasks WHERE task='"+previousTask+"'", null);
        if(c.moveToFirst()) {
            db.execSQL("UPDATE tasks SET task='" + task + "',priority='" + priority +
                    "' WHERE task='" + previousTask + "'");
        }
        itemsAdapter.notifyDataSetChanged();
    }
}
