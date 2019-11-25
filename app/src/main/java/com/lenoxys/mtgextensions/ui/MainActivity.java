package com.lenoxys.mtgextensions.ui;

import android.os.Bundle;

import com.google.gson.Gson;
import com.lenoxys.mtgextensions.R;
import com.lenoxys.mtgextensions.business.model.AllData;
import com.lenoxys.mtgextensions.business.model.AllExpansion;
import com.lenoxys.mtgextensions.business.model.ExpansionAdapter;
import com.lenoxys.mtgextensions.listener.OnNetworkListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import net.danlew.android.joda.JodaTimeAndroid;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //boa pratica
    private static String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ExpansionAdapter expansionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<AllExpansion> allExpansions = new ArrayList<>();

    Gson gson = new Gson();

    //fetch all expansions from api
    private Callback onAllExpansionsFetchedCallback = new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            Log.e(TAG, e.getMessage());
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            if (response.isSuccessful()) {

                //pass the api into a string
                String jsonResult = Objects.requireNonNull(response.body()).string();
                //send the data object inside of AllData object.
                AllData allExpansionsData = gson.fromJson(jsonResult, AllData.class);
                //insert all expansions into a list.
                allExpansions = allExpansionsData.getData().getAllExpansions();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        populateExpansionList();
                    }
                });
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    private void setup() {

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        expansionAdapter = new ExpansionAdapter(allExpansions);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(expansionAdapter);

        fetchAllExpansions();
    }

    private void populateExpansionList() {
        if (allExpansions != null && !allExpansions.isEmpty()) {

            expansionAdapter.setExpansionList(allExpansions);
            expansionAdapter.notifyDataSetChanged();
        }
    }

    public void fetchAllExpansions() {

        OkHttpClient client = new OkHttpClient();
        String url = "https://cdn.bigar.com/mtg/cardjson/expansions";

        Request request;
        request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(onAllExpansionsFetchedCallback);

    }


}