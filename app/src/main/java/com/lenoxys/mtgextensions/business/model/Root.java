package com.lenoxys.mtgextensions.business.model;

public class Root {
    private Data data;

    private Localization localization;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public Localization getLocalization() {
        return this.localization;
    }
}

