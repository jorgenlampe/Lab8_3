package com.example.lab8_3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8_3.R;
import com.example.lab8_3.entities.Contact;


import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactForNavnView;
        private final TextView contactEtterNavnView;

        private ContactViewHolder(View itemView) {
            super(itemView);
            contactForNavnView = itemView.findViewById(R.id.contactEtterNavn);
            contactEtterNavnView = itemView.findViewById(R.id.contactForNavn);
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> mContacts; // Cached copy of words

    public ContactListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);  //opprette RecyclerView ITEM
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        System.out.println(mContacts.size());
        if (mContacts != null) {
            Contact current = mContacts.get(position);
            holder.contactForNavnView.setText(current.getForNavn());
            holder.contactEtterNavnView.setText(current.getEtterNavn());
        } else {
            // Covers the case of data not being ready yet.
            holder.contactForNavnView.setText("No name");
            holder.contactEtterNavnView.setText("No name");
        }
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
