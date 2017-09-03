package app.john.com.simpletodo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by gnuhc on 9/2/2017.
 */

// Dialog Fragment for adding new task
public class EditTaskDialogFragment extends DialogFragment{
    private EditText mEditText;
    private Button taskButt;
    private Spinner prioritySpinner;
    private OnCompleteListener listener;

    public EditTaskDialogFragment(){

    }

    // Listener to send back data to MainActivity
    public static interface OnCompleteListener {
        public abstract void onComplete(String task, String priority);
    }

    // New instance of fragment
    public static EditTaskDialogFragment newInstance(String title){
        EditTaskDialogFragment frag = new EditTaskDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    // Inflate the corresponding view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_task, container);
    }

    // Build and show the Dialog Fragment
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mEditText = (EditText) view.findViewById(R.id.editTask);

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        mEditText.requestFocus();

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        prioritySpinner = (Spinner) view.findViewById(R.id.spinnerPriority);
        prioritySpinner.setPrompt("Choose your priority level: ");

        //When button is clicked, preserve data to send back to parent activity
        taskButt = (Button) view.findViewById(R.id.buttonTask);
        taskButt.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        listener.onComplete(mEditText.getText().toString(), prioritySpinner.getSelectedItem().toString());
                        dismiss();

                    }
                });
    }

    // When listener is attached to MainActivity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnCompleteListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }
}
