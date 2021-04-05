package com.example.tasklistproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasklistproject.R;
import com.example.tasklistproject.TaskAdapter;
import com.example.tasklistproject.TaskItem;
import com.example.tasklistproject.TaskListener;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = null;

        ArrayList<TaskItem> taskList = new ArrayList<>();
        mAdapter = new TaskAdapter(taskList);

        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 1:
                v = inflater.inflate(R.layout.fragment_main, container, false);
                taskList.add(new TaskItem(R.drawable.ic_lens, "Faire le projet java ", "15/05/2021"));
                taskList.add(new TaskItem(R.drawable.ic_lens, "Projet vuejs à faire", "20/05/2021"));
                taskList.add(new TaskItem(R.drawable.ic_lens, "Finir le projet Symfony", "01/06/2021"));

                mRecyclerView = v.findViewById(R.id.taskListRecyler);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getContext());

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                break;

            case 2:
                v = inflater.inflate(R.layout.fragment_task, container, false);

                Button createTaskBtn = (Button) v.findViewById(R.id.createTaskBtn);
                createTaskBtn.setOnClickListener(new TaskListener(v, taskList, mAdapter));
                break;

            case 3:
                v = inflater.inflate(R.layout.fragment_calendar, container, false);
                break;
        }
        return v;
    }
}