package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.CourseStatus;
import com.alwold.classwatch.model.CourseStatusPk;
import com.alwold.classwatch.model.Status;
import com.alwold.classwatch.model.Term;
import com.alwold.classwatch.model.User;
import com.alwold.classwatch.model.UserCourse;
import com.alwold.classwatch.model.UserCoursePk;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		return getJpaTemplate().find("select uc.pk.course from UserCourse uc where uc.pk.user.email = ?", email);
	}

	public void addCourse(final String email, final String termCode, final String courseNumber) {
		getJpaTemplate().execute(new JpaCallback<Object>(){

			public Object doInJpa(EntityManager em) throws PersistenceException {
				// load the course
				// TODO add school
				Long schoolId = 1L;
				Course course;
				logger.trace("looking for course");
				try {
					course = em.createQuery("from Course c where c.term.pk.code = ? and c.term.pk.school.id = ? and c.courseNumber = ?", Course.class)
							.setParameter(1, termCode)
							.setParameter(2, schoolId)
							.setParameter(3, courseNumber)
							.getSingleResult();
				} catch (NoResultException e) {
					course = new Course();
					Term term = em.createQuery("from Term t where t.pk.code = ? and t.pk.school.id = ?", Term.class)
							.setParameter(1, termCode)
							.setParameter(2, schoolId)
							.getSingleResult();
					course.setTerm(term);
					course.setCourseNumber(courseNumber);
					logger.trace("persisting course");
					em.persist(course);
				}

				logger.trace("finding user");
				User user = (User) em.createQuery("from User u where u.email = ?").setParameter(1, email).getSingleResult();
				UserCourse userCourse = new UserCourse();
				UserCoursePk userCoursePk = new UserCoursePk();
				userCoursePk.setUser(user);
				userCoursePk.setCourse(course);
				userCourse.setPk(userCoursePk);
				userCourse.setNotified(false);
				logger.trace("persisting UserCourse");
				em.persist(userCourse);
				return null;
			}
		});
	}

	public void deleteCourse(final String email, final Long courseId) {
		getJpaTemplate().execute(new JpaCallback<Object>(){

			public Object doInJpa(EntityManager em) throws PersistenceException {
				Course course = em.find(Course.class, courseId);
				User user = em.createQuery("from User u where u.email = ?", User.class)
						.setParameter(1, email)
						.getSingleResult();
				UserCoursePk pk = new UserCoursePk();
				pk.setCourse(course);
				pk.setUser(user);

				UserCourse userCourse = em.find(UserCourse.class, pk);
				em.remove(userCourse);
				return null;
			}
		});
	}

	public void logStatus(final Long courseId, final Status status) {
		logger.trace("logging status "+status+" for "+courseId);
		getJpaTemplate().execute(new JpaCallback<Object>(){

			public Object doInJpa(EntityManager em) throws PersistenceException {
				Course course = em.find(Course.class, courseId);
				List<CourseStatus> statuses = em.createQuery("from CourseStatus cs where cs.pk.course = ? order by cs.pk.timestamp").getResultList();
				if (statuses.isEmpty() || statuses.get(0).getStatus() != status) {
					logger.trace("no existing status or different latest status");
					CourseStatus cs = new CourseStatus();
					CourseStatusPk pk = new CourseStatusPk();
					pk.setCourse(course);
					pk.setTimestamp(new Date());
					cs.setPk(pk);
					cs.setStatus(status);
					em.persist(cs);
				} else {
					logger.trace("same status, not logging");
				}
				return null;
			}
		});
	}
	
}
