package com.lenoxys.mtgextensions.business.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lenoxys.mtgextensions.R;

public class CardDetailFragment extends Fragment {
    public static final String TAG = "CardDetailFragment";
    private static final String CARDID = "text";

    public CardDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_detail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView cardName = view.findViewById(R.id.card_name);
        TextView rarity = view.findViewById(R.id.card_rarity);
        TextView manaCost = view.findViewById(R.id.card_manacost);
        TextView artistName = view.findViewById(R.id.card_artist);
        TextView price = view.findViewById(R.id.card_price);
        TextView type = view.findViewById(R.id.card_type);
        ImageView cardimage = view.findViewById(R.id.card_image);
        Button backButton = view.findViewById(R.id.card_back);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static CardDetailFragment newInstance(String cardId) {
        CardDetailFragment fragment = new CardDetailFragment();
        Bundle args = new Bundle();
        args.putString(CARDID, cardId);
        fragment.setArguments(args);
        return fragment;
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        }
    };


}


