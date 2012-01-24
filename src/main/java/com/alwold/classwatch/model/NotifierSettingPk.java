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
public class NotifierSettingPk implements Serializable {
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	@Column(name="TYPE", length=50)
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
