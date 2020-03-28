package com.example.lab8_3.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab8_3.ContactViewModel;
import com.example.lab8_3.R;
import com.example.lab8_3.entities.Contact;
import com.example.lab8_3.room.ContactDAO;

public class ContactActivity extends AppCompatActivity {

    ContactViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        this.setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        model = new ViewModelProvider(this).get(ContactViewModel.class);
        Intent intent = getIntent();

        String category = intent.getStringExtra("category");
        Long categoryId = intent.getLongExtra("categoryId", 0);

        TextView tvCategory = findViewById(R.id.tvCategory);
        EditText etForNavn = findViewById(R.id.etForNavn);
        EditText etEtterNavn = findViewById(R.id.etEtterNavn);
        EditText etEmail = findViewById(R.id.etEmail);

        tvCategory.setText("Valgt kategori: " + category);



        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact newContact = new Contact(etEtterNavn.getText().toString(), etForNavn.getText().toString(), etEmail.getText().toString(), "", 0, categoryId);
                model.insertContact(newContact);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });


    }





























}
