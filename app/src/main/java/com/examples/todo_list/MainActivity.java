package com.examples.todo_list;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;

import com.examples.todo_list.Room.MyViewModel;
import com.examples.todo_list.Room.TaskEntry;
import com.examples.todo_list.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String ID_ITEM = "idItem";
    ActivityMainBinding binding;
    MyViewModel viewModel;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.ftBtn.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.loadAllTask().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(List<TaskEntry> taskEntries) {
                adapter.setTasks(taskEntries);
            }
        });

        initRec();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               int id= viewHolder.getAdapterPosition();
              TaskEntry entry = adapter.getTaskById(id);
              viewModel.deleteTask(entry);
            }
        }).attachToRecyclerView(binding.recyclerView);


    }

    private void initRec() {
        adapter = new TaskAdapter(this, new TaskAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(int itemId) {
                Intent intent =new Intent(getBaseContext(),AddTaskActivity.class);
                intent.putExtra(ID_ITEM,itemId);
                startActivity(intent);

            }
        });

        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,AddTaskActivity.class));
    }
}