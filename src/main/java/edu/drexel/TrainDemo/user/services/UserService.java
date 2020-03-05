package edu.drexel.TrainDemo.user.services;

import edu.drexel.TrainDemo.user.models.User;
import edu.drexel.TrainDemo.user.repositories.UserRepository;

import java.util.List;

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
        List<User> matchingUsers = userRepository.findByExternalId(externalId);
        // TODO what if more than 1 user match
        if (matchingUsers.size() != 1) {
            return null;
        }
        return matchingUsers.get(0);
    }

    public User createUser(long externalId, String name) {
        User newUser = new User(name, "", externalId);
        return userRepository.save(newUser);
    }
}
