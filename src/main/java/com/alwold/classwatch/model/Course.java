package com.alwold.classwatch.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="COURSE", uniqueConstraints=@UniqueConstraint(columnNames={"TERM_ID", "COURSE_NUMBER"}))
public class Course implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="COURSE_ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name="TERM_ID")
	private Term term;
	@Column(name="COURSE_NUMBER",length=15,nullable=false)
	private String courseNumber;
	@OneToMany(mappedBy="course")
	private Set<UserCourse> userCourses;

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

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Set<UserCourse> getUserCourses() {
		return userCourses;
	}

	public void setUserCourses(Set<UserCourse> userCourses) {
		this.userCourses = userCourses;
	}

}
