package edu.drexel.TrainDemo.checkout.models;

public class Billing {
    private String creditCard;
    private String customerEmail;


    public Billing(String creditCard, String customerEmail) {
        this.creditCard = creditCard;
        this.customerEmail = customerEmail;
    }

    public Billing() {

    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "creditCard='" + creditCard + '\'' +
                "customerEmail='" + customerEmail + '\'' +
                '}';
    }
}