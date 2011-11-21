package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.apache.log4j.Logger;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alwold
 */
@Transactional
public class JpaCourseDao extends JpaDaoSupport implements CourseDao {
	private static Logger logger = Logger.getLogger(JpaCourseDao.class);

	public void saveCourse(Course course) {
		getJpaTemplate().persist(course);
	}
	
	public List<Course> getCourses(String email) {
		return getJpaTemplate().find("from Course c where c.user.email = ?", email);
	}

	public void addCourse(final String email, String term, String courseNumber) {
		final Course course = new Course();
		course.setTerm(term);
		course.setCourseNumber(courseNumber);
		getJpaTemplate().execute(new JpaCallback<Object>(){

			public Object doInJpa(EntityManager em) throws PersistenceException {
				logger.trace("finding user");
				User user = (User) em.createQuery("from User u where u.email = ?").setParameter(1, email).getSingleResult();
				logger.trace("got a user? "+(user != null));
				course.setUser(user);
				logger.trace("saving course");
				em.persist(course);
				return null;
			}
		});
	}
	
}
