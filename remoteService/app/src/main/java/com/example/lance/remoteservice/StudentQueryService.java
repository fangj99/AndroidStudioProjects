package com.example.lance.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by lance on 7/12/2015.
 */
public class StudentQueryService extends Service{

    private String[] names = {"james", "lance", "sherry"};

    private IBinder iBinder = new StudentQueryBinder();

    public String query(int no){
        if(no>0 && no <4){
            return names[no-1];
        }
        return null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private final class StudentQueryBinder extends  StudentQuery.Stub {


        @Override
        public String queryStudent(int number) throws RemoteException {


            return query(number);
        }
    }






}
