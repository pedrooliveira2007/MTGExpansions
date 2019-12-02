package com.lenoxys.mtgextensions.business.model;


import com.lenoxys.mtgextensions.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExpansionDetailFragment extends Fragment {

    private Expansion expansion;
    public static final String TAG = "ExpansionDetailFragment";
    private static final String CARDID = "text";

    private RecyclerView.LayoutManager layoutManager;

    private List<Card> expansionCards = new ArrayList<>();
    private Gson gson = new Gson();
    String cardId = "";

    public ExpansionDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expansion_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardId = getArguments().getString(CARDID);

    }


    @Override
    public void onResume() {
        super.onResume();
        fetchExpansionCards(cardId);
    }


    private final Callback onAllExpansionsFetchedCallback = new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            if (response.isSuccessful()) {

                //pass the api into a string
                String jsonResult = Objects.requireNonNull(response.body()).string();

                //send the data object inside of AllData object.
                AllData allData = gson.fromJson(jsonResult, AllData.class);
                //insert all expansions into a list.
                expansionCards = allData.getCardData().getCardlist();
                expansion = allData.getCardData().getExpansion();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateCardList();
                    }
                });
            }
        }
    };
    //instantiate new fragment

    public static ExpansionDetailFragment newInstance(String cardId) {
        //new fragment
        ExpansionDetailFragment fragment = new ExpansionDetailFragment();
        Bundle args = new Bundle();
        args.putString(CARDID, cardId);
        fragment.setArguments(args);


        return fragment;
    }

    private void populateCardList() {
        if (expansionCards != null && !expansionCards.isEmpty()) {


            ExpansionAdapter expansionAdapter = new ExpansionAdapter(expansion, expansionCards);
            expansionAdapter.setCardList(expansionCards, onItemClickListener);
            RecyclerView recyclerView = getView().findViewById(R.id.fragment_expansion_recyclerView2);
            recyclerView.setAdapter(expansionAdapter);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            expansionAdapter.notifyDataSetChanged();
        }
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //get extension id
            String expansionId = (String) view.getTag();


//            ExpansionDetailFragment expansionDetailFragment = ExpansionDetailFragment.newInstance(expansionId);
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_constraintlayout, cardDetailFragment, ExpansionDetailFragment.TAG);
//            fragmentTransaction.commit();
        }
    };


    public void fetchExpansionCards(String expansionId) {

        OkHttpClient client = new OkHttpClient();
        String url = "https://cdn.bigar.com/mtg/cardjson/expansions/";

        Request request;
        request = new Request.Builder().url(url + expansionId).build();
        client.newCall(request).enqueue(onAllExpansionsFetchedCallback);

    }

}
