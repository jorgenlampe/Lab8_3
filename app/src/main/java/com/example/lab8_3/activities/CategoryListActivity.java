package com.example.lab8_3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.lab8_3.ContactViewModel;
import com.example.lab8_3.R;
import com.example.lab8_3.adapters.CategoryListAdapter;
import com.example.lab8_3.entities.Category;
import com.example.lab8_3.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CategoryListActivity extends AppCompatActivity {
    private ContactViewModel model;
    private RecyclerView recyclerView;
    private CategoryListAdapter mAdapter;
    private List<Category> categoriess;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        mAdapter = new CategoryListAdapter(categoriess, this);

        recyclerView = findViewById(R.id.recycler_category_view);
        recyclerView.setHasFixedSize(true); //bedre ytelse med fast størrelse på layout

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        model = new ViewModelProvider(this).get(ContactViewModel.class);

        model.getAllCategories().observe(CategoryListActivity.this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                mAdapter.setCategories(categories);
                mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);
            }
        });

        FloatingActionButton btnAddCategory = findViewById(R.id.add_category);
        FloatingActionButton btnDeleteCategory = findViewById(R.id.delete_category);
        FloatingActionButton btnEditCategory = findViewById(R.id.edit_category);

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                    startActivity(intent);
            }
        });

        btnDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("SELECTED COATEGORY:::::::::::::::" );

                Category selectedContact = mAdapter.getSelected();
                System.out.println(selectedContact.getDescription());
                model.deleteCategory(selectedContact);
                mAdapter.notifyDataSetChanged();
            }
        });
        btnEditCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category selectedCategory = mAdapter.getSelected();
                Intent intent = new Intent(CategoryListActivity.this, EditCategory.class);
                intent.putExtra("category", selectedCategory);
                startActivity(intent);
            }
        });
    }
}
