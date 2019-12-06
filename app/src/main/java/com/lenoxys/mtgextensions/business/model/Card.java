package com.lenoxys.mtgextensions.business.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lenoxys.mtgextensions.business.model.fields.Name;
import com.lenoxys.mtgextensions.business.model.fields.ProviderPrice;
import com.lenoxys.mtgextensions.business.model.fields.Type;
import com.lenoxys.mtgextensions.business.model.fields.URLs;

import java.util.List;

public class Card {
    @SerializedName("priceId")
    @Expose
    private int priceId;
    @SerializedName("expansionCode")
    @Expose
    private String expansionCode;
    @SerializedName("expansionName")
    @Expose
    private String expansionName;
    @SerializedName("expansionFriendlyId")
    @Expose
    private String expansionFriendlyId;
    @SerializedName("numberStr")
    @Expose
    private String numberStr;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("layouType")
    @Expose
    private String layouType;
    @SerializedName("manacost")
    @Expose
    private String manacost;
    @SerializedName("cmc")
    @Expose
    private int cmc;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("rarity")
    @Expose
    private String rarity;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("friendlyId")
    @Expose
    private String friendlyId;
    @SerializedName("imageUrls")
    @Expose
    private List<URLs> imageUrls = null;
    @SerializedName("providerPrices")
    @Expose
    private List<ProviderPrice> providerPrices = null;

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public String getExpansionCode() {
        return expansionCode;
    }

    public void setExpansionCode(String expansionCode) {
        this.expansionCode = expansionCode;
    }

    public String getExpansionName() {
        return expansionName;
    }

    public void setExpansionName(String expansionName) {
        this.expansionName = expansionName;
    }

    public String getExpansionFriendlyId() {
        return expansionFriendlyId;
    }

    public void setExpansionFriendlyId(String expansionFriendlyId) {
        this.expansionFriendlyId = expansionFriendlyId;
    }

    public String getNumberStr() {
        return numberStr;
    }

    public void setNumberStr(String numberStr) {
        this.numberStr = numberStr;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLayouType() {
        return layouType;
    }

    public void setLayouType(String layouType) {
        this.layouType = layouType;
    }

    public String getManacost() {
        return manacost;
    }

    public void setManacost(String manacost) {
        this.manacost = manacost;
    }

    public Integer getCmc() {
        return cmc;
    }

    public void setCmc(int cmc) {
        this.cmc = cmc;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFriendlyId() {
        return friendlyId;
    }

    public void setFriendlyId(String friendlyId) {
        this.friendlyId = friendlyId;
    }

    public List<URLs> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<URLs> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<ProviderPrice> getProviderPrices() {
        return providerPrices;
    }

    public void setProviderPrices(List<ProviderPrice> providerPrices) {
        this.providerPrices = providerPrices;
    }

}