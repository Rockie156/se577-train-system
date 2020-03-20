package edu.drexel.TrainDemo.admin.services;

import edu.drexel.TrainDemo.admin.models.entities.PaymentEntity;
import edu.drexel.TrainDemo.admin.repositories.PaymentRepository;

import org.springframework.stereotype.Service;
import java.io.*;
import java.lang.Iterable;

import java.util.Iterator;
import java.util.ArrayList;

@Service
public class PaymentService {
	private PaymentEntity paymentEntity;
	private PaymentRepository paymentRepository;
		
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
 	}

	public Iterable<PaymentEntity> getAllPaymentOptions() {
		return paymentRepository.findAll();
	}

	public void saveNewPayment(PaymentEntity newOption) {
		paymentRepository.save(newOption);
	}

	public void deletePaymentOption(PaymentEntity optionToDelete) {
		Iterable<PaymentEntity> options = getAllPaymentOptions();
    Iterator<PaymentEntity> i = options.iterator();
    while (i.hasNext()) {
			if (optionToDelete.getName().equals(i.next().getName())) {
				i.remove();
	    }
    }

    paymentRepository.deleteAll();
    paymentRepository.saveAll(options);
	}
}
