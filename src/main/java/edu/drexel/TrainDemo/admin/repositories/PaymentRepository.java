package edu.drexel.TrainDemo.admin.repositories;

import edu.drexel.TrainDemo.admin.models.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
}
