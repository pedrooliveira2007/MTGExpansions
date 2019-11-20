package com.example.mtg_extensions_java_android.Business.Model;

import java.util.ArrayList;
import java.util.List;

public class AllExpansionCards {
    private List<ImageUrls> imageUrls;

    private List<ProviderPrices> providerPrices;

    private int priceId;

    private String numberStr;

    private int number;

    private String layouType;

    private String manacost;

    private int cmc;

    private String artist;

    private String rarity;

    private Name name;

    private Type type;

    private int id;

    private String friendlyId;

    public void setImageUrls(List<ImageUrls> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<ImageUrls> getImageUrls() {
        return this.imageUrls;
    }

    public void setProviderPrices(List<ProviderPrices> providerPrices) {
        this.providerPrices = providerPrices;
    }

    public List<ProviderPrices> getProviderPrices() {
        return this.providerPrices;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getPriceId() {
        return this.priceId;
    }

    public void setNumberStr(String numberStr) {
        this.numberStr = numberStr;
    }

    public String getNumberStr() {
        return this.numberStr;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setLayouType(String layouType) {
        this.layouType = layouType;
    }

    public String getLayouType() {
        return this.layouType;
    }

    public void setManacost(String manacost) {
        this.manacost = manacost;
    }

    public String getManacost() {
        return this.manacost;
    }

    public void setCmc(int cmc) {
        this.cmc = cmc;
    }

    public int getCmc() {
        return this.cmc;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getRarity() {
        return this.rarity;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName() {
        return this.name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setFriendlyId(String friendlyId) {
        this.friendlyId = friendlyId;
    }

    public String getFriendlyId() {
        return this.friendlyId;
    }
}