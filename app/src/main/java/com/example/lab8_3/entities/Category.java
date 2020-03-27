package com.example.lab8_3.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

   @NonNull
   @PrimaryKey(autoGenerate = true)
   private long id;

   @NonNull
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public Category(@NonNull String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}

