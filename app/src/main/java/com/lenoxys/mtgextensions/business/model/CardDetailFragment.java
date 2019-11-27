package com.lenoxys.mtgextensions.business.model;

import android.os.Bundle;

import com.google.gson.Gson;
import com.lenoxys.mtgextensions.R;
import com.lenoxys.mtgextensions.ui.MainActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class CardDetailFragment extends Fragment {

    public static final String TAG = "CardDetailFragment";
    public static final String CARDID = "text";

    private String cardId;

    private Expansion expansion;
    private List<ExpansionCard> expansionCards = new ArrayList<>();

    Gson gson = new Gson();

    //fetch all expansions from api
    private final Callback onAllExpansionsFetchedCallback = new Callback() {
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
                ExpansionCardData allExpansionsData = gson.fromJson(jsonResult, ExpansionCardData.class);
                //insert all expansions into a list.
                expansionCards = allExpansionsData.expansionCards;

                //run on main thread

                PopulateCardList();


            }
        }
    };

    private void PopulateCardList() {
        if (expansionCards != null && !expansionCards.isEmpty()) {

            CardAdapter.setCardList(expansionCards, onItemClickListener);
            CardAdapter.notifyDataSetChanged();
        }
    }


    public CardDetailFragment() {
        // Required empty public constructor
    }

    //instantiate new fragment
    public static CardDetailFragment newInstance(String cadrdId) {

        //new fragment
        CardDetailFragment fragment = new CardDetailFragment();
        Bundle args = new Bundle();
        args.putString(CARDID, cadrdId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cardId = getArguments().getString(CARDID);
        }

        Setup();
    }

    private void Setup() {

        PopulateCardList();

    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //get extension id
            String expansionId = (String) view.getTag();

            //instantiate the fragment
            CardDetailFragment cardDetailFragment = CardDetailFragment.newInstance(expansionId);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_constraintlayout, cardDetailFragment, CardDetailFragment.TAG);
            fragmentTransaction.commit();

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_detail, container, false);
    }

    public void fetchExpansionCards(String expansionId) {

        OkHttpClient client = new OkHttpClient();
        String url = "https://cdn.bigar.com/mtg/cardjson/expansions/";

        Request request;
        request = new Request.Builder().url(url + expansionId).build();
        client.newCall(request).enqueue(onAllExpansionsFetchedCallback);

    }
}