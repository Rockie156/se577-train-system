package edu.drexel.TrainDemo.admin.models;

import edu.drexel.TrainDemo.admin.models.entities.PaymentEntity;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;


public class PaymentModel {
    private String name;
		private List<String> availablePaymentOptions2;
		private Iterable<PaymentEntity> availablePaymentOptions;

    public PaymentModel(String name, Iterable<PaymentEntity> options) {
			this.name = name;
			this.availablePaymentOptions = options;
    }

    public String getName() {
        return name;
    }

		public void setName(String name) {
			this.name = name;
		}			

    public Iterable<PaymentEntity> getAvailablePaymentOptions() {
        return availablePaymentOptions;
    }

		public void setAvailablePaymentOptions(List<PaymentEntity> options) {
			this.availablePaymentOptions = options;
		}			
			
		@Override
		public String toString() {
			return "payment model string";
		}
}
