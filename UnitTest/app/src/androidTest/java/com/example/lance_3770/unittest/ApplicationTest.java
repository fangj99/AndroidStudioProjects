package com.example.lance_3770.unittest;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.Assert;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void testSave() throws Exception{
        PersonService service=new PersonService();
        service.save(null);
    }
    public void testAdd() throws Exception{
        PersonService service=new PersonService();
        int result = service.add(1,2);
        Assert.assertEquals(8,result);
    }
}