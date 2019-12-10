package com.lenoxys.mtgextensions.ui;

import android.os.Bundle;

import com.google.gson.Gson;
import com.lenoxys.mtgextensions.R;
import com.lenoxys.mtgextensions.business.model.AllExpansions;
import com.lenoxys.mtgextensions.ui.adapter.AllExpansionsAdapter;

import com.lenoxys.mtgextensions.business.model.Expansion;
import com.lenoxys.mtgextensions.ui.fragments.ExpansionDetailFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private ArrayList<Expansion> allExpansions = new ArrayList<>();
    Gson gson = new Gson();


    private RecyclerView recyclerView;
    private AllExpansionsAdapter expansionsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    TextView textViewProgressBar;


    private Callback onAllExpansionsFetchedCallback = new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            if (response.isSuccessful()) {
                String jsonResult = Objects.requireNonNull(response.body()).string();
                AllExpansions allExpansionsData = gson.fromJson(jsonResult, AllExpansions.class);
                allExpansions = allExpansionsData.getExpansions();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateExpansionList();
                    }
                });
            }
        }
    };

    private void populateExpansionList() {
        if (allExpansions != null && !allExpansions.isEmpty()) {
            expansionsAdapter.setExpansionList(allExpansions, onItemClickListener);
            expansionsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    private void setup() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        expansionsAdapter = new AllExpansionsAdapter(allExpansions);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(expansionsAdapter);
        setContentView(R.layout.loading_screen);
        progressBar = findViewById(R.id.progress_bar);
        textViewProgressBar = findViewById(R.id.text_view_progress_bar);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
        fetchAllExpansions();
    }
    //when the user choose one extension pack

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String expansionId = (String) view.getTag();
            ExpansionDetailFragment expansionDetailFragment = ExpansionDetailFragment.newInstance(expansionId);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(ExpansionDetailFragment.TAG);
            fragmentTransaction.replace(R.id.fragment_container, expansionDetailFragment, ExpansionDetailFragment.TAG);
            fragmentTransaction.commit();
        }
    };

    public void fetchAllExpansions() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://cdn.bigar.com/mtg/cardjson/expansions";
        Request request;
        request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(onAllExpansionsFetchedCallback);
    }

    public void progressAnimation() {
        ProgressBarAnimation anim = new ProgressBarAnimation(this, progressBar, textViewProgressBar, 0f, 100f);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }

}