package trangtrunghoangphuc.readcontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAllContacts(View view) {
        Intent intent=new Intent(this,ContactActivity.class);
        startActivity(intent);
    }

    public void showAllSMS(View view) {
        Intent intent=new Intent(this,SMSActivity.class);
        startActivity(intent);
    }
}
