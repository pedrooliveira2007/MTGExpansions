package com.lenoxys.mtgextensions.business.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("allExpansions")
    @Expose
    private List<Expansion> allExpansions = null;

    public List<Expansion> getAllExpansions() {
        return allExpansions;
    }

    public void setAllExpansions(List<Expansion> allExpansions) {
        this.allExpansions = allExpansions;
    }

}