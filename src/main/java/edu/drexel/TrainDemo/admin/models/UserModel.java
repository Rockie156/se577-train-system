package edu.drexel.TrainDemo.admin.models;

import edu.drexel.TrainDemo.user.models.UserEntity;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;


public class UserModel {
    private String name;
		private Iterable<UserEntity> registeredUsers;

    public UserModel(String name, Iterable<UserEntity> users) {
			this.name = name;
			this.registeredUsers = users;
    }

    public String getName() {
        return name;
    }

		public void setName(String name) {
			this.name = name;
		}			

    public Iterable<UserEntity> getRegisteredUsers() {
        return registeredUsers;
    }

		public void setRegisteredUsers(List<UserEntity> users) {
			this.registeredUsers = users;
		}			
			
		@Override
		public String toString() {
			return "user model string";
		}
}
