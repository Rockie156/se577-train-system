package edu.drexel.TrainDemo.checkout.models;

public class Billing {
    private String creditCard;


    public Billing(String creditCard) {
        this.creditCard = creditCard;
    }

    public Billing() {

    }

    public String getCreditCard() {
        return creditCard;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "creditCard='" + creditCard + '\'' +
                '}';
    }
}