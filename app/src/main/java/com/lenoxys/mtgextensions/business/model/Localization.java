package com.lenoxys.mtgextensions.business.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Localization {

    @SerializedName("defaultLanguage")
    @Expose
    private String defaultLanguage;
    @SerializedName("languages")
    @Expose
    private List<String> languages;

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

}