package com.alwold.classwatch.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	@OneToMany

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
	
	public static String encryptPassword(String password) {
		// TODO salt
		return DigestUtils.sha256Hex(password);
	}
}
