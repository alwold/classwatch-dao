package com.alwold.classwatch.notification;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.User;
import com.alwold.classwatch.school.ClassInfo;
import com.techventus.server.voice.Voice;
import java.io.IOException;

/**
 *
 * @author alwold
 */
public class GoogleVoiceSmsNotifier implements Notifier {
	private String username;
	private String password;

	public void notify(User user, Course course, ClassInfo classInfo) throws NotificationException {
		try {
			Voice voice = new Voice(username, password);
			String msg = "Your class (" + course.getCourseNumber() + ") is now available.";
			voice.sendSMS(user.getPhone(), msg);
		} catch (IOException e) {
			throw new NotificationException(e);
		}
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
