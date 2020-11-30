package com.seaborne.order_validator.model;

public class StockData {
    private double bidPrice;
    private double askPrice;
    private int buyLimit;
    private  String  product;
    private  int sellLimit;
    private  double lastTradedPrice;
    private  double maxPriceShift;

    public  StockData(){

    }

    public StockData(double bidPrice, double askPrice, int buyLimit, String ticker, int sellLimit, double lastTradedPrice, double maxPriceShift) {
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.buyLimit = buyLimit;
        this.product = ticker;
        this.sellLimit = sellLimit;
        this.lastTradedPrice = lastTradedPrice;
        this.maxPriceShift = maxPriceShift;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public int getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(int buyLimit) {
        this.buyLimit = buyLimit;
    }

    public String getTicker() {
        return product;
    }

    public void setTicker(String ticker) {
        this.product = ticker;
    }

    public int getSellLimit() {
        return sellLimit;
    }

    public void setSellLimit(int sellLimit) {
        this.sellLimit = sellLimit;
    }

    public double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    public double getMaxPriceShift() {
        return maxPriceShift;
    }

    public void setMaxPriceShift(double maxPriceShift) {
        this.maxPriceShift = maxPriceShift;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "bidPrice=" + bidPrice +
                ", askPrice=" + askPrice +
                ", buyLimit=" + buyLimit +
                ", ticker='" + product + '\'' +
                ", sellLimit=" + sellLimit +
                ", lastTradedPrice=" + lastTradedPrice +
                ", maxPriceShift=" + maxPriceShift +
                '}';
    }

}
