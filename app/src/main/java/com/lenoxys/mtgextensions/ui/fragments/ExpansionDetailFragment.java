package com.lenoxys.mtgextensions.ui.fragments;


import com.lenoxys.mtgextensions.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.lenoxys.mtgextensions.business.model.AllData;
import com.lenoxys.mtgextensions.business.model.Card;
import com.lenoxys.mtgextensions.business.model.Expansion;
import com.lenoxys.mtgextensions.ui.adapter.ExpansionAdapter;

import org.jetbrains.annotations.NotNull;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExpansionDetailFragment extends Fragment {

    private Expansion expansion;
    public static final String TAG = "ExpansionDetailFragment";
    private static final String EXPANSION_ID = "text";
    private RecyclerView.LayoutManager layoutManager;
    private List<Card> expansionCards = new ArrayList<>();
    private Gson gson = new Gson();
    String expansionId = "";

    public ExpansionDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expansion_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expansionId = getArguments().getString(EXPANSION_ID);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchExpansionCards(expansionId);
    }

    public void fetchExpansionCards(String expansionId) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://cdn.bigar.com/mtg/cardjson/expansions/";
        Request request;
        request = new Request.Builder().url(url + expansionId).build();
        client.newCall(request).enqueue(onAllExpansionsFetchedCallback);
    }

    private final Callback onAllExpansionsFetchedCallback = new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            if (response.isSuccessful()) {
                String jsonResult = Objects.requireNonNull(response.body()).string();
                AllData allData = gson.fromJson(jsonResult, AllData.class);
                expansionCards = allData.getCardData().getCardlist();
                expansion = allData.getCardData().getExpansion();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            populateCardList();
                        }
                    });
                }
            }
        }
    };

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

    public static ExpansionDetailFragment newInstance(String cardId) {
        ExpansionDetailFragment fragment = new ExpansionDetailFragment();
        Bundle args = new Bundle();
        args.putString(EXPANSION_ID, cardId);
        fragment.setArguments(args);
        return fragment;
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Card card = new Card();

            String cardId = (String) view.getTag();//change the string
            for (Card c : expansionCards) {
                if (c.getFriendlyId().equals(cardId)) {
                    card = c;
                    break;
                }

            }

            CardDetailFragment cardDetailFragment = CardDetailFragment.newInstance(cardId, card);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(CardDetailFragment.TAG);
            fragmentTransaction.replace(R.id.fragment_container, cardDetailFragment, String.valueOf(false));
            fragmentTransaction.commit();
        }
    };
}
