package com.lenoxys.mtgextensions.business.model;

import java.util.ArrayList;

public class CardData {
    Expansion expansion;
    ArrayList<Card> allExpansionCards = new ArrayList<Card>();

    // Getter Methods

    public Expansion getExpansion() {
        return expansion;
    }

    // Setter Methods

    public void setExpansion(Expansion expansionObject) {
        this.expansion = expansionObject;
    }



    public ArrayList<Card> getCardlist() {
        return allExpansionCards;
    }

    // Setter Methods

    public void setCardlist(ArrayList<Card> expansionCards) {
        this.allExpansionCards = expansionCards;
    }
}