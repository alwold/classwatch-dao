package com.alwold.classwatch.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="COURSE_STATUS")
public class CourseStatus implements Serializable {
	@EmbeddedId
	private CourseStatusPk pk;
	@Column(name="STATUS")
	private Status status;

	public CourseStatusPk getPk() {
		return pk;
	}

	public void setPk(CourseStatusPk pk) {
		this.pk = pk;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
