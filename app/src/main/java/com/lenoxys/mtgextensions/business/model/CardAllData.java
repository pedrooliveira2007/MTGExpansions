package com.lenoxys.mtgextensions.business.model;

public class CardAllData {
    CardData DataObject;
    Localization LocalizationObject;


    // Getter Methods

    public CardData getData() {
        return DataObject;
    }

    public Localization getLocalization() {
        return LocalizationObject;
    }

    // Setter Methods

    public void setData(CardData dataObject) {
        this.DataObject = dataObject;
    }

    public void setLocalization(Localization localizationObject) {
        this.LocalizationObject = localizationObject;
    }
}