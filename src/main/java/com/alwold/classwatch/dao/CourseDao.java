package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.Status;
import com.alwold.classwatch.model.User;
import java.util.List;

/**
 *
 * @author alwold
 */
public interface CourseDao {
	void saveCourse(Course course);
	List<Course> getCourses(String email);
	void addCourse(String email, String term, String courseNumber);
	void deleteCourse(String email, Long courseId);
	void logStatus(Long courseId, Status status);
	List<User> getActiveWatchers(Course course);
	void setNotified(User user, Course course);
	List<Course> getCoursesWithWatchers();
}
