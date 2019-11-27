package com.lenoxys.mtgextensions.business.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.lenoxys.mtgextensions.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private List<ExpansionCard> cardList;
    public View.OnClickListener onClickListener;


    public CardAdapter(List<ExpansionCard> expansionCardsList) {
        this.cardList = expansionCardsList;
    }

    public List<ExpansionCard> getCardList() {
        return this.cardList;
    }

    public void setCardList(List<ExpansionCard> expansionCardsList, View.OnClickListener onClickListener) {
        this.cardList = expansionCardsList;
        this.onClickListener = onClickListener;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expansion_item, parent, false);

        return new CardViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        ExpansionCard currentItem = cardList.get(position);

        holder.textViewCardRarity.setText(currentItem.getRarity());
        holder.textViewCardName.setText(currentItem.getName().getEn());
        holder.textViewCardType.setText(currentItem.getType().getEn());
        holder.itemView.setTag(currentItem.getId());

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}


class CardViewHolder extends RecyclerView.ViewHolder {

    TextView textViewCardName;
    TextView textViewCardRarity;
    TextView textViewCardType;

    CardViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
        super(itemView);
        textViewCardType = itemView.findViewById(R.id.card_name);
        textViewCardRarity = itemView.findViewById(R.id.card_rarity);
        textViewCardName = itemView.findViewById(R.id.card_type);

        itemView.setOnClickListener(onClickListener);
    }
}
