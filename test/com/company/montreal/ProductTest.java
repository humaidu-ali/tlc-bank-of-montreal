package com.company.montreal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void productCanBePricedAsStocks() {
        Product product = new Product("W3");
        product.price("USD", "RT");
        assertEquals(Product.STOCK_PRICE, product.getPrice(), "Product has not been priced as Stocks");
    }

    @Test
    public void productCanBePricedAsFutures() {
        Product product = new Product("W3");
        product.price("USD", "RT", 3, 20);
        assertEquals(Product.FUTURES_PRICE, product.getPrice(), "Product has not been priced as Futures");
    }
}