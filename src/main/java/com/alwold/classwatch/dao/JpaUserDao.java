package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 *
 * @author alwold
 */
public class JpaUserDao extends JpaDaoSupport implements UserDao {
	private static Logger logger = Logger.getLogger(JpaUserDao.class);

	public void saveUser(User user) {
		logger.trace("saving user");
		logger.trace("email = "+user.getEmail());
		getJpaTemplate().persist(user);
		logger.trace("user id = "+user.getId());
	}

	public User getUser(final String email) {
		return getJpaTemplate().execute(new JpaCallback<User>(){

			public User doInJpa(EntityManager em) throws PersistenceException {
				Query q = em.createQuery("from User u where u.email = :email");
				q.setParameter("email", email);
				List list = q.getResultList();
				logger.trace("got "+list.size()+" things");
				if (list.isEmpty()) {
					return null;
				} else {
					return (User) list.get(0);
				}
			}
		});
	}
}
