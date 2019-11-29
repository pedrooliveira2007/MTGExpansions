package com.lenoxys.mtgextensions.business.model;
import java.util.ArrayList;

public class CardData {

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


