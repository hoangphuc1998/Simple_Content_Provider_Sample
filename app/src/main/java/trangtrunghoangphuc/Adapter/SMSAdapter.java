package trangtrunghoangphuc.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import trangtrunghoangphuc.model.SMS;
import trangtrunghoangphuc.readcontacts.R;

/**
 * Created by Administrator on 4/22/2017.
 */

public class SMSAdapter extends ArrayAdapter<SMS> {
    Activity context; int resource; List<SMS> objects;
    public SMSAdapter(Activity context, int resource, List<SMS> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(resource,null);

        TextView txtSender= (TextView) row.findViewById(R.id.txtSender);
        TextView txtDate= (TextView) row.findViewById(R.id.txtDate);
        TextView txtBody= (TextView) row.findViewById(R.id.txtBody);

        SMS sms=objects.get(position);
        txtSender.setText(sms.getSender());
        txtDate.setText(sms.getDate());
        txtBody.setText(sms.getBody());
        return row;
    }
}
