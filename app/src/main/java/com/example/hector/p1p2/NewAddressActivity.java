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

public class NewAddressActivity extends ActionBarActivity {

    private static SortedArrayList<Contact> contact_List = new SortedArrayList<Contact>();
    private static final String contacts_file = "contactList.txt";
    private EditText city;
    private EditText name;
    private EditText number;
    private EditText state;
    private EditText street;
    private EditText zipCode;


    private void addressAdded(int paramInt)
    {
        this.name = ((EditText)findViewById(R.id.addressType));
        this.street = ((EditText)findViewById(R.id.street));
        this.number = ((EditText)findViewById(R.id.number));
        this.city = ((EditText)findViewById(R.id.city));
        this.state = ((EditText)findViewById(R.id.state));
        this.zipCode = ((EditText)findViewById(R.id.zipCode));
        String[] newAddress = new String[6];
        newAddress[0] = this.name.getText().toString();
                newAddress[1] = this.street.getText().toString();
                        newAddress[2] = this.number.getText().toString();
                                newAddress[3] = this.city.getText().toString();
                                        newAddress[4] = this.state.getText().toString();
                                                newAddress[5] = this.zipCode.getText().toString();
        ContactsManager.getContact(paramInt).addAddress(newAddress);
        ContactsManager.writeContacts(ContactsManager.getContacts(), this);
        Intent localIntent = new Intent(this, EditContactActivity.class);
        localIntent.putExtra("Position", paramInt);
        Toast.makeText(getApplicationContext(), "Address Created", Toast.LENGTH_SHORT).show();
        startActivity(localIntent);
    }

    private void addressDelete(View paramView, int paramInt) {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

                final int i = getIntent().getExtras().getInt("Position");

                ((Button)findViewById(R.id.save_address)).setOnClickListener(new OnClickListener()
                {
                    public void onClick(View paramAnonymousView)
                    {
                        NewAddressActivity.this.addressAdded(i);
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_address, menu);
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

