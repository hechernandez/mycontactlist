package com.example.hector.p1p2;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ContactStore
{
  private static final int ADDRESS_FIELDS_LIMIT = 6;
  private static final int CONTACTS_FIELDS_LIMIT = 5;
  private BufferedReader buffStreamReader;
  private InputStream inputStream;
  private InputStreamReader inputStreamReader;
  private OutputStreamWriter output;
  private BufferedWriter writer;
  
  /* Error */
  public void clear(Context paramContext)
  {
  }
  
  public SortedArrayList<Contact> contactReader(InputStream paramInputStream, Context paramContext)
  {
    for (;;)
    {
      try
      {
        this.inputStream = paramInputStream;
        this.inputStreamReader = new InputStreamReader(this.inputStream);
        this.buffStreamReader = new BufferedReader(this.inputStreamReader);
        String str = new String();
        str = this.buffStreamReader.readLine();
        if (str != null) {
          continue;
        }
        this.inputStreamReader.close();
        this.buffStreamReader.close();
      }
      catch (Exception localException)
      {
        String str;
        Contact localContact;
        int i;
        ContactAddress localContactAddress;
        int j;
        Log.e("Contact List Activity", "Error locating file: " + localException.toString());
        localException.printStackTrace();
        continue;
        switch (i)
        {
        default: 
          str = this.buffStreamReader.readLine();
          i++;
          break;
        case 0: 
          localContact.setFirstName(str);
          break;
        case 1: 
          localContact.setLastName(str);
          break;
        case 2: 
          localContact.setCellPhone(str);
          break;
        case 3: 
          localContact.setWorkPhone(str);
          break;
        case 4: 
          localContact.setEmail(str);
          continue;
          str = this.buffStreamReader.readLine();
          j++;
          continue;
          localContactAddress.setName(str);
          continue;
          localContactAddress.setStreet(str);
          continue;
          localContactAddress.setNumber(str);
          continue;
          localContactAddress.setCity(str);
          continue;
          localContactAddress.setState(str);
          continue;
          localContactAddress.setZip(str);
          continue;
          switch (j)
          {
          }
          break;
        }
      }
      return ContactsManager.getContacts();
      localContact = new Contact();
      i = 0;
      if (i < 5) {
        continue;
      }
      localContactAddress = new ContactAddress();
      j = 0;
      if (j < 6) {
        continue;
      }
      localContact.addAddress(localContactAddress);
      ContactsManager.addContact(localContact);
    }
  }
  
  public void writeContacts(SortedArrayList<Contact> paramSortedArrayList, Context paramContext)
  {
    for (;;)
    {
      try
      {
        this.output = new OutputStreamWriter(paramContext.openFileOutput("contactList.txt", 0));
        this.writer = new BufferedWriter(this.output);
        int i = 0;
        if (i >= paramSortedArrayList.size())
        {
          this.writer.close();
          this.output.close();
          return;
        }
        Contact localContact = (Contact)paramSortedArrayList.get(i);
        this.writer.write(localContact.getFirstName() + "\n");
        this.writer.write(localContact.getLastName() + "\n");
        this.writer.write(localContact.getCellPhone() + "\n");
        this.writer.write(localContact.getWorkPhone() + "\n");
        this.writer.write(localContact.getEmail() + "\n");
        if ((!((ContactAddress)localContact.getAddressList().get(0)).equals(null)) && (((ContactAddress)localContact.getAddressList().get(1)).equals(null)))
        {
          this.writer.write(((ContactAddress)localContact.getAddressList().get(0)).getName() + "\n");
          this.writer.write(((ContactAddress)localContact.getAddressList().get(0)).getStreet() + "\n");
          this.writer.write(((ContactAddress)localContact.getAddressList().get(0)).getNumber() + "\n");
          this.writer.write(((ContactAddress)localContact.getAddressList().get(0)).getCity() + "\n");
          this.writer.write(((ContactAddress)localContact.getAddressList().get(0)).getState() + "\n");
          this.writer.write(((ContactAddress)localContact.getAddressList().get(0)).getZip() + "\n");
        }
        if (localContact.getNumberOfAddresses() > 0)
        {
          int j = 0;
          if (j < localContact.getNumberOfAddresses())
          {
            ContactAddress localContactAddress = (ContactAddress)localContact.getAddressList().get(j);
            this.writer.write(localContactAddress.getName() + "\n");
            this.writer.write(localContactAddress.getStreet() + "\n");
            this.writer.write(localContactAddress.getNumber() + "\n");
            this.writer.write(localContactAddress.getCity() + "\n");
            this.writer.write(localContactAddress.getState() + "\n");
            this.writer.write(localContactAddress.getZip() + "\n");
            j++;
            continue;
          }
        }
        i++;
      }
      catch (Exception localException)
      {
        Log.e("Cannot start program.", "File not found: " + localException.toString());
        localException.printStackTrace();
        return;
      }
    }
  }
}




/* Location:           C:\TUTORIAL\app\classes-dex2jar.jar

 * Qualified Name:     mycontacts2.icom4035.edu.uprm.ece.mycontacts2.ContactStore

 * JD-Core Version:    0.7.1

 */