package com.alwold.classwatch.school;

import com.alwold.classwatch.model.Status;

/**
 *
 * @author alwold
 */
public interface SchoolPlugin {
	ClassInfo getClassInfo(String termCode, String classNumber) throws RetrievalException;
	Status getClassStatus(String termCode, String classNumber) throws RetrievalException;
}
