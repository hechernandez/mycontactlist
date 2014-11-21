package com.example.hector.p1p2;

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

public class NewContactActivity extends ActionBarActivity {
    private static ContactStore store;
    EditText cellP;
    EditText eMail;
    EditText firstN;
    EditText lastN;
    EditText workP;

    public void contactAdded(View paramView)
    {
        this.firstN = ((EditText)findViewById(R.id.firstName));
        this.lastN = ((EditText)findViewById(R.id.lastName));
        this.cellP = ((EditText)findViewById(R.id.cellPhone));
        this.workP = ((EditText)findViewById(R.id.workPhone));
        this.eMail = ((EditText)findViewById(R.id.email));
        ContactsManager.addContact(new Contact(100000000, this.firstN.getText().toString(), this.lastN.getText().toString(), this.cellP.getText().toString(), this.workP.getText().toString(), this.eMail.getText().toString(), null));
        store.clear(this);
        store.writeContacts(ContactsManager.getContacts(), this);
        startActivity(new Intent(this, ContactListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        store = new ContactStore();
        ((Button)findViewById(R.id.add)).setOnClickListener(new OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Toast.makeText(NewContactActivity.this.getApplicationContext(), "Contact Created", Toast.LENGTH_SHORT).show();
                NewContactActivity.this.contactAdded(paramAnonymousView);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_contact, menu);
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