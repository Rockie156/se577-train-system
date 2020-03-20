package edu.drexel.TrainDemo.user.repositories;

import edu.drexel.TrainDemo.user.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByExternalId(long externalId);
}
