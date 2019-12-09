package com.lenoxys.mtgextensions.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lenoxys.mtgextensions.R;
import com.lenoxys.mtgextensions.business.model.Card;
import com.squareup.picasso.Picasso;

public class CardDetailFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "CardDetailFragment";
    private static final String CARDID = "";
    private Card card;

    public CardDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_detail_firetrap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView cardName = view.findViewById(R.id.card_name);
        TextView rarity = view.findViewById(R.id.card_rarity);
        TextView manaCost = view.findViewById(R.id.card_mana_cost);
        TextView artistName = view.findViewById(R.id.card_artist);
        TextView price = view.findViewById(R.id.card_price);
        TextView type = view.findViewById(R.id.card_type);
        ImageView cardImage = view.findViewById(R.id.card_image);
       // Button backButton = view.findViewById(R.id.card_back);

       // backButton.setOnClickListener(this);
        cardName.setText(card.getName().getEn());
        rarity.setText(card.getRarity());
        manaCost.setText(card.getManacost());
        artistName.setText(card.getArtist());
        // price.setText(card.getPr);
        type.setText(card.getType().getEn());
        Picasso.get().load(card.getImageUrls().get(0).getLarge()).into(cardImage);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static CardDetailFragment newInstance(String cardId, Card card) {
        CardDetailFragment fragment = new CardDetailFragment();
        Bundle args = new Bundle();
        args.putString(CARDID, cardId);
        fragment.card = card;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {

    }

}