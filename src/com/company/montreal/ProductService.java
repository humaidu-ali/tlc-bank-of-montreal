package com.company.montreal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements MontrealTradedProducts {

    /**
     * Holds registered products
     */
    private final List<Product> registeredProductsList;

    /**
     * Holds registered traded products with quantity
     */
    private final Map<Product, Integer> tradedProductsList;

    public ProductService() {
        this.registeredProductsList = new ArrayList<>();
        this.tradedProductsList = new HashMap<>();
    }

    /**
     * Adds a new product to the system that
     * the class will track statistics for
     *
     * @param product add a product available for trading
     * @throws ProductAlreadyRegisteredException thrown
     *                                           when a product is registered twice
     */
    @Override
    public void addNewProduct(Product product) throws ProductAlreadyRegisteredException {

        if (productIsRegistered(product)) {
            throw new ProductAlreadyRegisteredException("Product ID ".concat(product.getProductId().concat(" is already registered.")));
        }

        this.registeredProductsList.add(product);
    }

    /**
     * Checks if a product is already registered
     *
     * @param product The product to check
     * @return boolean
     */
    private boolean productIsRegistered(Product product) {
        boolean productIsAlreadyRegistered = false;
        for (Product p : this.registeredProductsList) {
            if (p.getProductId().equals(product.getProductId())) {
                productIsAlreadyRegistered = true;
                break;
            }
        }

        return productIsAlreadyRegistered;
    }

    /**
     * Returns the registered product list size
     *
     * @return int
     */
    public int registeredProductsSize() {
        return this.registeredProductsList.size();
    }

    /**
     * Books a quantity against the product traded. If the product
     * has not been registered, no quantity is recorded as
     * it is not a product we are required to track.
     *
     * @param product  the product traded
     * @param quantity the quantity traded
     */
    @Override
    public void trade(Product product, int quantity) {
        if (productIsRegistered(product)) {
            this.tradedProductsList.put(product, quantity);
        }
    }

    /**
     * Returns the size of the traded products
     * @return int
     */
    public int tradedProductsSize() {
        return this.tradedProductsList.size();
    }

    /**
     * Calculates the total quantity of all the registered
     * traded products so far today
     *
     * @return the total quantity traded
     */
    @Override
    public int totalTradeQuantityForDay() {
        int totalTradeQuantity = 0;
        for (Integer i: this.tradedProductsList.values()) {
            totalTradeQuantity += i;
        }
        return totalTradeQuantity;
    }

    /**
     * Calculates the total value of all the registered traded products
     * so far today. This is done by multiplying the value by the quantity
     * traded.
     *
     * @return the total value of today's traded products that are
     * registered in the system
     */
    @Override
    public double totalValueOfDaysTradedProducts() {
        double totalValue = 0.0;
        for (Map.Entry<Product, Integer> entry : this.tradedProductsList.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            totalValue += product.getPrice() * quantity;
        }

        return totalValue;
    }
}
