package com.example.lance_3770.xml;


import android.test.AndroidTestCase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance-3770 on 7/2/2015.
 */
public class PersonServiceTest extends AndroidTestCase {


    private static final String TAG = "PersonServiceTest";

    public void testPersons() throws Exception{
        InputStream xml= this.getClass().getClassLoader().getResourceAsStream("person.xml");
        List<Person> persons = PersonService.getPersons(xml);
        for (Person person:persons){
            Log.i(TAG, person.toString() );

        }
    }

    public void testSave() throws Exception{

        List<Person> persons= new ArrayList<Person>();
        persons.add(new Person(43,"aa",80));
        persons.add(new Person(42,"aab",20));
        persons.add(new Person(41,"aasd",30));

        File xmlFile = new File(getContext().getFilesDir(),"lance.xml");

        FileOutputStream outStream =  new FileOutputStream(xmlFile);

        PersonService.save(persons, outStream);

     }
}
