package com.example.lance_3770.db;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.List;

/**
 * Created by lance-3770 on 7/3/2015.
 */
public class OtherPersonServiceTest extends AndroidTestCase {


        private static final String TAG = "OtherPersonServiceTest";

    public OtherPersonServiceTest() {

    }

    public void testDb() throws Exception{

            DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
            dbOpenHelper.getReadableDatabase();


        }

        public void testSave() throws Exception{

            OtherPersonService service = new OtherPersonService(this.getContext());
            Person person= new Person("lancefang1","3475022988",100);

            service.save(person);


        }
        public void testDelete() throws Exception{
            OtherPersonService service = new OtherPersonService(this.getContext());
            service.delete(3);



        }
/*
        public void testUpdate() throws Exception{
            OtherPersonService service = new OtherPersonService(this.getContext());
            Person person = service.find(1);
            person.setName("zhangxiaoxiao");
            //Log.i(TAG, person.toString());
            service.update(person);

        }

*/
        public void testUpdate() throws Exception{
            OtherPersonService service = new OtherPersonService(this.getContext());
            Person person = service.find(2);
            person.setName("evaxiaoqi");
            service.update(person);
            Log.i(TAG, person.toString());
        }


        public void testFind() throws Exception{
            OtherPersonService service = new OtherPersonService(this.getContext());
            Person person = service.find(2);
            Log.i(TAG, person.toString());



        }
        public void testScrollData() throws Exception{
            OtherPersonService service = new OtherPersonService(this.getContext());
            List<Person> persons = service.getScrollData(0,5);

            for(Person person : persons){
                Log.i(TAG,person.toString());

            }




        }
        public void testCount() throws Exception{

            PersonService service = new PersonService(this.getContext());
            long result = service.getCount();
            Log.i(TAG, result+"");




        }
    }








