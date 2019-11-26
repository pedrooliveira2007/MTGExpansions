package com.lenoxys.mtgextensions.business.model;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lenoxys.mtgextensions.R;
import com.lenoxys.mtgextensions.ui.MainActivity;

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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link CardDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardDetailFragment extends Fragment {

    public static final String TAG = "CardDetailFragment";
    public static final String CARDID = "text";

    private String cardId;


//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;

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

                //run on main thread
                PopulateCardList();
            }
        }
    };




    public CardDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cadrdId Parameter 1.
     * @return A new instance of fragment CardDetailFragment.
     */

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_detail, container, false);
    }

    public void fetchExpansionCards(String expansionId) {

        OkHttpClient client = new OkHttpClient();
        String url = "https://cdn.bigar.com/mtg/cardjson/expansions/";

        Request request;
        request = new Request.Builder().url(url+expansionId).build();
        client.newCall(request).enqueue(onAllExpansionsFetchedCallback);

    }
}