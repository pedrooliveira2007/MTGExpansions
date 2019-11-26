package com.lenoxys.mtgextensions.business.model;

public class ExpansionCardData {

    Expansion ExpansionObject;
    ArrayList<Object> allExpansionCards = new ArrayList<Object>();


    // Getter Methods

    public Expansion getExpansion() {
        return ExpansionObject;
    }

    // Setter Methods

    public void setExpansion(Expansion expansionObject) {
        this.ExpansionObject = expansionObject;
    }
}


