package com.examples.todo_list.Room;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public class Repository {

    TaskDao taskDao;

    public Repository(Application application) {
        AppDatabase database = AppDatabase.getINSTANCE(application);
        taskDao = database.taskDao();
    }

    public LiveData<List<TaskEntry>> loadAllTask() {

        return taskDao.loadAllTask();
    }


    public void insertTask(TaskEntry taskEntry) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.insertTask(taskEntry);
            }
        });
    }


    public void deleteTask(TaskEntry taskEntry) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.deleteTask(taskEntry);
            }
        });

    }


    public void updateTask(TaskEntry taskEntry) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.updateTask(taskEntry);
            }

        });
    }

    public LiveData<TaskEntry> loadTaskById(int id) {

               return taskDao.loadTaskById(id);



    }


}
