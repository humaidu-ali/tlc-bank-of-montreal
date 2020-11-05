package com.company.montreal;

public class Product implements ProductPricingService {
    private String productId, exchange, ticker, contractCode;
    private int month, year;
    private double price = 0.0;

    public static final int STOCK_PRICE = 30;
    public static final int FUTURES_PRICE = 40;

    public Product(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return this.price;
    }


    @Override
    public double price(String exchange, String ticker) {
        this.exchange = exchange;
        this.ticker = ticker;
        this.price = STOCK_PRICE;
        return this.price;
    }

    @Override
    public double price(String exchange, String contractCode, int month, int year) {
        this.exchange = exchange;
        this.contractCode = contractCode;
        this.month = month;
        this.year = year;
        this.price = FUTURES_PRICE;
        return this.price;
    }
}
