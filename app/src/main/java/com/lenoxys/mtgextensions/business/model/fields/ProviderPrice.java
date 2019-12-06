package com.lenoxys.mtgextensions.business.model.fields;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderPrice {


    @SerializedName("providerId")
    @Expose
    private String providerId;
    @SerializedName("cardUrl")
    @Expose
    private String cardUrl;
    @SerializedName("prices")
    @Expose
    private List<Integer> prices = null;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public List<Integer> getPrices() {
        return prices;
    }

    public void setPrices(List<Integer> prices) {
        this.prices = prices;
    }

}