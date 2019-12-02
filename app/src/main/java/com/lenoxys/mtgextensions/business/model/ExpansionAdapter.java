package com.lenoxys.mtgextensions.business.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenoxys.mtgextensions.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExpansionAdapter extends RecyclerView.Adapter<ExpansionAdapter.ExpansionViewHolder> {

    protected Expansion actualExpansion;
    protected List<Object> cardList;
    public View.OnClickListener onClickListener;

    public ExpansionAdapter(Expansion expansion,List<Object> cards) {
        this.cardList = cards;
        this.actualExpansion = expansion;
    }

    public List<Object> getCardList() {
        return cardList;
    }

    public void setCardList(List<Object> cards, View.OnClickListener onClickListener) {
        this.cardList = cards;
        this.onClickListener = onClickListener;
    }

    @NotNull
    @Override
    public ExpansionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expansion_item, parent, false);
        return new ExpansionViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(final ExpansionViewHolder holder, int position) {


        //ExpansionCard currentItem = (ExpansionCard) cardList.get(position);

        holder.textViewExpansionName.setText(actualExpansion.getName());
        holder.textViewExpansionDate.setText(actualExpansion.getReleaseDate());
        holder.textViewExpansionCardCount.setText((int) actualExpansion.getCardCount());

//        holder.textViewCardRarity.setText(currentItem.getRarity());
//        holder.textViewCardName.setText(currentItem.getName());
//        holder.textViewCardType.setText(currentItem.getType().getEn());
//        holder.itemView.setTag(currentItem.getId());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class ExpansionViewHolder extends RecyclerView.ViewHolder {

        TextView textViewExpansionName;
        TextView textViewExpansionDate;
        TextView textViewExpansionCardCount;
//        TextView textViewCardRarity;
//        TextView textViewCardName;
//        TextView textViewCardType;

        ExpansionViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
            super(itemView);

            textViewExpansionName = itemView.findViewById(R.id.fragment_expansion_name);
            textViewExpansionDate= itemView.findViewById(R.id.fragment_expansion_date);
            textViewExpansionCardCount= itemView.findViewById(R.id.fragment_expansion_cards);

//            textViewCardRarity = itemView.findViewById(R.id.card_rarity);
//            textViewCardType = itemView.findViewById(R.id.card_type);
//            textViewCardName = itemView.findViewById(R.id.card_name);

            itemView.setOnClickListener(onClickListener);
        }
    }
}
