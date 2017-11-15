package com.bridgeit.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.bridgeit.todo.dao.UserDao;
import com.bridgeit.todo.model.User;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public int saveUser(User user) {
		return userDao.saveUser(user);
	}

	/*public User userLogin(String email, String password) {
		return userDao.userLogin(email, password);
	}*/
	
	@Override
	public User userLogin(User user) {
		return userDao.userLogin(user);

	}
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmail(email);
	}

	@Override
	public boolean setPassword(User user1) {
		// TODO Auto-generated method stub
		return userDao.setPassword(user1);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
		
	}
}
