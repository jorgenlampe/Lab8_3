package com.example.lab8_3.room;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.lab8_3.entities.Category;
import com.example.lab8_3.entities.Contact;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = {Contact.class, Category.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    private final static String ALL_CONTACTS_STRING = "Alle kategorier";
    //. . .


    public abstract ContactDAO contactDAO();
    public abstract CategoryDAO categoryDAO();
    // Volatile betyr at flere tråder kan referere INSTANCE på en sikker måte:
    private static volatile ContactRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4; //?
    // I stedet for AsyncTask:
    // We've created an ExecutorService with a fixed thread pool
    // that you will use to run database operations asynchronously on a background thread.
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static ContactRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                final ContactDAO contactDAO = INSTANCE.contactDAO();
                final CategoryDAO categoryDAO= INSTANCE.categoryDAO();
                if (categoryDAO.getAllCategoriesCount()<=0) {
                    categoryDAO.deleteAllCategories();
                    Category category0 = new Category(ALL_CONTACTS_STRING);
                    categoryDAO.insert(category0);
                    Category category1 = new Category("Venner");
                    categoryDAO.insert(category1);
                    Category category2 = new Category("Kollegaer");
                    categoryDAO.insert(category2);
                    Category category3 = new Category("Familie");
                    categoryDAO.insert(category3);

                }
                Contact contact = new Contact("Hårstad", "Peer", "http...", "per@mail.nå", 12, 4);
                contactDAO.insert(contact);

//test
            });
        }
    };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ContactDAO contactDAO;
        private final CategoryDAO categoryDAO;
        PopulateDbAsync(ContactRoomDatabase db) {
            contactDAO = db.contactDAO();
            categoryDAO= db.categoryDAO();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            // Legger til noen testkategorier:
            if (categoryDAO.getAllCategoriesCount()<=0) {
                categoryDAO.deleteAllCategories();
                Category category0 = new Category(ALL_CONTACTS_STRING);
                categoryDAO.insert(category0);
                Category category1 = new Category("Venner");
                categoryDAO.insert(category1);
                Category category2 = new Category("Kollegaer");
                categoryDAO.insert(category2);
                Category category3 = new Category("Familie");
                categoryDAO.insert(category3);
            }
            Contact contact = new Contact("Hårstad", "Peer", "http...", "per@mail.nå", 12, 4);
            contactDAO.insert(contact);
            return null;
        }
    }
}