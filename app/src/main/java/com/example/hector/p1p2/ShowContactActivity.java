package com.example.hector.p1p2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ShowContactActivity extends ActionBarActivity {

    private Bundle b;
    private Contact currentContact;
    private Intent i;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        this.i = getIntent();
        this.b = this.i.getExtras();
        this.position = this.b.getInt("Position");
        this.currentContact = ContactsManager.getContactFromList(this.position);
        ((TextView)findViewById(R.id.firstName_show)).setText(this.currentContact.getFirstName());
        ((TextView)findViewById(R.id.lastName_show)).setText(this.currentContact.getLastName());
        ((TextView)findViewById(R.id.cell_phone_show)).setText(this.currentContact.getCellPhone());
        ((TextView)findViewById(R.id.workPhone_show)).setText(this.currentContact.getWorkPhone());
        ((TextView)findViewById(R.id.email_show)).setText(this.currentContact.getEmail());
        if (this.currentContact.getNumberOfAddresses() > 0)
        {
            if (this.currentContact.getNumberOfAddresses() == 1)
            {
                ((TextView)findViewById(R.id.home_address)).setText(this.currentContact.getAddressList().get(0)[0]);
                ((TextView)findViewById(R.id.street)).setText(this.currentContact.getAddressList().get(0)[1]);
                ((TextView)findViewById(R.id.number)).setText(this.currentContact.getAddressList().get(0)[2]);
                ((TextView)findViewById(R.id.city)).setText(this.currentContact.getAddressList().get(0)[3]);
                ((TextView)findViewById(R.id.state)).setText(this.currentContact.getAddressList().get(0)[4]);
                ((TextView)findViewById(R.id.zip_code)).setText(this.currentContact.getAddressList().get(0)[5]);
            }
            if (this.currentContact.getNumberOfAddresses() == 2)
            {
                ((TextView)findViewById(R.id.work_address)).setText(this.currentContact.getAddressList().get(1)[0]);
                ((TextView)findViewById(R.id.street_work)).setText(this.currentContact.getAddressList().get(1)[1]);
                ((TextView)findViewById(R.id.number_work)).setText(this.currentContact.getAddressList().get(1)[2]);
                ((TextView)findViewById(R.id.city_work)).setText(this.currentContact.getAddressList().get(1)[3]);
                ((TextView)findViewById(R.id.state_work)).setText(this.currentContact.getAddressList().get(1)[4]);
                ((TextView)findViewById(R.id.zip_code_work)).setText(this.currentContact.getAddressList().get(1)[5]);
            }
        }

        findViewById(R.id.deleteButton1).setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View paramAnonymousView)
            {
                ContactsManager.deleteAddress(0, position);
                Intent localIntent = new Intent(getApplicationContext(), EditContactActivity.class);
                localIntent.putExtra("Position", position);
                startActivity(localIntent);
            }
        });
        findViewById(R.id.deleteButtonWork).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ContactsManager.deleteAddress(1, position);
                Intent localIntent = new Intent(getApplicationContext(), EditContactActivity.class);
                localIntent.putExtra("Position", position);
                startActivity(localIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_contact, menu);
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

