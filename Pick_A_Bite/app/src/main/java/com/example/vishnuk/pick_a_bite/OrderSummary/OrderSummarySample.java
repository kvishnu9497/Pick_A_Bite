package com.example.vishnuk.pick_a_bite.OrderSummary;

public class OrderSummarySample {
    private String itemname;
    private String rate;

    public OrderSummarySample() {
    }

    public OrderSummarySample(String itemname, String rate) {
        this.itemname = itemname;
        this.rate = rate;

    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
