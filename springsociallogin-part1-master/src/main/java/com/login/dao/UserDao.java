package com.login.dao;

import java.util.List;

import com.login.entites.User;

public interface UserDao {
	

	    public List<User> listUser();

	    public void add(User user);

	    public void update(User user);

	    public void delete(User user);

	    public User findUserById(String id);
	    public List<User> findUserByName(String name);
	    public boolean checkUserExist(User user);
	    public boolean loginUser(User user);

		public User findUserByEmail(User user);
	
}
