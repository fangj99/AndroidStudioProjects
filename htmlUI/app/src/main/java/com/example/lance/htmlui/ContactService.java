package com.example.lance.htmlui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 7/16/2015.
 */
public class ContactService {

    public List<Contact> getContacts(){

        List<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact(12,"zhangming", "1231434342", 1500));
        contacts.add(new Contact(13,"zhangming1", "123143334542", 15060));
        contacts.add(new Contact(142,"zhangming2", "123137454342", 15600));
        contacts.add(new Contact(122,"zhangming3", "1231654342", 15070));
        contacts.add(new Contact(212,"zhangming4", "12314376842", 17500));
        contacts.add(new Contact(312,"zhangming5", "1231564342", 15600));
        contacts.add(new Contact(3412,"zhangming6", "12345314342", 15080));
        contacts.add(new Contact(132,"zhangming7", "1231436542", 15800));
        return contacts;
    }
}
