package com.alwold.classwatch.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="TERM")
public class Term implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TERM_ID")
	private Long id;
	@Column(name="TERM_CODE", length=10)
	private String code;
	@ManyToOne
	@JoinColumn(name="SCHOOL_ID")
	private School school;
	@Column(name="NAME", length=100, nullable=false)
	private String name;
	@Column(name="START_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Column(name="END_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	public Term() {}

	public Term(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
