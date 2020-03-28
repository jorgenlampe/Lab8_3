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
import android.content.Intent;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ContactViewModel model;
    private Spinner spinner;
    private List<Category> options = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private ContactListAdapter contactListAdapter;
    private List<Contact> selectedContacts;
    private ArrayList<Contact> allContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        spinner = findViewById(R.id.spinner);

        model = new ViewModelProvider(this).get(ContactViewModel.class);

        FloatingActionButton btnAddContact = findViewById(R.id.add_contact);
        FloatingActionButton btnDeleteContact = findViewById(R.id.delete_contact);

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSelectedCategory().getId() == 1)
                    showError();
                else{
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                intent.putExtra("category", getSelectedCategory().getDescription());
                intent.putExtra("categoryId", getSelectedCategory().getId());
                startActivity(intent);
            }
            }
        });

        btnDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedContacts = contactListAdapter.getSelected();

                for(Contact contact : selectedContacts){
                    model.deleteContact(contact);
                    contactListAdapter.setContacts(allContacts);
                    contactListAdapter.notifyDataSetChanged();


                }
            }
        });


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


        model.getAllContacts().observe(MainActivity.this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {

                allContacts = new ArrayList<>();
                for (Contact c : contacts)
                    allContacts.add(c);

            }
        });


    }

    private void showError() {
        Toast.makeText(this, "you must chose a category", Toast.LENGTH_LONG).show();
    }

    public Category getSelectedCategory() {
        Category category = (Category) spinner.getSelectedItem();
        Log.d("catz", String.valueOf(category.getId()));

        return category;
    }



    public void displaySelectedCategory(Category category) {
//        Toast.makeText(this,category.getDescription(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        System.out.println("TEST");
        Toast.makeText(this,"HELLOE",Toast.LENGTH_LONG).show();

        ArrayList<Contact> temp = new ArrayList<>();

        for (Contact c : allContacts) {
            if (getSelectedCategory().getId() == 1 || c.getCategory_id() == getSelectedCategory().getId())
                temp.add(c);
        }


        contactListAdapter = new ContactListAdapter(getApplicationContext());

        contactListAdapter.setContacts(temp);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(contactListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
