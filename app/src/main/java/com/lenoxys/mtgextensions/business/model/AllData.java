package com.lenoxys.mtgextensions.business.model;

public class AllData {
    CardData data;
    Localization localization;

    public CardData getCardData() {
        return data;
    }

    public void setCardData(CardData data) {
        this.data = data;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localizationObject) {
        this.localization = localizationObject;
    }

}