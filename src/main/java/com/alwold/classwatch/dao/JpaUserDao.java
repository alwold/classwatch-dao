package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.NotifierSetting;
import com.alwold.classwatch.model.NotifierSettingPk;
import com.alwold.classwatch.model.User;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
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
		NotifierSettingPk pk = new NotifierSettingPk();
		pk.setUser(user);
		pk.setType(type);
		NotifierSetting setting = getJpaTemplate().find(NotifierSetting.class, pk);
		if (setting == null) {
			setting = new NotifierSetting();
			setting.setPk(pk);
		}
		setting.setEnabled(enabled);
		getJpaTemplate().merge(setting);
	}

	public String generateResetToken(String email) {
		User user = getUser(email);
		user.setResetToken(UUID.randomUUID().toString());
		Calendar expiration = Calendar.getInstance();
		expiration.roll(Calendar.DATE, 1);
		user.setResetTokenExpiration(expiration.getTime());
		getJpaTemplate().merge(user);
		return user.getResetToken();
	}

	public boolean isNotifierEnabled(User user, String type) {
		NotifierSettingPk pk = new NotifierSettingPk();
		pk.setUser(user);
		pk.setType(type);
		NotifierSetting setting = getJpaTemplate().find(NotifierSetting.class, pk);
		if (setting != null && setting.getEnabled()) {
			return true;
		} else {
			return false;
		}
	}
}
