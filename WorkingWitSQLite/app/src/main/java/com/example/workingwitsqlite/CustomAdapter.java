package com.example.workingwitsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private Context context ;
    private int resource ;
    private ArrayList<PhoneBook> arr;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PhoneBook> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arr = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder view;
        if(convertView == null){
            view = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.show_phone,parent,false);
            view.imageView = convertView.findViewById(R.id.img_avatar);
            view.tv_phonenumber = convertView.findViewById(R.id.tv_phone);
            view.tv_username = convertView.findViewById(R.id.tv_name);
            convertView.setTag(view);
        }else{
            view = (viewHolder) convertView.getTag();
        }
        PhoneBook phone =arr.get(position);
        view.imageView.setImageResource(R.drawable.profile);
        view.tv_username.setText(phone.getUserNamephone());
        view.tv_phonenumber.setText(phone.getPhoneNumber());
        int id = phone.getID();
        return convertView;
    }
    public class viewHolder{
        private ImageView imageView;
        private TextView tv_username ;
        private TextView tv_phonenumber;
    }
}
