package com.example.lance_3770.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lance-3770 on 7/3/2015.
 */
public class PersonAdapter extends BaseAdapter {

    private List<Person> persons;
    private int resource;
    private LayoutInflater inflater; //xml->view object

    public PersonAdapter(Context context, List<Person> persons, int resource) {
        this.persons = persons;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nameView = null;
        TextView phoneView = null;
        TextView amountView = null;

        if(convertView == null){ //first page
            convertView = inflater.inflate(resource,null);
            nameView = (TextView) convertView.findViewById(R.id.name);
            phoneView = (TextView)  convertView.findViewById(R.id.phone);
            amountView = (TextView)  convertView.findViewById(R.id.amount);

            ViewCache cache = new ViewCache();
            cache.nameView = nameView;
            cache.phoneView = phoneView;
            cache.amountView = amountView;
            convertView.setTag(cache);

        }else{
            ViewCache cache = (ViewCache) convertView.getTag();
            nameView = cache.nameView;
            phoneView = cache.phoneView;
            amountView =  cache.amountView;


        }

        Person person = persons.get(position);



        nameView.setText(person.getName());
        phoneView.setText(person.getPhone());
        amountView.setText(person.getAmount().toString());


        return convertView;
    }


    private final class ViewCache{

        //get set method cost memory and file size
        public TextView nameView;
        public TextView phoneView;
        public TextView amountView;



    }

}
