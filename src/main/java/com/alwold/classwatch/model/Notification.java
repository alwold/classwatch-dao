package com.alwold.classwatch.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="NOTIFICATION")
public class Notification implements Serializable {
	@EmbeddedId
	private NotificationPk pk;
	@Column(name="STATUS", length=10, nullable=false)
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;
	@Column(name="ATTEMPTS", nullable=false)
	private Integer attempts;
	@Column(name="INFO", length=255, nullable=true)
	private String info;
	@Column(name="LAST_ATTEMPT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAttempt;

	public NotificationPk getPk() {
		return pk;
	}

	public void setPk(NotificationPk pk) {
		this.pk = pk;
	}

	/**
	 * Get information pertaining to the status
	 */
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public Date getLastAttempt() {
		return lastAttempt;
	}

	public void setLastAttempt(Date lastAttempt) {
		this.lastAttempt = lastAttempt;
	}
	
}
