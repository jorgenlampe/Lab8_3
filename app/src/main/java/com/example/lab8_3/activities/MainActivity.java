package com.example.lab8_3.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.AccountAuthenticatorActivity;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab8_3.ContactViewModel;
import com.example.lab8_3.R;
import com.example.lab8_3.adapters.ContactListAdapter;
import com.example.lab8_3.entities.Category;
import com.example.lab8_3.entities.CategoryWithContacts;
import com.example.lab8_3.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ContactViewModel model;
    private Spinner spinner;
    private List<Category> options = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private ContactListAdapter contactListAdapter;

    private ArrayList allContacts;

   // Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        spinner = findViewById(R.id.spinner);


        //tester å inserte kontakt
        //Contact testContact = new Contact("hans", "hansen", "wwww", "", 5);
        //model.insertContact(testContact);

      //  final ContactListAdapter adapter = new ContactListAdapter(this);

        model = new ViewModelProvider(this).get(ContactViewModel.class);

        allContacts = new ArrayList();
        Contact cont = new Contact("heisann", "hoppsann", "eee", "rr", 2, 2);
        allContacts.add(cont);

        contactListAdapter = new ContactListAdapter(this);
        contactListAdapter.setContacts(allContacts);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(contactListAdapter);
       /* RecyclerView recyclerView = findViewById(R.id.recyclerview);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
        //selectedCategory......

        //Spinnner med kategorier..... = selectedCategory

/*        model.getAllContactsByCategory(getSelectedCategory().getId()).observe(MainActivity.this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts) {
                    contactListAdapter.setContacts(contacts);
                //Log.d("catt", String.valueOf(contacts.size()));
            }});

*/




        model.getAllContactsCount().observe(MainActivity.this, new Observer<Integer>() {
            @Override
        public void onChanged(
                @Nullable final Integer count) {
                myToolbar.setTitle("Kontakter (" + count + ")");}});


        model.getAllCategories().observe(MainActivity.this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, categories);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                System.out.println("SIZE????" + options.size());

                spinner.setAdapter(arrayAdapter);
                spinner.setOnItemSelectedListener(MainActivity.this);
                System.out.println(options.size());
                arrayAdapter.notifyDataSetChanged();
                System.out.println(arrayAdapter.getCount());
                 }
        });


        //test for å se at kontakter kommer med - OK
        model.getAllContacts().observe(MainActivity.this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
            //    for (Contact c : contacts){
//            if (c.getCategory_id() == getSelectedCategory().getId())
              //      Log.d("zzz", String.valueOf(c.getCategory_id()));
                //    Log.d("zzz", String.valueOf(getSelectedCategory().getId()));

            allContacts.add(contacts);


/*
                contactListAdapter = new ContactListAdapter(getApplicationContext());
             //   contactListAdapter.setContacts(allContacts);
                RecyclerView recyclerView = findViewById(R.id.recyclerview);

                recyclerView.setAdapter(contactListAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
Log.d("heisann", "V");*/
            }
        });


    }

    public Category getSelectedCategory() {
        Category category = (Category) spinner.getSelectedItem();
   //     displaySelectedCategory(category);
//        Log.d("catt", String.valueOf(category.getId()));
        return category;
    }



    public void displaySelectedCategory(Category category) {
//        Toast.makeText(this,category.getDescription(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        System.out.println("TEST");
        Toast.makeText(this,"HELLOE",Toast.LENGTH_LONG).show();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
