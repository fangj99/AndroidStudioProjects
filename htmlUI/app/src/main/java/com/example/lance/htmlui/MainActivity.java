package com.example.lance.htmlui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends Activity {



    private WebView webView;

    private ContactService contactService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)this.findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/index.html");

        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new JSObject(), "contact");

        contactService = new ContactService();

    }


    private final class JSObject{

        public void call(String phone){
           Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
            startActivity(intent);

        }





        public void showcontacts() throws JSONException {

            try {
                List<Contact> contacts = contactService.getContacts();
                JSONArray jsonArray = new JSONArray();


                for(Contact c : contacts){

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", c.getName());
                    jsonObject.put("amount", c.getAmount());
                    jsonObject.put("phone", c.getPhone());

                    jsonArray.put(jsonObject);




                }

                String json = jsonArray.toString();
                webView.loadUrl("javascript:show('"+ json + "')");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



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
