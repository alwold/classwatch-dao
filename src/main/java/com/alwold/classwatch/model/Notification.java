package com.alwold.classwatch.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="NOTIFICATION", uniqueConstraints={@UniqueConstraint(columnNames={"USER_ID", "COURSE_ID", "TYPE", "NOTIFICATION_TIMESTAMP"})})
public class Notification implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NOTIFICATION_ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name="COURSE_ID")
	private Course course;
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	@Column(name="NOTIFICATION_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	@Column(name="TYPE", length=50)
	private String type;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
