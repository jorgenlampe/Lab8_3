package com.example.lab8_3.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab8_3.entities.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * from Category ORDER BY description ASC")
    LiveData<List<Category>> getAllCategories();
    @Query("SELECT COUNT(id) FROM Category")
    int getAllCategoriesCount();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);
    @Delete
    void deleteCategory(Category category);
    @Update
    void editCategory(Category category);
    @Query("DELETE FROM Category")
    void deleteAllCategories();
}
