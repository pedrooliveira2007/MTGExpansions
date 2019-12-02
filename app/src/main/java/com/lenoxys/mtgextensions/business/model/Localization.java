package com.lenoxys.mtgextensions.business.model;

import java.util.ArrayList;
import java.util.List;
public class Localization {
    private String defaultLanguage;
    ArrayList < Object > languages = new ArrayList < Object > ();


    // Getter Methods

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    // Setter Methods

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }
}