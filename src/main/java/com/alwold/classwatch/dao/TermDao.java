package com.alwold.classwatch.dao;

import com.alwold.classwatch.model.Term;
import java.util.List;

/**
 *
 * @author alwold
 */
public interface TermDao {
	List<Term> getTerms(Long schoolId);
	Term getTerm(Long schoolId, String termCode);
}
