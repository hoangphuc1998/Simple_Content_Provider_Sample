package trangtrunghoangphuc.readcontacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import trangtrunghoangphuc.Adapter.SMSAdapter;
import trangtrunghoangphuc.model.SMS;

public class SMSActivity extends AppCompatActivity {
    ListView lvSMS;
    ArrayList<SMS>listSMS;
    SMSAdapter smsAdapter;
    public static int REQUEST_READ_SMS=2;
    public static String URI_READ_INBOX="content://sms/inbox";
    public static String SENDER_COLUMN_NAME="address";
    public static String DATE_COLUMN_NAME="date";
    public static String BODY_COLUMN_NAME="body";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        addControls();
        addEvents();
    }

    private void addEvents() {
        askPermissionsAndShowSMS();
        lvSMS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SMSActivity.this,SMSDetailActivity.class);
                intent.putExtra("SENDER",listSMS.get(position).getSender());
                intent.putExtra("DATE",listSMS.get(position).getDate());
                intent.putExtra("BODY",listSMS.get(position).getBody());
                startActivity(intent);
                return false;
            }
        });
    }

    private void askPermissionsAndShowSMS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_SMS)== PackageManager.PERMISSION_GRANTED)
            {
                processShowSMS();
            }
            else
                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS))
                    Toast.makeText(this,"This permission helps the app to read your sms",Toast.LENGTH_LONG).show();
                requestPermissions(new String[]{Manifest.permission.READ_SMS},REQUEST_READ_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_READ_SMS)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                processShowSMS();
            else
                Toast.makeText(this,"Permission was not grandted",Toast.LENGTH_LONG).show();
        }
        else
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void processShowSMS() {
        Uri uri=Uri.parse(URI_READ_INBOX);
        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
        listSMS.clear();
        while (cursor.moveToNext())
        {
            int senderColumnIndex=cursor.getColumnIndex(SENDER_COLUMN_NAME);
            int dateColumnIndex=cursor.getColumnIndex(DATE_COLUMN_NAME);
            int bodyColumnIndex=cursor.getColumnIndex(BODY_COLUMN_NAME);

            String sender=cursor.getString(senderColumnIndex);
            long dateInMiliseconds=Long.parseLong(cursor.getString(dateColumnIndex));
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(dateInMiliseconds);
            String date= sdf.format(calendar.getTime());
            String body=cursor.getString(bodyColumnIndex);

            SMS sms=new SMS(sender,date,body);
            listSMS.add(sms);
        }
        cursor.close();
        smsAdapter.notifyDataSetChanged();
    }

    private void addControls() {
        lvSMS= (ListView) findViewById(R.id.lvSMS);
        listSMS=new ArrayList<>();
        smsAdapter=new SMSAdapter(this,R.layout.item_sms,listSMS);
        lvSMS.setAdapter(smsAdapter);
    }
}
