package com.example.lab8_3;

import android.app.Application;
import android.graphics.LightingColorFilter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab8_3.entities.Category;
import com.example.lab8_3.entities.Contact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository mRepository;

    private LiveData<List<Contact>> mAllContacts;
    private LiveData<List<Category>> mAllCategories;
    private LiveData<Integer> mAllContactsCount;

    //. . .
    public ContactViewModel(Application application) {
        super(application);
        mRepository = new ContactRepository(application);
        mAllContacts = mRepository.getAllContacts();
        mAllCategories = mRepository.getAllCategories();
        mAllContactsCount = mRepository.getAllContactsCount();
    }

    //. . .
    public LiveData<List<Contact>> getAllContactsByCategory(int categoryId) {
        return mRepository.getContactsByCategory(categoryId);
    }

    public LiveData<Integer> getAllContactsCount(){
        return mRepository.getAllContactsCount();
    }

    public LiveData<List<Category>> getAllCategories(){
        return mRepository.getAllCategories();
    }

}