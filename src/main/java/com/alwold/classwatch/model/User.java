package com.alwold.classwatch.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="USERS",
	uniqueConstraints={@UniqueConstraint(columnNames={"EMAIL"}), @UniqueConstraint(columnNames={"RESET_PASSWORD_TOKEN"})})
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long id;
	@Column(name="EMAIL",length=25,nullable=false)
	private String email;
	@Column(name="ENCRYPTED_PASSWORD",length=1024,nullable=false)
	private String password;
	@Column(name="PHONE",length=25,nullable=true)
	private String phone;
	@Column(name="RESET_PASSWORD_TOKEN", length=255, nullable=true)
	private String resetToken;
	@Column(name="RESET_PASSWORD_SENT_AT", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date resetTokenSentAt;
	@Column(name="REMEMBER_CREATED_AT", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date rememberCreatedAt;
	@Column(name="SIGN_IN_COUNT", nullable=true)
	private Integer signInCount;
	@Column(name="CURRENT_SIGN_IN_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date currentSignInAt;
	@Column(name="LAST_SIGN_IN_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastSignInAt;
	@Column(name="CURRENT_SIGN_IN_IP")
	private String currentSignInIp;
	@Column(name="LAST_SIGN_IN_IP")
	private String lastSignInIp;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Date getResetTokenSentAt() {
		return resetTokenSentAt;
	}

	public void setResetTokenSentAt(Date resetTokenSentAt) {
		this.resetTokenSentAt = resetTokenSentAt;
	}

	public Date getCurrentSignInAt() {
		return currentSignInAt;
	}

	public void setCurrentSignInAt(Date currentSignInAt) {
		this.currentSignInAt = currentSignInAt;
	}

	public String getCurrentSignInIp() {
		return currentSignInIp;
	}

	public void setCurrentSignInIp(String currentSignInIp) {
		this.currentSignInIp = currentSignInIp;
	}

	public Date getLastSignInAt() {
		return lastSignInAt;
	}

	public void setLastSignInAt(Date lastSignInAt) {
		this.lastSignInAt = lastSignInAt;
	}

	public String getLastSignInIp() {
		return lastSignInIp;
	}

	public void setLastSignInIp(String lastSignInIp) {
		this.lastSignInIp = lastSignInIp;
	}

	public Date getRememberCreatedAt() {
		return rememberCreatedAt;
	}

	public void setRememberCreatedAt(Date rememberCreatedAt) {
		this.rememberCreatedAt = rememberCreatedAt;
	}

	public Integer getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(Integer signInCount) {
		this.signInCount = signInCount;
	}

	public Date getResetTokenExpiration() {
		Calendar expiration = Calendar.getInstance();
		expiration.setTime(resetTokenSentAt);
		expiration.roll(Calendar.DATE, 1);
		return expiration.getTime();
	}

	public static String encryptPassword(String password) {
		// TODO salt
		return DigestUtils.sha256Hex(password);
	}
}
