package com.lenoxys.mtgextensions.ui.fragments;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView cardName = view.findViewById(R.id.card_name);
        TextView rarity = view.findViewById(R.id.card_rarity);
        TextView price1 = view.findViewById(R.id.card_price0);
        TextView price2 = view.findViewById(R.id.card_price1);
        TextView price3 = view.findViewById(R.id.card_price2);
        TextView type = view.findViewById(R.id.card_type);

        ImageView cardImage = view.findViewById(R.id.card_image);
        ArrayList<TextView> sites = new ArrayList<>();
        sites.add(price1);
        sites.add(price2);
        sites.add(price3);

        cardName.setText(card.getName().getEn());
        rarity.setText("rarity: " + setRarity(card.getRarity()));

        type.setText(card.getType().getEn());
        Picasso.get().load(card.getImageUrls().get(0).getLarge()).into(cardImage);
        addValues(sites);
    }

    private String setRarity(String rar) {

        String rarity;
        switch (rar) {

            case "C":
                rarity = "common";
                break;

            case "U":
                rarity = "uncommon";
                break;

            case "R":
                rarity = "rare";
                break;

            case "M":
                rarity = "Mythic";
                break;

            default:
                rarity = "undefined";
                break;
        }
        return rarity;
    }

    private void addValues
            (ArrayList<TextView> sites) {

        if (card.getProviderPrices() != null) {
            for (int i = 0; i < card.getProviderPrices().size(); i++) {
                if (i >= 3) {
                    break;
                } else {
                    if (card.getProviderPrices().get(i) != null) {
                        sites.get(i).setText(card.getProviderPrices().get(i).getCardUrl());
                    }
                }
            }
        }
    }

    @Override
    public void onResume
            () {
        super.onResume();
    }

    public static CardDetailFragment newInstance
            (String
                     cardId, Card
                     card) {
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