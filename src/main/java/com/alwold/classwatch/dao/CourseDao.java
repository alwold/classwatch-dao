package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Course;
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
}
