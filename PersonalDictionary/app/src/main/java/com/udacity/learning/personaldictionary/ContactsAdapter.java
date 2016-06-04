package com.udacity.learning.personaldictionary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Subbu on 6/3/16.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {

    private static final String TAG = ContactsAdapter.class.getSimpleName();
    private final Context context;
    private final List<String> contactsList;
    private final LayoutInflater inflater;

    public ContactsAdapter(Context context, List<String> listOfContacts, ContactsFragment contactsFragment) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.contactsList = listOfContacts;
    }

    @Override
    public ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.contact_list_item, parent, false);
        return new ContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsHolder holder, int position) {
        String contactName = contactsList.get(position);
        Log.d(TAG, "onBindViewHolder: contactName: " + contactName);
        holder.contactNameView.setText(contactName);
    }

    @Override
    public int getItemCount() {
        return this.contactsList.size();
    }

    public class ContactsHolder extends RecyclerView.ViewHolder {
        TextView contactNameView;

        public ContactsHolder(View itemView) {
            super(itemView);
            contactNameView = (TextView) itemView.findViewById(R.id.contactName);
        }
    }
}
