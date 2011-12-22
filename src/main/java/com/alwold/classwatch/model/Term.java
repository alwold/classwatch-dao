package com.alwold.classwatch.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
	@EmbeddedId
	private TermPk pk;
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
		this.pk = new TermPk();
		this.pk.setCode(code);
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

	public TermPk getPk() {
		return pk;
	}

	public void setPk(TermPk pk) {
		this.pk = pk;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
