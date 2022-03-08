package com.examples.todo_list.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    public static Repository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<TaskEntry>> loadAllTask() {
        return repository.loadAllTask();
    }

    public void insertTask(TaskEntry taskEntry) {
        repository.insertTask(taskEntry);
    }

    public void deleteTask(TaskEntry taskEntry) {
        repository.deleteTask(taskEntry);
    }

    public LiveData<TaskEntry> loadTaskById(int id) {
      return repository.loadTaskById(id);

    }
    public void updateTask(TaskEntry taskEntry) {
        repository.updateTask(taskEntry);
    }
}
