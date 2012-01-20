package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.NotificationStatus;
import com.alwold.classwatch.model.User;

/**
 *
 * @author alwold
 */
public interface NotificationDao {
	void logNotification(Course course, User user, String type, NotificationStatus status, String info);
}
