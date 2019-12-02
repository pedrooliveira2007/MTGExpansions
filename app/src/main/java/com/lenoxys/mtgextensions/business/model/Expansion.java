package com.lenoxys.mtgextensions.business.model;

import java.util.ArrayList;

public class Expansion {
    private String id;
    private String name;
    private String code;
    private String releaseDate;
    private float cardCount;
    ArrayList< Object > providerPrices = new ArrayList < Object > ();


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getCardCount() {
        return cardCount;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setCardCount(float cardCount) {
        this.cardCount = cardCount;
    }
}
