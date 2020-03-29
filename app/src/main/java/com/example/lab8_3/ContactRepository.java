package com.example.lab8_3;


import android.app.Application;
import android.graphics.LightingColorFilter;

import androidx.lifecycle.LiveData;

import com.example.lab8_3.entities.Category;
import com.example.lab8_3.entities.CategoryWithContacts;
import com.example.lab8_3.entities.Contact;
import com.example.lab8_3.room.CategoryDAO;
import com.example.lab8_3.room.ContactDAO;
import com.example.lab8_3.room.ContactRoomDatabase;

import java.util.List;

import static com.example.lab8_3.room.ContactRoomDatabase.databaseWriteExecutor;

public class ContactRepository {
    private ContactDAO contactDAO;
    private CategoryDAO categoryDAO;
 //. . .

    private LiveData<List<Contact>> mAllContacts;
    private LiveData<List<Category>> mAllCategories;
    private LiveData<Integer> mAllContactsCount;
    private LiveData<List<CategoryWithContacts>> mAllCategoriesWithContacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDAO = db.contactDAO();
        categoryDAO = db.categoryDAO();
 //. . .
        mAllContacts = contactDAO.getAllContacts();
        mAllCategories = categoryDAO.getAllCategories();
        mAllContactsCount = contactDAO.getAllContactsCount();
        mAllCategoriesWithContacts = contactDAO.getCategoriesWithContacts();
 //. . .
    }
// . . .
    public LiveData<List<Contact>> getContactsByCategory(long categoryId) {
        return contactDAO.getContactsByCategory(categoryId);

    }

    public LiveData<List<CategoryWithContacts>>
    getAllCategoriesWithContacts() {
        return mAllCategoriesWithContacts;
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
        databaseWriteExecutor.execute(() -> {
            contactDAO.deleteAllContacts();
        });

    }
    public void insertContact(Contact contact) {

        databaseWriteExecutor.execute(()-> {

               contactDAO.insert(contact);
            });

    }

    public void deleteContact(Contact contact) {

        databaseWriteExecutor.execute(()-> {

            contactDAO.deleteContact(contact);
        });

    }
    public void editContact(Contact contact) {
        databaseWriteExecutor.execute(() -> {
            contactDAO.editContact(contact);
        });
    }

    public Contact getContact(int id){
        return contactDAO.getContact(id);

    }
}
