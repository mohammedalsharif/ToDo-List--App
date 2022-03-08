package com.examples.todo_list.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface TaskDao {
    @Query("SELECT * FROM task ORDER BY priority")
    LiveData<List<TaskEntry>> loadAllTask();

    @Insert
    void insertTask(TaskEntry taskEntry);

    @Delete
    void deleteTask(TaskEntry taskEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskEntry taskEntry);

    @Query("SELECT * FROM task WHERE id=:id")
    LiveData<TaskEntry> loadTaskById(int id);

}
