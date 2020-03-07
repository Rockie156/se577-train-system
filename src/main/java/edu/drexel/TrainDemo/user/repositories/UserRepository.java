package edu.drexel.TrainDemo.user.repositories;

import edu.drexel.TrainDemo.user.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByExternalId(long externalId);
}
