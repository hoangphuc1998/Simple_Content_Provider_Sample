package trangtrunghoangphuc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.List;

import trangtrunghoangphuc.model.Contact;
import trangtrunghoangphuc.readcontacts.R;

/**
 * Created by Administrator on 4/19/2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int resource;
    List<Contact> objects;
    public ContactAdapter(Activity context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View row=inflater.inflate(resource,null);
        TextView txtName= (TextView) row.findViewById(R.id.txtName);
        TextView txtContact= (TextView) row.findViewById(R.id.txtPhone);
        txtName.setText(objects.get(position).getName());
        txtContact.setText(objects.get(position).getPhone());
        return row;
    }
}
