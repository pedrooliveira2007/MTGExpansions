package com.lenoxys.mtgextensions.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenoxys.mtgextensions.R;
import com.lenoxys.mtgextensions.business.model.Card;
import com.lenoxys.mtgextensions.business.model.Expansion;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExpansionAdapter extends RecyclerView.Adapter<ExpansionAdapter.ExpansionViewHolder> {

    private Expansion actualExpansion;
    private List<Card> cardList;
    private View.OnClickListener onClickListener;

    public ExpansionAdapter(Expansion expansion, List<Card> cards) {
        this.cardList = cards;
        this.actualExpansion = expansion;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cards, View.OnClickListener onClickListener) {
        this.cardList = cards;
        this.onClickListener = onClickListener;
    }

    @NotNull
    @Override
    public ExpansionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ExpansionViewHolder(view, onClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ExpansionViewHolder holder, int position) {
        Card currentItem = cardList.get(position);
        holder.textViewCardName.setText(currentItem.getName().getEn());
        holder.textViewCardRarity.setText("Rarity: " + currentItem.getRarity());
        holder.textViewExpansionCardManaCost.setText("mana: " + currentItem.getManacost());
        holder.itemView.setTag(currentItem.getFriendlyId());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class ExpansionViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCardName;
        TextView textViewCardRarity;
        TextView textViewExpansionCardManaCost;

        ExpansionViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
            super(itemView);

            textViewCardRarity = itemView.findViewById(R.id.card_rarity);
            textViewCardName = itemView.findViewById(R.id.card_name);
            textViewCardRarity = itemView.findViewById(R.id.card_rarity);
            textViewExpansionCardManaCost = itemView.findViewById(R.id.card_manacost);
            itemView.setOnClickListener(onClickListener);
        }
    }
}
