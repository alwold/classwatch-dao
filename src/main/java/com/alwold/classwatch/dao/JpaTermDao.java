package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.School;
import com.alwold.classwatch.model.Term;
import com.alwold.classwatch.model.TermPk;
import java.util.Date;
import java.util.List;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 *
 * @author alwold
 */
public class JpaTermDao extends JpaDaoSupport implements TermDao {

	public List<Term> getTerms(Long schoolId) {
		return getJpaTemplate().find("from Term t where t.pk.school.id = ? and t.startDate <= ? and t.endDate >= ?", schoolId, new Date(), new Date());
	}

	public Term getTerm(Long schoolId, String termCode) {
		School school = getJpaTemplate().find(School.class, schoolId);
		TermPk pk = new TermPk();
		pk.setCode(termCode);
		pk.setSchool(school);
		return getJpaTemplate().find(Term.class, pk);
	}
	
	
}
