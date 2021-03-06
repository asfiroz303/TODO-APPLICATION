package com.bridgeit.todo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.todo.dao.UserDao;
import com.bridgeit.todo.model.User;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public int saveUser(User user) {
		
		String hashedPassword = null;
    
    if (!(user.getPassword() == null)) {
		  hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
		  user.setPassword(hashedPassword);
		  }
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
		return userDao.getUserByEmail(email);
	}


	@Override
	public boolean setPassword(User user1) {
		return userDao.setPassword(user1);
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
		
	}
	
}
