package com.examples.todo_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.examples.todo_list.Room.MyViewModel;
import com.examples.todo_list.Room.TaskEntry;
import com.examples.todo_list.databinding.ActivityAddTaskBinding;
import com.examples.todo_list.databinding.TaskLayoutBinding;

import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityAddTaskBinding binding;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;
    Calendar calendar;
    MyViewModel model;
    int idItemUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        calendar = Calendar.getInstance();
        model = new ViewModelProvider(this).get(MyViewModel.class);
        binding.saveButton.setOnClickListener(this);
        Intent intent = getIntent();

        idItemUpdate = intent.getIntExtra(MainActivity.ID_ITEM, -1);

        if (idItemUpdate != -1) {
            binding.saveButton.setText("UPDATE");
            model.loadTaskById(idItemUpdate).observe(this, new Observer<TaskEntry>() {
                @Override
                public void onChanged(TaskEntry taskEntry) {
                    binding.editTextTaskDescription.setText(taskEntry.getDescription());
                    setPriorityInViews(taskEntry.getPriority());
                }
            });


        }

    }

    @Override
    public void onClick(View view) {
        String description = binding.editTextTaskDescription.getText().toString();
        int priority = getPriorityFromViews();
        TaskEntry entry = new TaskEntry(description, priority, calendar.getTime());
        if (idItemUpdate != -1) {
            entry.setId(idItemUpdate);
            model.updateTask(entry);
        } else {
            model.insertTask(entry);
        }
        onBackPressed();
    }

    public int getPriorityFromViews() {
        int priority = 1;
        int checkedId = binding.radioGroup.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.radButton1:
                priority = PRIORITY_HIGH;
                break;
            case R.id.radButton2:
                priority = PRIORITY_MEDIUM;
                break;
            case R.id.radButton3:
                priority = PRIORITY_LOW;
        }
        return priority;
    }

    public void setPriorityInViews(int priority) {
        switch (priority) {
            case PRIORITY_HIGH:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.radButton1);
                break;
            case PRIORITY_MEDIUM:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.radButton2);
                break;
            case PRIORITY_LOW:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.radButton3);
        }
    }
}