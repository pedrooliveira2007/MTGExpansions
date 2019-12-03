package com.lenoxys.mtgextensions.business.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lenoxys.mtgextensions.R;

public class CardDetailFragment extends Fragment {

    private Card card;
    public static final String TAG = "CardDetailFragment";
    private static final String CARDID = "text";

    public CardDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public static CardDetailFragment newInstance(Card card) {
        //new fragment
        CardDetailFragment fragment = new CardDetailFragment();
        Bundle args = new Bundle();
        args.putString(CARDID, card.getFriendlyId());
        fragment.setArguments(args);
        return fragment;
    }

    private void populateCardView() {
        CardAdapter cardAdapter = new CardAdapter(card);
        cardAdapter.setCard(card, onItemClickListener);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        }
    };
}


