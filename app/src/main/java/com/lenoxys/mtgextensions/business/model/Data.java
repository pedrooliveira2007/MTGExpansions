package com.lenoxys.mtgextensions.business.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("allExpansions")
    @Expose
    private List<AllExpansion> allExpansions = null;

    public List<AllExpansion> getAllExpansions() {
        return allExpansions;
    }

    public void setAllExpansions(List<AllExpansion> allExpansions) {
        this.allExpansions = allExpansions;
    }

}