package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.Notification;
import com.alwold.classwatch.model.NotificationStatus;
import com.alwold.classwatch.model.User;
import java.util.List;

/**
 *
 * @author alwold
 */
public interface NotificationDao {
	void logNotification(Course course, User user, String type, NotificationStatus status, String info);
	List<Notification> getNotifications(String email);
}
