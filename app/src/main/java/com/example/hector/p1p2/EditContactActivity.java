package com.example.hector.p1p2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EditContactActivity
        extends ActionBarActivity
{
    private static final String contacts_file = "contactList.txt";
    private SortedArrayList<Contact> contactList = new SortedArrayList<Contact>();
    EditText emailField;
    EditText lastNameField;
    EditText nameField;
    EditText numberField;
    private int position = 0;
    EditText workNumberField;

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_edit_contact);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        try
        {
            FileInputStream fin = openFileInput("contactList.txt");
            this.contactList = ContactsManager.readContacts(fin, this);
            fin.close();
            Intent i = getIntent();
            Bundle b = i.getExtras();
            this.position = b.getInt("Position");
            Contact currentContact = ContactsManager.getContact(this.position);
            this.nameField = ((EditText)findViewById(R.id.firstNameEdit));
            this.nameField.setText(currentContact.getFirstName());
            this.lastNameField = ((EditText)findViewById(R.id.lastNameEdit));
            this.lastNameField.setText(currentContact.getLastName());
            this.numberField = ((EditText)findViewById(R.id.cellPhoneEdit));
            this.numberField.setText(currentContact.getCellPhone());
            this.workNumberField = ((EditText)findViewById(R.id.workPhoneEdit));
            this.workNumberField.setText(currentContact.getWorkPhone());
            this.emailField = ((EditText)findViewById(R.id.emailEdit));
            this.emailField.setText(currentContact.getEmail());
            //TODO CHECK!!!!
            ((Button)findViewById(R.id.add_addressEdit)).setOnClickListener(new OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    Intent localIntent = new Intent(EditContactActivity.this.getApplicationContext(), NewAddressActivity.class);
                    Bundle localBundle = new Bundle();
                    localBundle.putInt("Position", EditContactActivity.this.position);
                    localIntent.putExtras(localBundle);
                    EditContactActivity.this.startActivity(localIntent);
                }
            });
            ((Button)findViewById(R.id.contact_informationEdit)).setOnClickListener(new OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    Intent localIntent = new Intent(EditContactActivity.this.getApplicationContext(), ShowContactActivity.class);
                    localIntent.putExtra("Position", EditContactActivity.this.position);
                    EditContactActivity.this.startActivity(localIntent);
                }
            });
            ((Button)findViewById(R.id.contact_informationSave)).setOnClickListener(new OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    ContactsManager.editContact(EditContactActivity.this.position, EditContactActivity.this.nameField.getText().toString(), EditContactActivity.this.lastNameField.getText().toString(), EditContactActivity.this.numberField.getText().toString(), EditContactActivity.this.workNumberField.getText().toString(), EditContactActivity.this.emailField.getText().toString(), ContactsManager.getContact(EditContactActivity.this.position).getAddressList());
                    ContactsManager.writeContacts(ContactsManager.getContacts(), EditContactActivity.this);
                    Toast.makeText(EditContactActivity.this.getApplicationContext(), "Contact Edited", Toast.LENGTH_SHORT).show();
                    Intent localIntent = new Intent(paramAnonymousView.getContext(), ContactListActivity.class);
                    EditContactActivity.this.startActivity(localIntent);
                }
            });
            ((Button)findViewById(R.id.delete_contactEdit)).setOnClickListener(new OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    ContactsManager.deleteContact(EditContactActivity.this.position);
                    ContactsManager.writeContacts(ContactsManager.getContacts(), EditContactActivity.this);
                    Toast.makeText(EditContactActivity.this.getApplicationContext(), "Contact Deleted", Toast.LENGTH_SHORT).show();
                    Intent localIntent = new Intent(paramAnonymousView.getContext(), ContactListActivity.class);
                    EditContactActivity.this.startActivity(localIntent);
                }
            });
            return;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
            for (;;)
            {
                localFileNotFoundException.printStackTrace();
            }
        }
        catch (IOException localIOException)
        {
            for (;;)
            {
                localIOException.printStackTrace();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.menu_edit_contact, paramMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}