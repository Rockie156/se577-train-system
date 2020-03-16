package edu.drexel.TrainDemo.user.services;

import edu.drexel.TrainDemo.user.models.UserEntity;
import edu.drexel.TrainDemo.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getOrCreateUser(Integer externalId, String defaultName) {
        long id = externalId.longValue();

        UserEntity currentUser = getUser(id);

        if (currentUser != null) {
            return currentUser;
        }

        return createUser(id, defaultName);
    }

    public UserEntity getUser(long externalId) {
        Optional<UserEntity> matchingUsers = userRepository.findByExternalId(externalId);
        if (matchingUsers.isPresent()) {
            return matchingUsers.get();
        }
        return null;
    }

    public UserEntity createUser(long externalId, String name) {
        UserEntity newUser = new UserEntity(name, "", externalId);
        return userRepository.save(newUser);
    }

    public void saveUser(UserEntity original, UserEntity userEntity) {
        Long id = original.getId();
        Optional<UserEntity> updatedUserResult = userRepository.findById(id);
        UserEntity updatedUser = updatedUserResult.get();

        String newName = userEntity.getName();
        String newEmail = userEntity.getEmail();

        updatedUser.setName(newName);
        updatedUser.setEmail(newEmail);

        userRepository.save(updatedUser);
    }
}
