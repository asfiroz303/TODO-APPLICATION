package com.bridgeit.todo.dao;

import com.bridgeit.todo.model.User;

public interface UserDao {
	public void saveUser(User user);
	public User userLogin(String email, String password);

}
