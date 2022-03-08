package com.examples.todo_list.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TaskEntry.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract  TaskDao taskDao();

    private static final int THREAD_NUMBER_FIXED = 4;
    static final ExecutorService executorService= Executors.newFixedThreadPool(THREAD_NUMBER_FIXED);

    private static volatile AppDatabase INSTANSE;

    public static AppDatabase getINSTANCE(Context context) {
        if (INSTANSE==null){
            synchronized (AppDatabase.class){
                if (INSTANSE==null){
                    INSTANSE= Room.databaseBuilder(
                            context.getApplicationContext(),AppDatabase.class
                            ,"AppDatabase").build();
                }
            }
        }
        return INSTANSE;
    }


}
