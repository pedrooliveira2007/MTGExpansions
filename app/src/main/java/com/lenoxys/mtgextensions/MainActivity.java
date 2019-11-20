package com.lenoxys.mtgextensions;

import android.os.Bundle;

import com.google.gson.Gson;
import com.lenoxys.mtgextensions.business.model.AllExpansion;
import com.lenoxys.mtgextensions.business.model.Example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.danlew.android.joda.JodaTimeAndroid;

import java.io.IOException;
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

        Request request;
        request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {

            // review how to consume properly the API and how to put it inside the Data object.
            // (give a look at this site https://www.geeksforgeeks.org/parse-json-java/).
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    String myResponse = response.body().string();
//                    System.out.println(myResponse);
                    Log.d("PEDRO", myResponse);

                    Example allExpansionsData = gson.fromJson(myResponse, Example.class);

                    if (allExpansionsData != null) {

                        allExpansions = allExpansionsData.getData().getAllExpansions();
                        Log.d("PEDRO", myResponse);

                    }
//                    MainActivity.this.runOnUiThread(new Runnable() {
//
//
//                        @Override
//                        public void run() {
//
//                        }
//                    });

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
}
