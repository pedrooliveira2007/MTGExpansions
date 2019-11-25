package com.lenoxys.mtgextensions.business.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lenoxys.mtgextensions.R;

import java.util.ArrayList;
import java.util.List;

public class ExpansionAdapter extends RecyclerView.Adapter<ExpansionAdapter.ExpansionViewHolder> {
    private List<AllExpansion> expansionList;

    public ExpansionAdapter(List<AllExpansion> allExpansionList) {
        this.expansionList = allExpansionList;
    }

    public List<AllExpansion> getExpansionList() {
        return expansionList;
    }

    public void setExpansionList(List<AllExpansion> expansionList) {
        this.expansionList = expansionList;
    }

    @NonNull
    @Override
    public ExpansionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expansion_item, parent, false);

        return new ExpansionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpansionViewHolder holder, int position) {

        AllExpansion currentItem = expansionList.get(position);
        holder.expansionCards.setText(currentItem.getCardCount() + " cards");
        holder.expansionDate.setText("Released in " + currentItem.getReleaseDate());
        holder.expansionName.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return expansionList.size();
    }

    static class ExpansionViewHolder extends RecyclerView.ViewHolder {

        TextView expansionName;
        TextView expansionId;
        TextView expansionDate;
        TextView expansionCards;

        ExpansionViewHolder(@NonNull View itemView) {
            super(itemView);
            expansionId = itemView.findViewById(R.id.expansionId);
            expansionCards = itemView.findViewById(R.id.expansionCards);
            expansionDate = itemView.findViewById(R.id.expansionDate);
            expansionName = itemView.findViewById(R.id.expansionName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    OpenFragment(expansionId.getText().toString());

                }
            });
        }

        private void OpenFragment(String expansionId) {
            CardDetailFragment cardDetailFragment = CardDetailFragment.newInstance(expansionId);
            FragmentManager fragmentManager;


        }
    }
}
