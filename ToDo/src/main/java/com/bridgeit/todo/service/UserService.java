package com.bridgeit.todo.service;

import com.bridgeit.todo.model.User;

public interface UserService {

	 void saveUser(User user);
	 User userLogin(String email, String password);
	 
}
