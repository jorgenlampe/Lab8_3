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

import com.example.lab8_3.ContactViewModel;
import com.example.lab8_3.R;
import com.example.lab8_3.adapters.ContactListAdapter;
import com.example.lab8_3.entities.Category;
import com.example.lab8_3.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ContactViewModel model;
    String categoryFromSpinner;
    ArrayList<String> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ContactListAdapter adapter = new ContactListAdapter(this);

        model = new ViewModelProvider(this).get(ContactViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //selectedCategory......

        //Spinnner med kategorier..... = selectedCategory

        Spinner spinner = findViewById(R.id.spinner);

        //LiveData<List<Category>> options = model.getAllCategories();
        
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);


        /*model.getAllContactsByCategory(selectedCategory.getId()).observe(MainActivity.this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts) {
                adapter.setContacts(contacts);}});
*/

        model.getAllContactsCount().observe(MainActivity.this, new Observer<Integer>() {
            @Override
        public void onChanged(
                @Nullable final Integer count) {
                myToolbar.setTitle("Kontakter (" + String.valueOf(count) + ")");}});



        model.getAllCategories().observe(MainActivity.this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
        for (Category c : categories)
            options.add(c.getDescription());
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        categoryFromSpinner  = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
