package com.bridgeit.todo.dao;

import com.bridgeit.todo.model.User;

public interface UserDao {
	public boolean saveUser(User user);
	public User userLogin(String email, String password);
	public User getUserByEmail(String email);
    public boolean setPassword(User user);
	User getUserById(int id);
	boolean updateUser(User user);
}
