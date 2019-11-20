package com.lenoxys.mtgextensions.business.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class ProviderPrices {
    private String providerId;

    private String cardUrl;

    private DateTime date;

    private List<Integer> prices;

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderId() {
        return this.providerId;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public String getCardUrl() {
        return this.cardUrl;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public DateTime getDate() {
        return this.date;
    }

    public void setPrices(List<Integer> prices) {
        this.prices = prices;
    }

    public List<Integer> getPrices() {
        return this.prices;
    }
}
