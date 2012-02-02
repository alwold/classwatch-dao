package com.alwold.classwatch.school;

import com.alwold.classwatch.model.School;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 *
 * @author alwold
 */
public class ClassInfoManager extends JpaDaoSupport {
	@Cacheable("classes")
	public ClassInfo getClassInfo(Long institution, String termCode, String classNumber) throws RetrievalException {
		School school = getJpaTemplate().find(School.class, institution);
		try {
			SchoolPlugin plugin = (SchoolPlugin) Class.forName(school.getPluginClass()).newInstance();
			return plugin.getClassInfo(termCode, classNumber);
		} catch (ClassNotFoundException e) {
			logger.error("Unable to instantiate school plugin", e);
			return null;
		} catch (IllegalAccessException e) {
			logger.error("Unable to instantiate school plugin", e);
			return null;
		} catch (InstantiationException e) {
			logger.error("Unable to instantiate school plugin", e);
			return null;
		}
	}
	
}
