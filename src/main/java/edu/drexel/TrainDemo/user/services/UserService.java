package edu.drexel.TrainDemo.user.services;

import edu.drexel.TrainDemo.user.models.UserEntity;
import edu.drexel.TrainDemo.user.repositories.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getOrCreateUser(OAuth2User principal) {
        Integer id = principal.getAttribute("id");
        String defaultName = principal.getAttribute("name");
        return getOrCreateUser(id, defaultName);
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
		
		public Iterable<UserEntity> getAllUsers() {
			return userRepository.findAll();
		}

    public UserEntity createUser(long externalId, String name) {
        UserEntity newUser = new UserEntity(name, "", externalId);
        return userRepository.save(newUser);
    }

    public void saveUser(OAuth2User principal, UserEntity userEntity) {
        UserEntity originalUser = getOrCreateUser(principal);
        saveUser(originalUser, userEntity);
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

	
	public void updateUser(UserEntity userToUpdate) {
		Iterable<UserEntity> registeredUsers = getAllUsers();
		Iterator<UserEntity> i = registeredUsers.iterator();
		while (i.hasNext()) {
				UserEntity user = i.next();
			if ((userToUpdate.getName().equals(user.getName())) ||
 					(userToUpdate.getEmail().equals(user.getEmail()))) {
				userToUpdate.setId(user.getId());
				userToUpdate.setExternalId(user.getExternalId());
				i.remove();
			}
		}
		
		userRepository.save(userToUpdate);
	}
	
	public void removeUser(UserEntity userToRemove) {
		Iterable<UserEntity> registeredUsers = getAllUsers();
		Iterator<UserEntity> i = registeredUsers.iterator();
		while (i.hasNext()) {
			if (userToRemove.getEmail().equals(i.next().getEmail())) {
				i.remove();
			}
		}
		
		userRepository.deleteAll();
		userRepository.saveAll(registeredUsers);
	}
}
