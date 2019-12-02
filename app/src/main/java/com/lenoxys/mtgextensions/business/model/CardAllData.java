package com.lenoxys.mtgextensions.business.model;

public class CardAllData {
    Data DataObject;
    Localization LocalizationObject;


    // Getter Methods

    public Data getData() {
        return DataObject;
    }

    public Localization getLocalization() {
        return LocalizationObject;
    }

    // Setter Methods

    public void setData(Data dataObject) {
        this.DataObject = dataObject;
    }

    public void setLocalization(Localization localizationObject) {
        this.LocalizationObject = localizationObject;
    }
}