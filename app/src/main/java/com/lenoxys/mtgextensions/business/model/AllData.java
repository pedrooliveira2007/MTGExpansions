package com.lenoxys.mtgextensions.business.model;

import com.lenoxys.mtgextensions.business.model.fields.Localization;

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