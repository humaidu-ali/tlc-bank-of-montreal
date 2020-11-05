package com.company.montreal;

public class ProductAlreadyRegisteredException extends Exception {

    public ProductAlreadyRegisteredException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
