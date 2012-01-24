package com.alwold.classwatch.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="NOTIFIER_SETTING")
public class NotifierSetting {
	@EmbeddedId
	private NotifierSettingPk pk;
	@Column(name="ENABLED", nullable=false)
	private Boolean enabled;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public NotifierSettingPk getPk() {
		return pk;
	}

	public void setPk(NotifierSettingPk pk) {
		this.pk = pk;
	}
}
