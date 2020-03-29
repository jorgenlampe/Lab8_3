package com.example.lab8_3.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @NonNull
    private String etterNavn;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public void setFodselsaar(String fodselsaar) {
        this.fodselsaar = fodselsaar;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    @NonNull
    private String forNavn;

    private String fbLink;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String email;
    private String telefonnr;
    private String fodselsaar;

    private long category_id;

    public long getCategory_id() {
        return category_id;
    }


    public Contact(@NonNull String etterNavn, @NonNull String forNavn, String email, String fbLink, long id, long category_id){

        this.etterNavn = etterNavn;
        this.forNavn = forNavn;
        this.fbLink = fbLink;
        this.id = id;
        this.email = email;
        this.category_id = category_id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public String getFodselsaar() {
        return fodselsaar;
    }

@Ignore
    public Contact() {
        super();
        this.etterNavn = "";
        this.forNavn = "";
        this.fbLink = "";
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getEtterNavn() {
        return etterNavn;
    }

    public void setEtterNavn(String etterNavn) {
        this.etterNavn = etterNavn;
    }

    @NonNull
    public String getForNavn() {
        return forNavn;
    }

    public void setForNavn(String forNavn) {
        this.forNavn = forNavn;
    }

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    @Override
    public String toString() {
        return id + " " + etterNavn + " " + forNavn;
    }
}

