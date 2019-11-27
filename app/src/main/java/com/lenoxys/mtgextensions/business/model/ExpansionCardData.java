package com.lenoxys.mtgextensions.business.model;

import java.util.ArrayList;

public class ExpansionCardData {

    Expansion ExpansionObject;
    ArrayList<ExpansionCard> expansionCards = new ArrayList<ExpansionCard>();


    // Getter Methods

    public Expansion getExpansion() {
        return ExpansionObject;
    }

    // Setter Methods

    public void setExpansion(Expansion expansionObject) {
        this.ExpansionObject = expansionObject;
    }
}


