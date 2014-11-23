package com.example.hector.p1p2;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class ContactStore {

    private FileInputStream input;
    private BufferedReader br;
    private InputStreamReader inputStream;
    private static final String contacts_file = "contactList.txt";
    private OutputStreamWriter output;
    private BufferedWriter writer;

    private final int CONTACT_FIELDS = 5;
    private final int ADDRESS_FIELDS = 6;

    public ContactStore() {
    }

    /**
     * Obtains contacts in file.
     */
    public SortedArrayList<Contact> readContacts(FileInputStream in) {

        try {

            this.input = in; //initialize fileinputstream
            inputStream = new InputStreamReader(this.input); //initialize inputstreamreader
            br = new BufferedReader(inputStream); //initialize our reader

        } catch(Exception e) {
            e.getStackTrace();
        }

        try{

            String line = br.readLine();

            while (line != null) {
                Contact temp = new Contact();
                for(int i=0; i < CONTACT_FIELDS; i++){ //from first name to addressfields
                    if(i == 0){
                        System.out.println(line);
                        System.out.println(Long.valueOf(line.substring(2)));
                        temp.setId(Long.valueOf(line.substring(2))); // extract id number from format id#
                    }
                    if(i == 1){
                        System.out.println(line);
                        temp.setFirstName(line);
                    }
                    if(i == 2){
                        System.out.println(line);
                        temp.setLastName(line);
                    }
                    if(i == 3){
                        System.out.println(line);
                        temp.setCellPhone(line);
                    }
                    if(i == 4){
                        System.out.println(line);
                        temp.setWorkPhone(line);
                    }
                    if(i == 5){
                        System.out.println(line);
                        temp.setEmail(line);
                    }
                    line = br.readLine();
                }

                while(!line.contains("address")) {

                    String[] contactAddress = new String[6];

                    for (int i = 0; i < ADDRESS_FIELDS; i++) {

                        contactAddress[i] = line;

                        line = br.readLine();
                    }
                    temp.addAddress(contactAddress);
                    ContactsManager.addContact(temp);
                    Log.i("Testing Reader:", temp.getFirstName() + temp.getLastName() + temp.getCellPhone() + temp.getWorkPhone() + temp.getEmail());
                }
            }
            inputStream.close();
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ContactsManager.getContactsFromList();
    }

    public void writeContacts(SortedArrayList<Contact> contacts, Context context){

        try{

            output = new OutputStreamWriter(context.openFileOutput(contacts_file, Context.MODE_PRIVATE));
            writer = new BufferedWriter(output);

            writer.write("");

            for(int i=0; i < contacts.size(); i++){
                Contact temp = contacts.get(i);
                if ( temp.getId() != 0 )
                    writer.write("id" + temp.getId() + "\n\n");
                writer.write(temp.getFirstName() + "\n");
                writer.write(temp.getLastName() + "\n");
                writer.write(temp.getCellPhone() + "\n");
                writer.write(temp.getWorkPhone() + "\n");
                writer.write(temp.getEmail() + "\n");

                for (int j = 0; j < temp.getNumberOfAddresses(); ++j){
                    if (temp.getAddress(j) != null) {
                        for (int k = 0; k < ADDRESS_FIELDS; ++k)
                            writer.write(temp.getAddress(j)[k]+"\n");
                    } else {
                        writer.write("N/A" + "\n");
                        writer.write("N/A" + "\n");
                        writer.write("N/A" + "\n");
                        writer.write("N/A" + "\n");
                        writer.write("N/A" + "\n");
                        writer.write("N/A" + "\n");
              }
                }
            }

            writer.close();
            output.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void clear(Context context) {
        try {
            output = new OutputStreamWriter(context.openFileOutput(contacts_file, Context.MODE_PRIVATE));
            writer = new BufferedWriter(output);
            writer.write("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

