package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.Notification;
import com.alwold.classwatch.model.NotificationStatus;
import com.alwold.classwatch.model.User;
import java.util.Date;
import java.util.List;
import javax.persistence.NonUniqueResultException;
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
		// XXX how could this possibly do anything useful, since date is always different?
		Date timestamp = new Date();
		List<Notification> notifications = getJpaTemplate().find("from Notification where user = ? and course = ? and type = ? and timestamp = ?", user, course, type, timestamp);
		Notification notification = null;
		if (notifications.size() == 1) {
			notification = notifications.get(0);
		} else if (notifications.size() > 1) {
			throw new NonUniqueResultException();
		}
		if (notification == null) {
			notification = new Notification();
			notification.setCourse(course);
			notification.setUser(user);
			notification.setType(type);
			notification.setTimestamp(timestamp);
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
		return getJpaTemplate().find("select n from Notification n join n.pk.user as user where user.email = ?", email);
	}
	
}
