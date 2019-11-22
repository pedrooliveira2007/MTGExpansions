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

    private static String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ExpansionAdapter expansionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<AllExpansion> allExpansions = new ArrayList<>();
    //    private List<ExpansionItem> expansionItems = new ArrayList<>();
    Gson gson = new Gson();

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
                AllData allExpansionsData = gson.fromJson(jsonResult, AllData.class);
                allExpansions = allExpansionsData.getData().getAllExpansions();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

//                        onNetworkListener.onSuccess();
                        populateExpansionList();
                    }
                });
            }
        }
    };

//    private OnNetworkListener onNetworkListener = new OnNetworkListener() {
//        @Override
//        public void onFailure(Exception e) {
//
//        }
//
//        @Override
//        public void onSuccess() {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    private void setup() {

        JodaTimeAndroid.init(this);
        setContentView(R.layout.activity_main);
//        Log.d("EXPANSION", "lalalalala" + expansionItems.size());
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

    //return the Expansion name for the new window
    public String GetExpansionNameById(String expansionId) {

        //default string for return(just for debug)
        String str = "Throne of Eldraine";

        //get string from expansion object in AllExpansions array
        for (int i = 0; i < allExpansions.size(); i++) {

            if (allExpansions.get(i).getId().equals(expansionId)) {

                str = allExpansions.get(i).getName();
            }
        }

        return str;
    }

//    public List<ExpansionItem> GenerateExpansionRecycleView(List<AllExpansion> expansions) {
//
//        List<ExpansionItem> expansionItems = new ArrayList<>();
//
//        for (int i = 0; i < expansions.size(); i++) {
//
//            ExpansionItem expansion = new ExpansionItem(allExpansions.get(i).getName(),
//                    allExpansions.get(i).getReleaseDate(),
//                    allExpansions.get(i).getCardCount().toString());
//            expansionItems.add(expansion);
//
//        }
//
//        return expansionItems;
//    }

    public void fetchAllExpansions() {

        OkHttpClient client = new OkHttpClient();
        String url = "https://cdn.bigar.com/mtg/cardjson/expansions";
        String urlbaseExpansion = "https://cdn.bigar.com/mtg/cardjson/expansions/";
        Request request;
        request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(onAllExpansionsFetchedCallback);
//        return expansionItems;
    }


}