package com.alwold.classwatch.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author alwold
 */
@Embeddable
public class TermPk implements Serializable {
	@Column(name="TERM_CODE", length=10)
	private String code;
	@ManyToOne
	@JoinColumn(name="SCHOOL_ID")
	private School school;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
}
