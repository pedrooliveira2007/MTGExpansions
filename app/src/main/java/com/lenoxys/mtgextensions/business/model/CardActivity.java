package com.lenoxys.mtgextensions.business.model;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lenoxys.mtgextensions.R;

public class CardActivity extends AppCompatActivity {

    private static final String TAG = "CardActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_card_detail);
    }
}
