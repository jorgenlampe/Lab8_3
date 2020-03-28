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

    List<Contact> selectedContacts = new ArrayList<>();

    public List<Contact> getSelected() {

        return selectedContacts;
    }


    public void setSelected(Contact contact){

        selectedContacts.add(contact);
    }

        public class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactForNavnView;
        private final TextView contactEtterNavnView;


        public ContactViewHolder(View itemView) {
            super(itemView);
            contactForNavnView = itemView.findViewById(R.id.contactEtterNavn);
            contactEtterNavnView = itemView.findViewById(R.id.contactForNavn);
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
            Log.d("yyy", String.valueOf(mContacts.get(position)));
            Contact current = mContacts.get(position);
            holder.contactForNavnView.setBackgroundColor(res.getColor(android.R.color.holo_orange_dark));
            holder.contactEtterNavnView.setBackgroundColor(res.getColor(android.R.color.holo_orange_light));
            holder.contactForNavnView.setText(current.getForNavn());
            holder.contactEtterNavnView.setText(current.getEtterNavn());
        } else {
            holder.contactForNavnView.setBackgroundColor(res.getColor(android.R.color.holo_orange_dark));
            holder.contactEtterNavnView.setBackgroundColor(res.getColor(android.R.color.holo_orange_light));
            holder.contactForNavnView.setText("No name");
            holder.contactEtterNavnView.setText("No name");
        }



        holder.contactForNavnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.contactForNavnView.setBackgroundColor(Color.LTGRAY);
                holder.contactEtterNavnView.setBackgroundColor(Color.LTGRAY);
                Contact contact = mContacts.get(holder.getAdapterPosition());
                setSelected(contact);

            }
        });

        holder.contactEtterNavnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.contactForNavnView.setBackgroundColor(Color.LTGRAY);
                holder.contactEtterNavnView.setBackgroundColor(Color.LTGRAY);
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
