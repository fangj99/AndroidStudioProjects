package com.example.lance.datapageload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 7/19/2015.
 */
public class DataService {

    public static List<String> getData(int offset, int maxResult){

        List<String> data = new ArrayList<String>();

        for(int i =0 ; i< maxResult; i++){
            data.add("遇到任何问题"+i);

        }

        return data;
    }
}
