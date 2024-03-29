package com.lenoxys.mtgextensions.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.lenoxys.mtgextensions.R;
import com.lenoxys.mtgextensions.business.model.Expansion;

import java.util.ArrayList;
import java.util.List;

public class AllExpansionsAdapter extends RecyclerView.Adapter<AllExpansionsAdapter.ExpansionViewHolder> {
    private List<Expansion> expansionList;
    private View.OnClickListener onClickListener;

    public AllExpansionsAdapter(ArrayList<Expansion> allExpansionList) {
        this.expansionList = allExpansionList;
    }


    public List<Expansion> getExpansionList() {
        return expansionList;
    }

    public void setExpansionList(List<Expansion> expansionList, View.OnClickListener onClickListener) {
        this.expansionList = expansionList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ExpansionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expansion_item, parent, false);
        return new ExpansionViewHolder(view, onClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExpansionViewHolder holder, int position) {

        Expansion currentItem = expansionList.get(position);

        holder.textViewExpansionId.setText(currentItem.getId());
        holder.textViewExpansionName.setText(currentItem.getName());
        holder.textViewExpansionDate.setText("Released in " + currentItem.getReleaseDate());
        holder.textViewExpansionCards.setText((int)currentItem.getCardCount() + " cards");
        holder.itemView.setTag(currentItem.getId());
    }

    @Override
    public int getItemCount() {
        return expansionList.size();
    }

    class ExpansionViewHolder extends RecyclerView.ViewHolder {

        TextView textViewExpansionName;
        TextView textViewExpansionId;
        TextView textViewExpansionDate;
        TextView textViewExpansionCards;

        ExpansionViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
            super(itemView);
            textViewExpansionId = itemView.findViewById(R.id.expansionId);
            textViewExpansionCards = itemView.findViewById(R.id.expansionCards);
            textViewExpansionDate = itemView.findViewById(R.id.expansionDate);
            textViewExpansionName = itemView.findViewById(R.id.expansionName);
            itemView.setOnClickListener(onClickListener);
        }
    }
}
