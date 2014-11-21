package com.example.hector.p1p2;

import android.content.Context;

import java.io.InputStream;
import java.util.ArrayList;

public class ContactsManager
{
  private static ArrayList<String[]> addressList = new ArrayList<String[]>();
  private static SortedArrayList<Contact> contactList = new SortedArrayList();;
  private static ContactStore contactStore = new ContactStore();
  private static int numberOfAddresses = 0;
  
  public static void SetContactList(SortedArrayList<Contact> paramSortedArrayList)
  {
    contactList = paramSortedArrayList;
  }
  
  public static void addAddress(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
      String[] newAddress = new String[6];
        contactList.get(paramInt).addAddress(newAddress);
  }
  
  public static void addContact(Contact paramContact)
  {
    contactList.add(paramContact);
  }
  
  public static void addAddressesToContact(int paramInt)
  {
    contactList.get(paramInt).setAddressList(addressList);
    setNumberOfAddresses(1 + getNumberOfAddresses());
  }
  
  public static void deleteContact(int paramInt)
  {
    contactList.remove(paramInt);
  }
  
  public static void deleteContactsFile(Context paramContext)
  {
    contactStore.clear(paramContext);
  }
  
  public static void editContact(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, ArrayList<String[]> paramArrayList)
  {
    contactList.get(paramInt).setFirstName(paramString1);
    contactList.get(paramInt).setLastName(paramString2);
    contactList.get(paramInt).setCellPhone(paramString3);
    contactList.get(paramInt).setWorkPhone(paramString4);
    contactList.get(paramInt).setEmail(paramString5);
    contactList.get(paramInt).setAddressList(paramArrayList);
  }
  
  public static Contact getContact(int paramInt)
  {
    return (Contact)contactList.get(paramInt);
  }
  
  public static String[] getContactAddress(int paramInt)
  {
    return addressList.get(paramInt);
  }
  
  public static SortedList<Contact> getContactList()
  {
    return contactList;
  }
  
  public static SortedArrayList<Contact> getContacts()
  {
    return contactList;
  }
  
  public static int getNumberOfAddresses()
  {
    return numberOfAddresses;
  }
  
  public static SortedArrayList<Contact> readContacts(InputStream paramInputStream, Context paramContext)
  {
    contactList = new ContactStore().contactReader(paramInputStream, paramContext);
    return contactList;
  }
  
  public static void replaceContact(int paramInt, Contact paramContact)
  {
    contactList.remove(paramInt);
    contactList.add(paramContact);
  }
  
  public static void setNumberOfAddresses(int paramInt)
  {
    numberOfAddresses = paramInt;
  }
  
  public static void writeContacts(SortedArrayList<Contact> paramSortedArrayList, Context paramContext)
  {
    contactStore.writeContacts(paramSortedArrayList, paramContext);
  }
}




/* Location:           C:\TUTORIAL\app\classes-dex2jar.jar

 * Qualified Name:     mycontacts2.icom4035.edu.uprm.ece.mycontacts2.ContactsManager

 * JD-Core Version:    0.7.1

 */