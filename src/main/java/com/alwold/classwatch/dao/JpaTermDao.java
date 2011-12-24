package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Term;
import java.util.List;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 *
 * @author alwold
 */
public class JpaTermDao extends JpaDaoSupport implements TermDao {

	public List<Term> getTerms(Long schoolId) {
		return getJpaTemplate().find("from Term t where t.pk.school.id = ?", schoolId);
	}
	
	
}
