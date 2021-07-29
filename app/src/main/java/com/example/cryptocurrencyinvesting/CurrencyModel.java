package com.example.cryptocurrencyinvesting;

public class CurrencyModel {
    private String bitcoin,btc,price,volume,percentage,market;

    public CurrencyModel(String bitcoin, String btc, String price, String volume, String percentage, String market) {
        this.bitcoin = bitcoin;
        this.btc = btc;
        this.price = price;
        this.volume = volume;
        this.percentage = percentage;
        this.market = market;
    }


    public String getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(String bitcoin) {
        this.bitcoin = bitcoin;
    }

    public String getBtc() {
        return btc;
    }

    public void setBtc(String btc) {
        this.btc = btc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }




}
