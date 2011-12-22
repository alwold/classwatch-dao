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
@Table(name="USER_COURSE")
public class UserCourse implements Serializable {
	@EmbeddedId
	private UserCoursePk pk;
	@Column(name="NOTIFIED", nullable=false)
	private boolean notified;

	public boolean isNotified() {
		return notified;
	}

	public void setNotified(boolean notified) {
		this.notified = notified;
	}

	public UserCoursePk getPk() {
		return pk;
	}

	public void setPk(UserCoursePk pk) {
		this.pk = pk;
	}
	
}
