package com.lenoxys.mtgextensions.ui;

import android.os.Bundle;

import com.google.gson.Gson;
import com.lenoxys.mtgextensions.R;
import com.lenoxys.mtgextensions.business.model.AllExpansions;
import com.lenoxys.mtgextensions.business.model.AllExpansionsAdapter;

import com.lenoxys.mtgextensions.business.model.Expansion;
import com.lenoxys.mtgextensions.business.model.ExpansionDetailFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private AllExpansionsAdapter expansionsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Expansion> allExpansions = new ArrayList<>();

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
                AllExpansions allExpansionsData = gson.fromJson(jsonResult, AllExpansions.class);
                //insert all expansions into a list.
                allExpansions = allExpansionsData.getExpansions();

                //run on main thread
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
        expansionsAdapter = new AllExpansionsAdapter(allExpansions);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(expansionsAdapter);

        FetchAllExpansions();
    }

    //when the user choose one extension pack
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //get extension id
            String expansionId = (String) view.getTag();

            //instantiate the fragment
            ExpansionDetailFragment expansionDetailFragment = ExpansionDetailFragment.newInstance(expansionId);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_constraintlayout, expansionDetailFragment, ExpansionDetailFragment.TAG);
            fragmentTransaction.commit();

        }
    };

    private void populateExpansionList() {
        if (allExpansions != null && !allExpansions.isEmpty()) {
            expansionsAdapter.setExpansionList(allExpansions, onItemClickListener);
            expansionsAdapter.notifyDataSetChanged();
        }
    }

    public void FetchAllExpansions() {

        OkHttpClient client = new OkHttpClient();
        String url = "https://cdn.bigar.com/mtg/cardjson/expansions";

        Request request;
        request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(onAllExpansionsFetchedCallback);

    }
}