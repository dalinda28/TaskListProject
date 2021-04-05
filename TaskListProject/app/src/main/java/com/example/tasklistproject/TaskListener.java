package com.example.tasklistproject;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class TaskListener implements View.OnClickListener {

    private View fragment;
    ArrayList<TaskItem> taskList;
    RecyclerView.Adapter mAdapter;

    public TaskListener(View fragment, ArrayList taskList, RecyclerView.Adapter mAdapter) {
        this.fragment = fragment;
        this.taskList = taskList;
        this.mAdapter = mAdapter;
    }

    @Override
    public void onClick(View v) {
        EditText nameTaskEdit = fragment.findViewById(R.id.nameTask);
        EditText dateTaskEdit = fragment.findViewById(R.id.dateTask);

        Log.d("====", nameTaskEdit.getText().toString() + " - " +dateTaskEdit.getText().toString());

        taskList.add(0, new TaskItem( R.drawable.ic_lens , nameTaskEdit.getText().toString(), dateTaskEdit.getText().toString() ));

        mAdapter.notifyItemInserted(0);

        Snackbar.make(fragment, "Tâche créée", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        nameTaskEdit.setText("");
        dateTaskEdit.setText("");
    }
}