package com.example.lance_3770.db;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity {

    private ListView listView;
    private PersonService personService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personService= new PersonService(this);
        listView =  (ListView) this.findViewById(R.id.listview);
        listView.setOnItemClickListener(new ItemClickListener());

        show2();
    }

    private final class ItemClickListener implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ListView lView = ( ListView) parent;

            /*use with show3()
            Person person = (Person) lView.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),person.getId().toString(),Toast.LENGTH_LONG).show();
*/

            /*  use with sow2() */
            Cursor cursor = (Cursor) lView.getItemAtPosition(position);
            int personid =cursor.getInt(cursor.getColumnIndex("_id"));
            Toast.makeText(getApplicationContext(), personid+ "", Toast.LENGTH_SHORT).show();

        }
    }

//self defined adapter
    private void show3() {
        List<Person> persons = personService.getScrollData(0, 20);
        PersonAdapter adapter = new PersonAdapter(this, persons, R.layout.item);
        listView.setAdapter(adapter);


    }

    private void show2() {
        Cursor cursor =  personService.getCursorScrollData(0,20);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor,new String[]{"name", "phone", "amount"}, new int[]{R.id.name, R.id.phone, R.id.amount});


        listView.setAdapter(adapter);

    }




    private void show() {
        List<Person> persons = personService.getScrollData(0, 20);
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (Person person : persons) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("name", person.getName());
            item.put("phone", person.getPhone());
            item.put("amount", person.getAmount());
            item.put("id", person.getId());
            data.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item, new String[]{"name", "phone", "amount"}, new int[]{R.id.name, R.id.phone, R.id.amount});

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
