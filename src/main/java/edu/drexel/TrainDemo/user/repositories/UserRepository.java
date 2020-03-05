package edu.drexel.TrainDemo.user.repositories;

import edu.drexel.TrainDemo.user.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByExternalId(long externalId);
}
