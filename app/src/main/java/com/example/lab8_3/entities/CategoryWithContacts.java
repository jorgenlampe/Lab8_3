package com.example.lab8_3.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithContacts {
    @Embedded
    public Category category;
    @Relation(
            parentColumn = "id",
            entityColumn = "category_id"
    )
    public List<Contact> contactList;
}