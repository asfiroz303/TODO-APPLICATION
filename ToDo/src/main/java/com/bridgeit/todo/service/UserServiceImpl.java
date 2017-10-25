package com.bridgeit.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.bridgeit.todo.dao.UserDao;
import com.bridgeit.todo.model.User;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public void saveUser(User user) {
		     userDao.saveUser(user);
	}

	public User userLogin(String email, String password) {
		 return userDao.userLogin(email, password);
		
		
	}
}
