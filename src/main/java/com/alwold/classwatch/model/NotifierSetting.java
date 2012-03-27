package com.alwold.classwatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="NOTIFIER_SETTING", uniqueConstraints={@UniqueConstraint(columnNames={"USER_COURSE_ID", "TYPE"})})
public class NotifierSetting {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NOTIFIER_SETTING_ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name="USER_COURSE_ID")
	private UserCourse userCourse;
	@Column(name="TYPE", length=50)
	private String type;
	@Column(name="ENABLED", nullable=false)
	private Boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserCourse getUserCourse() {
		return userCourse;
	}

	public void setUserCourse(UserCourse userCourse) {
		this.userCourse = userCourse;
	}
}
