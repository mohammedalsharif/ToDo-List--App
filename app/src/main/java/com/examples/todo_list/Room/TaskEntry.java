package com.examples.todo_list.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "task")
@TypeConverters(DateConverter.class)
public class TaskEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull

    private String description;
    @NonNull
    private int priority;
    private Date updateAt;

    @Ignore
    public TaskEntry(String description, int priority, Date updateAt) {
        this.description = description;
        this.priority = priority;
        this.updateAt = updateAt;
    }

    public TaskEntry(int id, String description, int priority, Date updateAt) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
