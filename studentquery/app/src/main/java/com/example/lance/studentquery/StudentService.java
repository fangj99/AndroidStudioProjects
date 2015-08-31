package com.example.lance.studentquery;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by lance on 7/12/2015.
 */
public class StudentService extends Service {

    private String[] names = {"james", "lance", "sherry"};

    private IBinder binder = new StudentBinder();

    public String query(int no){
        if(no>0 && no <4){
            return names[no-1];
        }
        return null;
    }

    //communication between server and client
    public IBinder onBind(Intent intent) {
        return binder;
    }



    private class StudentBinder extends Binder implements  IStudent{


        @Override
        public String queryStudent(int no) {
            return query(no);
        }
    }






}
