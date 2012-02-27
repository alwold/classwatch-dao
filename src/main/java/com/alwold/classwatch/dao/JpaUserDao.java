package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.NotifierSetting;
import com.alwold.classwatch.model.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alwold
 */
@Transactional
public class JpaUserDao extends JpaDaoSupport implements UserDao {
	private static Logger logger = Logger.getLogger(JpaUserDao.class);

	public void saveUser(User user) {
		logger.trace("saving user");
		logger.trace("email = "+user.getEmail());
		getJpaTemplate().merge(user);
		logger.trace("user id = "+user.getId());
	}

	@Transactional(readOnly=true)
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

	public List<NotifierSetting> getNotifierSettings(User user) {
		return getJpaTemplate().find("from NotifierSetting where pk.user = ?", user);
	}

	public void setNotifierEnabled(User user, String type, boolean enabled) {
		List<NotifierSetting> settings = getJpaTemplate().find("from NotifierSetting where user = ? and type = ?", user, type);
		NotifierSetting setting = null;
		if (settings.isEmpty()) {
			setting = new NotifierSetting();
		} else if (settings.size() == 1) {
			setting = settings.get(0);
		} else {
			throw new NonUniqueResultException();
		}
		setting.setEnabled(enabled);
		getJpaTemplate().merge(setting);
	}

	public String generateResetToken(String email) {
		User user = getUser(email);
		user.setResetToken(UUID.randomUUID().toString());
		user.setResetTokenSentAt(new Date());
		getJpaTemplate().merge(user);
		return user.getResetToken();
	}

	public boolean isNotifierEnabled(User user, String type) {
		List<NotifierSetting> settings = getJpaTemplate().find("from NotifierSetting where user = ? and type = ?", user, type);
		if (settings.size() > 1) {
			throw new NonUniqueResultException();
		} else if (settings.size() == 1 && settings.get(0).getEnabled()) {
			return true;
		} else {
			return false;
		}
	}
}
