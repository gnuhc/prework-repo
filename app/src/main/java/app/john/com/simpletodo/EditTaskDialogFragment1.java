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

// Dialog Fragment for editing task
public class EditTaskDialogFragment1 extends DialogFragment{
    private EditText mEditText;
    private Button taskButt;
    private Spinner prioritySpinner;
    private OnCompleteListener1 listener;

    private int position;
    private String eText;

    public EditTaskDialogFragment1(){

    }
    // Listener to send back data to MainActivity
    public static interface OnCompleteListener1 {
        public abstract void onComplete(String task, String priority, int pos);
    }

    // New instance of fragment
    public static EditTaskDialogFragment1 newInstance(String title){
        EditTaskDialogFragment1 frag = new EditTaskDialogFragment1();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    // Inflate the corresponding view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        eText = getArguments().getString("eText");
        position = getArguments().getInt("position");

        return inflater.inflate(R.layout.fragment_edit_task1, container);
    }

    // Build and show the Dialog Fragment
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mEditText = (EditText) view.findViewById(R.id.editTask1);


        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);

        mEditText.setText(eText);
        mEditText.setSelection(mEditText.getText().length());
        mEditText.requestFocus();

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        prioritySpinner = (Spinner) view.findViewById(R.id.spinnerPriority1);
        prioritySpinner.setPrompt("Choose your priority level: ");

        //When button is clicked, preserve data to send back to parent activity
        taskButt = (Button) view.findViewById(R.id.buttonTask1);
        taskButt.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        listener.onComplete(mEditText.getText().toString(), prioritySpinner.getSelectedItem().toString(), position);
                        dismiss();
                    }
                });
    }

    // When listener is attached to MainActivity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnCompleteListener1)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener1");
        }
    }
}
