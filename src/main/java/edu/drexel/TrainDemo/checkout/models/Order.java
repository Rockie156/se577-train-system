package edu.drexel.TrainDemo.checkout.models;

import edu.drexel.TrainDemo.cart.models.Cart;

public class Order {
    private Cart cart;
    private Billing billing;

    public Order(Cart cart, Billing billing) {
        this.cart = cart;
        this.billing = billing;
    }

    public Cart getCart() {
        return cart;
    }

    public Billing getBilling() {
        return billing;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cart=" + cart +
                ", billing=" + billing +
                '}';
    }
}
