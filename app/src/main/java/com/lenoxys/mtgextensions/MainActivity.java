package com.lenoxys.mtgextensions;

import android.os.Bundle;

import com.google.gson.Gson;
import com.lenoxys.mtgextensions.business.model.AllExpansion;
import com.lenoxys.mtgextensions.business.model.Data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.danlew.android.joda.JodaTimeAndroid;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private List<AllExpansion> allExpansions = null;
    Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    private void setup() {
        JodaTimeAndroid.init(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        textView = findViewById(R.id.Text_View);

        OkHttpClient client = new OkHttpClient();

        String url = "https://cdn.bigar.com/mtg/cardjson/expansions";
        String urlbaseExpansion = "https://cdn.bigar.com/mtg/cardjson/expansions/";

        Request request;
        request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    //pass the api into a string
                    String myResponse = response.body().string();
                    //print the string at console
                    Log.d("PEDRO", myResponse);

                    //parsing the api into a class
                    Data allExpansionsData = gson.fromJson(myResponse, Data.class);

                    //if class !null add all expansions into a list<AllExpansion>
                    if (allExpansionsData != null) {

                        allExpansions = allExpansionsData.getAllExpansions();
                        Log.d("PEDRO", myResponse);


                    } else {

                        Log.e("ERROR", "AllExpansionData is NULL");
                    }


                }


            }

            @Override
            public void onFailure(Call call, IOException e) {

                e.printStackTrace();

            }
        });


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


    //return the Expansion name for the new window
    public String GetExpansionNameById(String expansionId) {

        //default string for return(just for debug)
        String str = "Throne of Eldraine";

        //get string from expansion object in AllExpansions array
        for (int i = 0; i <= allExpansions.size(); i++) {

            if (allExpansions.get(i).getId() == expansionId) {

                str = allExpansions.get(i).getName();
            }
        }

        return str;
    }
}

//search where i need to put this to work with recycleList :thinking Emoji:
/*
 OkHttpClient expansionClient = new OkHttpClient();

        String urlbaseExpansion = "https://cdn.bigar.com/mtg/cardjson/expansions/";

        Request expansionRequest;
        expansionRequest = new Request.Builder().url(urlbaseExpansion+GetExpansionNameById(expansionId)).build();

        expansionClient.newCall(expansionRequest).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    //pass the api into a string
                    String myResponse = response.body().string();
                    //print the string at console
                    Log.d("PEDRO", myResponse);

                    //parsing the api into a class
                    Root expansionData = gson.fromJson(myResponse, Root.class);

                    if (expansionData != null) {

                       String expansionName = expansionData.getData().getExpansion().getName();
                       String expansionReleaseDate = expansionData.getData().getExpansion().getReleaseDate();
                       Float  expansionCardCount = expansionData.getData().getExpansion().getCardCount();

                      ArrayList<Object> expansionCards = expansionData.getData().getAllExpansionCards();

                        Log.d("EXPANSION NAME : ", expansionName);
                        Log.d("EXPANSION RELEASE DATE ", expansionReleaseDate);
                        Log.d("EXPANSION CARD COUNT", expansionCardCount);

                    } else {

                        Log.e("ERROR", "ExpansionData is NULL");
                    }
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

                e.printStackTrace();

            }
        });


       public String GetExpansionNameById(String expansionId){

            //get string from expansion object in AllExpansions array
           for(int i=0; i<=allExpansions.size();i++ ){

                if(allExpansions.get(i).getId() == expansionId){

                    return ((allExpansions.get(i).getName()) as String);
                    }
            }
        }


 */