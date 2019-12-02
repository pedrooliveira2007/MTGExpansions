package com.lenoxys.mtgextensions.business.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.lenoxys.mtgextensions.R;

import java.util.ArrayList;
import java.util.List;

public class ExpansionsAdapter extends RecyclerView.Adapter<ExpansionsAdapter.ExpansionViewHolder> {
    private List<Object> expansionList;
    private View.OnClickListener onClickListener;

    public ExpansionsAdapter(ArrayList<Object> allExpansionList) {
        this.expansionList = allExpansionList;
    }


    public List<Object> getExpansionList() {
        return expansionList;
    }

    public void setExpansionList(List<Object> expansionList, View.OnClickListener onClickListener) {
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

        Data currentItem = (Data)expansionList.get(position);

        holder.textViewExpansionId.setText(currentItem.getExpansion().getId());
        holder.textViewExpansionName.setText(currentItem.getExpansion().getName());
        holder.textViewExpansionDate.setText("Released in " + currentItem.getExpansion().getReleaseDate());
        holder.textViewExpansionCards.setText(currentItem.getExpansion().getCardCount() + " cards");
        holder.itemView.setTag(currentItem.getExpansion().getId());
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
