package com.example.lab8_3;


import android.app.Application;
import android.graphics.LightingColorFilter;

import androidx.lifecycle.LiveData;

import com.example.lab8_3.entities.Category;
import com.example.lab8_3.entities.Contact;
import com.example.lab8_3.room.CategoryDAO;
import com.example.lab8_3.room.ContactDAO;
import com.example.lab8_3.room.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {
    private ContactDAO contactDAO;
    private CategoryDAO categoryDAO;
 //. . .

    private LiveData<List<Contact>> mAllContacts;   //må gjøres noe med
    private LiveData<List<Category>> mAllCategories;  //må gjøres noe med
    private LiveData<Integer> mAllContactsCount;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDAO = db.contactDAO();
        categoryDAO = db.categoryDAO();
 //. . .
        mAllContacts = contactDAO.getAllContacts();
        mAllCategories = categoryDAO.getAllCategories();
        mAllContactsCount = contactDAO.getAllContactsCount();
 //. . .
    }
// . . .
    public LiveData<List<Contact>> getContactsByCategory(int categoryId) {
        return contactDAO.getContactsByCategory(categoryId);

    }

    public LiveData<List<Contact>> getAllContacts(){
        return mAllContacts;
    }

    public LiveData<List<Category>> getAllCategories(){
        return mAllCategories;
    }

    public LiveData<Integer> getAllContactsCount() {
        return mAllContactsCount;
    }
 //. . .
    // NB! Bruker WriteExecutor:
    public void deleteAllContacts() {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            contactDAO.deleteAllContacts();
        });

    }
    public void insertContact(Contact contact) {
        
    }
}
