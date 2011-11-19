package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Course;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alwold
 */
@Transactional
public class JpaCourseDao extends JpaDaoSupport implements CourseDao {

	public void saveCourse(Course course) {
		getJpaTemplate().persist(course);
	}
	
}
