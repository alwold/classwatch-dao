package com.alwold.classwatch.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	uniqueConstraints={@UniqueConstraint(columnNames={"EMAIL"})})
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private Long id;
	@Column(name="EMAIL",length=25,nullable=false)
	private String email;
	@Column(name="PASSWORD",length=1024,nullable=false)
	private String password;
	@Column(name="PHONE",length=25,nullable=true)
	private String phone;
	@Column(name="RESET_TOKEN", length=255, nullable=true)
	private String resetToken;
	@Column(name="RESET_TOKEN_EXPIRATION", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date resetTokenExpiration;

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

	public Date getResetTokenExpiration() {
		return resetTokenExpiration;
	}

	public void setResetTokenExpiration(Date resetTokenExpiration) {
		this.resetTokenExpiration = resetTokenExpiration;
	}
	
	public static String encryptPassword(String password) {
		// TODO salt
		return DigestUtils.sha256Hex(password);
	}
}
