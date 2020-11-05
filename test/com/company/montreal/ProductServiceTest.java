package com.company.montreal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    public void productCanBeRegistered() throws ProductAlreadyRegisteredException {
        Product product = new Product("W3");
        product.price("USD", "RT", 3, 20);

        ProductService productService = new ProductService();
        productService.addNewProduct(product);

        assertEquals(1, productService.registeredProductsSize(), "Product has not been registered");
    }

    @Test
    public void duplicateProductCannotBeRegistered() {
        assertThrows(ProductAlreadyRegisteredException.class, () -> {
            Product product = new Product("W3");
            product.price("USD", "RT", 3, 20);

            Product product2 = new Product("W3");
            product2.price("USD", "RT");

            ProductService productService = new ProductService();
            productService.addNewProduct(product);
            productService.addNewProduct(product2);

        }, "Product Already registered exception should be thrown.");
    }

    @Test
    public void canTradeRegisteredProduct() throws ProductAlreadyRegisteredException {
        Product product = new Product("W3");
        product.price("USD", "RT", 3, 20);

        ProductService productService = new ProductService();
        productService.addNewProduct(product);
        productService.trade(product, 2);

        assertEquals(1, productService.tradedProductsSize(), "Product has not been traded");
    }

    @Test
    public void cannotTradeUnregisteredProduct() {
        Product product = new Product("W3");
        product.price("USD", "RT", 3, 20);

        ProductService productService = new ProductService();
        productService.trade(product, 2);

        assertEquals(0, productService.tradedProductsSize(), "Product has not been traded");
    }

    @Test
    public void tradedProductQuantityIsCorrect() throws ProductAlreadyRegisteredException {
        Product product = new Product("W3");
        product.price("USD", "RT", 3, 20);

        ProductService productService = new ProductService();
        productService.addNewProduct(product);
        productService.trade(product, 2);

        assertEquals(2, productService.totalTradeQuantityForDay(), "Product traded quantity is not correct");
    }

    @Test
    public void totalValueForTradedProductIsCorrect() throws ProductAlreadyRegisteredException {
        Product product = new Product("W3");
        product.price("USD", "RT", 3, 20);

        ProductService productService = new ProductService();
        productService.addNewProduct(product);
        productService.trade(product, 2);

        assertEquals(Product.FUTURES_PRICE * 2, productService.totalValueOfDaysTradedProducts(), "Product total traded value is not correct");
    }
}