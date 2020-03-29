package com.example.lab8_3.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.EditText;
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
import android.os.Bundle;

import com.example.lab8_3.R;

public class EditContacts extends AppCompatActivity {
    ContactViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        this.setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        model = new ViewModelProvider(this).get(ContactViewModel.class);
        Intent intent = getIntent();
        Contact contact = intent.getParcelableExtra("contact");

        TextView tvCategory = findViewById(R.id.tvCategory);
        EditText etForNavn = findViewById(R.id.etForNavn);
        EditText etEtterNavn = findViewById(R.id.etEtterNavn);
        EditText etEmail = findViewById(R.id.etEmail);

        etEtterNavn.setText(contact.getEtterNavn());
        etForNavn.setText(contact.getForNavn());
        etEmail.setText(contact.getEmail());


        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(etEmail.getText());
                contact.setEmail(etEmail.getText().toString());
                contact.setForNavn(etForNavn.getText().toString());
                contact.setEtterNavn(etEtterNavn.getText().toString());
                model.updateContact(contact);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
