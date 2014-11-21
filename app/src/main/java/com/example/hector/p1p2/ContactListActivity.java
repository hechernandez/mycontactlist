package com.example.hector.p1p2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

public class ContactListActivity extends ActionBarActivity {
    private static final int REQUEST_CODE = 1234;
    private static final String contacts_file = "contactList.txt";
    private static SortedArrayList<Contact> readContactList = new SortedArrayList<Contact>();
    private ArrayAdapter<String> adapter;
    private ListView list;
    private ArrayList<String> localArrayList;

    private void startVoiceRecognitionActivity()
    {
        Intent localIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        localIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        localIntent.putExtra("android.speech.extra.PROMPT", "Voice recognition Demo...");
        startActivityForResult(localIntent, 1234);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        if ((paramInt1 == 1234) && (paramInt2 == -1))
        {
            localArrayList = paramIntent.getStringArrayListExtra("android.speech.extra.RESULTS");
            Log.d("Search result", localArrayList.get(0));
            Log.d("List element", (String)this.list.getItemAtPosition(0));
        }
        for (int i = 0;; i++)
        {
            if (i >= this.list.getCount())
            {
                super.onActivityResult(paramInt1, paramInt2, paramIntent);
                return;
            }
            if ((localArrayList.get(0)).equalsIgnoreCase((String)this.list.getItemAtPosition(i)))
            {
                Intent localIntent = new Intent(getApplicationContext(), EditContactActivity.class);
                localIntent.putExtra("Position", i);
                startActivity(localIntent);
            }
        }
    }

    public void speakButtonClicked(View paramView)
    {
        startVoiceRecognitionActivity();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        try
        {
            FileInputStream localFileInputStream = openFileInput("contactList.txt");
            readContactList = ContactsManager.readContacts(localFileInputStream, this);
            ContactsManager.SetContactList(readContactList);
            localFileInputStream.close();
            this.list = ((ListView)findViewById(R.id.list));
            Random localRandom = new Random();
            Paint localPaint = new Paint();
            localPaint.setARGB(localRandom.nextInt(255), localRandom.nextInt(255), localRandom.nextInt(255), localRandom.nextInt(255));
            this.list.setBackgroundColor(localPaint.getColor());
            localArrayList = new ArrayList<String>();
            int i = 0;

            if (i > -1 + readContactList.size())
            {
                this.adapter = new ArrayAdapter<String>(this, R.layout.activity_contact_list, localArrayList);
                this.list.setAdapter(this.adapter);
                this.list.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
                    {
                        Intent localIntent = new Intent(ContactListActivity.this.getApplicationContext(), EditContactActivity.class);
                        localIntent.putExtra("Position", paramAnonymousInt);
                        ContactListActivity.this.startActivity(localIntent);
                    }
                });

                // TODO This is the speak button functionality
                ((Button)findViewById(R.id.speakButton)).setOnClickListener(new OnClickListener()
                {
                    public void onClick(View paramAnonymousView)
                    {
                        speakButtonClicked(paramAnonymousView);
                    }
                });

                // TODO This is the add button functionality

                ((Button)findViewById(R.id.addButton)).setOnClickListener(new OnClickListener()
                {
                    public void onClick(View paramAnonymousView)
                    {
                        Intent localIntent = new Intent(ContactListActivity.this.getApplicationContext(), NewContactActivity.class);
                        ContactListActivity.this.startActivity(localIntent);
                    }
                });

            }
        }
        catch (Exception localException1)
        {
                localException1.printStackTrace();
                try
                {
                    ContactsManager.writeContacts(ContactsManager.getContacts(), this);
                }
                catch (Exception localException2)
                {
                    localException2.printStackTrace();
                }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
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


//                ((Button)findViewById(2131296334)).setOnClickListener(new View.OnClickListener()
//                {
//                    public void onClick(View paramAnonymousView)
//                    {
//                        Intent localIntent = new Intent(ContactListActivity.this.getApplicationContext(), ContactListActivity.class);
//                        for (int i = 0;; i++)
//                        {
//                            if (i >= ContactsManager.getContactList().size())
//                            {
//                                ContactsManager.deleteContactsFile(ContactListActivity.this);
//                                ContactListActivity.this.startActivity(localIntent);
//                                return;
//                            }
//                            ContactsManager.getContactList().remove(i);
//                        }
//                    }
//                });