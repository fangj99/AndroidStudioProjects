package com.example.lance_3770.db;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.List;

/**
 * Created by lance-3770 on 7/3/2015.
 */
public class PersonServiceTest extends AndroidTestCase {


        private static final String TAG = "PersonServiceTest";

    public PersonServiceTest() {

    }

    public void testDb() throws Exception{

            DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
            dbOpenHelper.getReadableDatabase();


        }

        public void testSav() throws Exception{

            PersonService service = new PersonService(this.getContext());
            Person person= new Person("lancefang1","3475022988",100);

            service.save(person);


        }
        public void testDelete() throws Exception{
            PersonService service = new PersonService(this.getContext());
            service.delete(1);



        }
/*
        public void testUpdate() throws Exception{
            PersonService service = new PersonService(this.getContext());
            Person person = service.find(1);
            person.setName("zhangxiaoxiao");
            //Log.i(TAG, person.toString());
            service.update(person);

        }

*/
        public void testUpdat() throws Exception{
            PersonService service = new PersonService(this.getContext());
            Person person = service.find(2);
            person.setName("lancefang");
            service.update(person);
            Log.i(TAG, person.toString());
        }


        public void testFind() throws Exception{
            PersonService service = new PersonService(this.getContext());
            Person person = service.find(2);
            Log.i(TAG, person.toString());



        }
        public void testScrollData() throws Exception{
            PersonService service = new PersonService(this.getContext());
            List<Person> persons = service.getScrollData(0, 5);

            for(Person person : persons){
                Log.i(TAG,person.toString());

            }




        }
    public void testCount() throws Exception{

        PersonService service = new PersonService(this.getContext());
        long result = service.getCount();
        Log.i(TAG, result+"");

    }


    public void testPayment() throws Exception{

        PersonService service = new PersonService(this.getContext());
        service.payment();

    }

    public void testUpdateAmount(){

        PersonService service=new PersonService(this.getContext());
        Person person1= service.find(2);
        Person person2= service.find(4);

        person1.setAmount(100);
        person2.setAmount(50);

        service.update(person1);
        service.update(person2);

    }





}








