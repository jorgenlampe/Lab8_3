package com.example.lab8_3.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8_3.ContactViewModel;
import com.example.lab8_3.R;
import com.example.lab8_3.entities.Contact;


import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private Contact selectedContact;

    public Contact getSelected() {

        return selectedContact;
    }


    public void setSelected(Contact contact){

        selectedContact = (contact);
    }

        public class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactView;


        public ContactViewHolder(View itemView) {
            super(itemView);
            contactView = itemView.findViewById(R.id.contact);
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> mContacts;

    public ContactListAdapter(Context context) {

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactListAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ContactListAdapter.ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        System.out.println(mContacts.size());
        Resources res = holder.itemView.getResources();
        if (mContacts != null) {
            Contact current = mContacts.get(position);
            String contactStringBuilder = current.getForNavn() + " " + current.getEtterNavn() + " (" + current.getEmail() + ")";
            holder.contactView.setText(contactStringBuilder);
        } else {
            holder.contactView.setText("No name");
        }



        holder.contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.contactView.setBackgroundColor(Color.LTGRAY);
                Contact contact = mContacts.get(holder.getAdapterPosition());
                setSelected(contact);
            }
        });
    }

    public void setContacts(List<Contact> contacts){
        mContacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mContacts != null)
            return mContacts.size();
        else return 0;
    }


}
