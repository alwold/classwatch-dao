package com.alwold.classwatch.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="SCHOOL")
public class School implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="SCHOOL_ID")
	private Long id;
	@Column(name="NAME", length=255, nullable=false)
	private String name;
	@Column(name="PLUGIN_CLASS", length=255, nullable=false)
	private String pluginClass;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPluginClass() {
		return pluginClass;
	}

	public void setPluginClass(String pluginClass) {
		this.pluginClass = pluginClass;
	}
	
}
