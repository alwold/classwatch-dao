package com.alwold.classwatch.notification;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.User;
import com.alwold.classwatch.school.ClassInfo;

/**
 *
 * @author alwold
 */
public interface Notifier {
	void notify(User user, Course course, ClassInfo classInfo);
}
