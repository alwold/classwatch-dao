package com.alwold.classwatch.school;

import com.alwold.classwatch.model.School;
import com.alwold.classwatch.model.Status;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 *
 * @author alwold
 */
public class ClassInfoManager extends JpaDaoSupport {
	@Cacheable("classes")
	public ClassInfo getClassInfo(Long institution, String termCode, String classNumber) throws RetrievalException {
		SchoolPlugin plugin = getSchoolPlugin(institution);
		if (plugin != null) {
			return plugin.getClassInfo(termCode, classNumber);
		} else {
			return null;
		}
	}
	
	@Cacheable("classStatuses")
	public Status getClassStatus(Long institution, String termCode, String classNumber) throws RetrievalException {
		SchoolPlugin plugin = getSchoolPlugin(institution);
		if (plugin != null) {
			return plugin.getClassStatus(termCode, classNumber);
		} else {
			return null;
		}
	}
	
	private SchoolPlugin getSchoolPlugin(Long institution) {
		School school = getJpaTemplate().find(School.class, institution);
		try {
			SchoolPlugin plugin = (SchoolPlugin) Class.forName(school.getPluginClass()).newInstance();
			return plugin;
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
