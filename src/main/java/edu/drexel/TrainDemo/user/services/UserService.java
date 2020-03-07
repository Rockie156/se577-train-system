package edu.drexel.TrainDemo.user.services;

import edu.drexel.TrainDemo.user.models.User;
import edu.drexel.TrainDemo.user.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(Integer externalId, String defaultName) {
        long id = externalId.longValue();

        User currentUser = getUser(id);

        if (currentUser != null) {
            return currentUser;
        }

        return createUser(id, defaultName);
    }

    public User getUser(long externalId) {
        Optional<User> matchingUsers = userRepository.findByExternalId(externalId);
        if (matchingUsers.isPresent()) {
            return matchingUsers.get();
        }
        return null;
    }

    public User createUser(long externalId, String name) {
        User newUser = new User(name, "", externalId);
        return userRepository.save(newUser);
    }

    public void saveUser(User original, User user) {
        Long id = original.getId();
        Optional<User> updatedUserResult = userRepository.findById(id);
        User updatedUser = updatedUserResult.get();

        String newName = user.getName();
        String newEmail = user.getEmail();

        updatedUser.setName(newName);
        updatedUser.setEmail(newEmail);

        userRepository.save(updatedUser);
    }
}
