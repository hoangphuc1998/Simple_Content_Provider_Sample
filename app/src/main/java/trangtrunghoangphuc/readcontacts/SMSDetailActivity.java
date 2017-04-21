package trangtrunghoangphuc.readcontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class SMSDetailActivity extends AppCompatActivity {
    TextView txtSenderDetail,txtDateDetail,txtBodyDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsdetail);
        txtSenderDetail= (TextView) findViewById(R.id.txtSenderDetail);
        txtBodyDetail= (TextView) findViewById(R.id.txtBodyDetail);
        txtDateDetail= (TextView) findViewById(R.id.txtDateDetail);
        Intent intent=getIntent();
        txtSenderDetail.setText(intent.getStringExtra("SENDER"));
        txtDateDetail.setText(intent.getStringExtra("DATE"));
        txtBodyDetail.setText(intent.getStringExtra("BODY"));
        txtBodyDetail.setMovementMethod(new ScrollingMovementMethod());
    }
}
