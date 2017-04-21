package trangtrunghoangphuc.readcontacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import trangtrunghoangphuc.Adapter.ContactAdapter;
import trangtrunghoangphuc.model.Contact;


public class ContactActivity extends AppCompatActivity {
    ListView lvContact;
    ArrayList<Contact>listContact;
    ContactAdapter contactAdapter;
    public static int REQUEST_CONTACT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        addControls();
        addEvents();
    }

    private void addEvents() {
        showAllContactsFromDevice();
    }

    private void showAllContactsFromDevice() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED) {
                processShowContacts();
            }
            else
            {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS))
                {
                    Toast.makeText(this,"Read Contacts permissions help the app read phone numbers from Contact",Toast.LENGTH_LONG).show();

                }
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CONTACT);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CONTACT)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                processShowContacts();
            } else
            {
                Toast.makeText(this,"Permission was not granted",Toast.LENGTH_LONG).show();
            }
        }else
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void processShowContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        listContact.clear();
        while (cursor.moveToNext()) {
            String nameColName = ContactsContract.Contacts.DISPLAY_NAME;
            int colNameIndex = cursor.getColumnIndex(nameColName);
            String name = cursor.getString(colNameIndex);

            String nameColPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int colPhoneIndex = cursor.getColumnIndex(nameColPhone);
            String phone = cursor.getString(colPhoneIndex);

            Contact contact = new Contact(name, phone);
            listContact.add(contact);
        }
        cursor.close();
        contactAdapter.notifyDataSetChanged();
    }

    private void addControls() {
        lvContact= (ListView) findViewById(R.id.lvContact);
        listContact=new ArrayList<>();
        contactAdapter=new ContactAdapter(this,R.layout.item,listContact);
        lvContact.setAdapter(contactAdapter);
    }


}
