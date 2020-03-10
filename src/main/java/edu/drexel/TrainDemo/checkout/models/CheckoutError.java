package edu.drexel.TrainDemo.checkout.models;

public class CheckoutError {
    private String message;

    public CheckoutError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
