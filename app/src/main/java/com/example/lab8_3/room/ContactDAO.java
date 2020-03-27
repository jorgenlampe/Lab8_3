package com.example.lab8_3.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.lab8_3.entities.CategoryWithContacts;
import com.example.lab8_3.entities.Contact;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("SELECT * from Contact ORDER BY forNavn ASC")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM Contact WHERE category_id = :categoryId ORDER BY forNavn ASC")
            LiveData<List<Contact>> getContactsByCategory(long categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);

    @Query("DELETE FROM Contact")
    void deleteAllContacts();


    @Delete
    void deleteContact(Contact contact);
    /* EVT:
    @Query("DELETE FROM Contact WHERE id = :contactId")
    void deleteContact(int contactId);
    */
    @Insert
    void insertAll(List<Contact> contacts);

    @Update
    void editContact(Contact contact);

    @Query("SELECT COUNT(id) FROM Contact")
    LiveData<Integer> getAllContactsCount();
    // SE: https://developer.android.com/training/datastorage/room/relationships
    // Henter alle Category-objekter som hver vil inneholde en liste med
    //Contact-objekter.
    @Transaction
    @Query("SELECT * FROM Category")
    public LiveData<List<CategoryWithContacts>>
    getCategoriesWithContacts();
}
