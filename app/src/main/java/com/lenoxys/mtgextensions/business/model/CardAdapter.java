package com.lenoxys.mtgextensions.business.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lenoxys.mtgextensions.R;


class CardAdapter {
    private Card actualCard;
    private View.OnClickListener onClickListener;


    CardAdapter(Card card) {
        this.actualCard = card;
    }

    public Card getCard() {
        return actualCard;
    }

    void setCard(Card card, View.OnClickListener onClickListener) {
        this.actualCard = card;
        this.onClickListener = onClickListener;
    }

    public CardAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardAdapter.CardViewHolder(view, onClickListener);
    }

    public void onBindViewHolder(final CardAdapter.CardViewHolder holder, int position) {
        holder.textViewCardName.setText(actualCard.getName().getEn());
        holder.textViewCardRarity.setText("Rarity: " + actualCard.getRarity());
        holder.textViewExpansionCardManaCost.setText("mana: " + actualCard.getManacost());
    }

    class CardViewHolder {
        TextView textViewCardName;
        TextView textViewCardRarity;
        TextView textViewExpansionCardManaCost;

        CardViewHolder(View view, View.OnClickListener onClickListener) {
            textViewCardRarity = view.findViewById(R.id.card_rarity);
            textViewCardName = view.findViewById(R.id.card_name);
            textViewCardRarity = view.findViewById(R.id.card_rarity);
            textViewExpansionCardManaCost = view.findViewById(R.id.card_manacost);
            view.setOnClickListener(onClickListener);
        }
    }
}
