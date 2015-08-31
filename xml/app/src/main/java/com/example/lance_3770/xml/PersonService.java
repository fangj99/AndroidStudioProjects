package com.example.lance_3770.xml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance-3770 on 7/1/2015.
 */
public class PersonService {

    public static List<Person> getPersons(InputStream xml) throws Exception{

        List <Person> persons = null;
        Person person=null;

        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(xml, "UTF-8");
        int event = pullParser.getEventType();

        while(event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    persons = new ArrayList<Person>();
                    break;

                case XmlPullParser.START_TAG:
                    if ("person".equals(pullParser.getName())){
                        int id = new Integer( pullParser.getAttributeValue(0));
                        person = new Person();
                        person.setId(id);
                    }
                    if ("name".equals(pullParser.getName())){
                        String name =pullParser.nextText();
                        person.setName(name);
                    }

                    if ("age".equals(pullParser.getName())){
                        int age = new Integer (pullParser.nextText());
                        person.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("person".equals(pullParser.getName())){
                        persons.add(person);
                        person=null;

                    }

                    break;



            }

            pullParser.next();
        }
        return persons;
    }

//save data to xml or outstream objexts lance
    public static void  save(List<Person> persons, OutputStream out) throws Exception{
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(out ,"UTF-8");
        serializer.startDocument("UTF-8", true);
        serializer.startTag(null, "persons");

        for(Person person : persons)
        {
            serializer.startTag(null,"person");
            serializer.attribute(null, "id", person.getId().toString());

            serializer.startTag(null, "name");
            serializer.text(person.getName().toString());
            serializer.endTag(null, "name");

            serializer.startTag(null,"age");
            serializer.text(person.getAge().toString());
            serializer.endTag(null,"age");


            serializer.endTag(null,"person");

        }





        serializer.endTag(null, "persons");
        serializer.endDocument();
        out.flush();
        out.close();

    }



}
