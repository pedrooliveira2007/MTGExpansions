package com.lenoxys.mtgextensions.business.model;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenoxys.mtgextensions.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link CardDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CARDID = "text";

    private String cardId;

    public CardDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cadrdId Parameter 1.
     * @return A new instance of fragment CardDetailFragment.
     */

    public static CardDetailFragment newInstance(String cadrdId) {

        CardDetailFragment fragment = new CardDetailFragment();
        Bundle args = new Bundle();
        args.putString(CARDID, cadrdId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cardId = getArguments().getString(CARDID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_detail, container, false);
    }

}
