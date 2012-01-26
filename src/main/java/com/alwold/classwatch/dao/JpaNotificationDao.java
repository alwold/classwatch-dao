package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.Notification;
import com.alwold.classwatch.model.NotificationPk;
import com.alwold.classwatch.model.NotificationStatus;
import com.alwold.classwatch.model.User;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alwold
 */
@Transactional
public class JpaNotificationDao extends JpaDaoSupport implements NotificationDao {
	private static Logger logger = Logger.getLogger(JpaNotificationDao.class);

	public void logNotification(Course course, User user, String type, NotificationStatus status, String info) {
		NotificationPk notificationPk = new NotificationPk();
		notificationPk.setCourse(course);
		notificationPk.setUser(user);
		notificationPk.setType(type);
		notificationPk.setTimestamp(new Date());
		Notification notification = getJpaTemplate().find(Notification.class, notificationPk);
		if (notification == null) {
			notification = new Notification();
			notification.setPk(notificationPk);
			notification.setAttempts(1);
		} else {
			notification.setAttempts(notification.getAttempts()+1);
		}
		notification.setLastAttempt(new Date());
		notification.setStatus(status);
		notification.setInfo(info);
		logger.trace("persisting notification");
		getJpaTemplate().persist(notification);
	}

	public List<Notification> getNotifications(String email) {
		return getJpaTemplate().find("from Notification n join n.pk.user as user where user.email = ?", email);
	}
	
}
