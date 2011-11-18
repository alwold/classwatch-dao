package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.User;

/**
 *
 * @author alwold
 */
public interface UserDao {
	public void saveUser(User user);

	public User getUser(String email);
	
}
