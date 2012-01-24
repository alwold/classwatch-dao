package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.NotifierSetting;
import com.alwold.classwatch.model.User;
import java.util.List;

/**
 *
 * @author alwold
 */
public interface UserDao {
	public void saveUser(User user);
	public User getUser(String email);
	public List<NotifierSetting> getNotifierSettings(User user);
	public void setNotifierEnabled(User user, String type, boolean enabled);
	
}
