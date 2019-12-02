package com.lenoxys.mtgextensions.business.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenoxys.mtgextensions.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExpansionAdapter extends RecyclerView.Adapter<ExpansionAdapter.ExpansionViewHolder> {

    private Expansion actualExpansion;
    private List<Card> cardList;
    private View.OnClickListener onClickListener;

    ExpansionAdapter(Expansion expansion, List<Card> cards) {
        this.cardList = cards;
        this.actualExpansion = expansion;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    void setCardList(List<Card> cards, View.OnClickListener onClickListener) {
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
        holder.textViewExpansionName.setText(actualExpansion.getName());
        holder.textViewExpansionDate.setText(actualExpansion.getReleaseDate());
        holder.textViewExpansionCardCount.setText((int) actualExpansion.getCardCount());

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class ExpansionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewExpansionName;
        TextView textViewExpansionDate;
        TextView textViewExpansionCardCount;

        ExpansionViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
            super(itemView);

            textViewExpansionName = itemView.findViewById(R.id.fragment_expansion_name);
            textViewExpansionDate = itemView.findViewById(R.id.fragment_expansion_date);
            textViewExpansionCardCount = itemView.findViewById(R.id.fragment_expansion_cards);


            itemView.setOnClickListener(onClickListener);
        }
    }
}
