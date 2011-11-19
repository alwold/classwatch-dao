package com.alwold.classwatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="COURSE")
public class Course {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="COURSE_ID")
	private Long id;
	@Column(name="TERM",length=10,nullable=false)
	private String term;
	@Column(name="COURSE_NUMBER",length=15,nullable=false)
	private String courseNumber;

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
}
