package com.example.mtg_extensions_java_android.Business.Model;

import java.util.ArrayList;
import java.util.List;

public class Localization {
    private String defaultLanguage;

    private List<String> languages;

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getDefaultLanguage() {
        return this.defaultLanguage;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getLanguages() {
        return this.languages;
    }
}