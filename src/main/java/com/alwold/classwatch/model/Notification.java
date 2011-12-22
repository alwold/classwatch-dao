package com.alwold.classwatch.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author alwold
 */
@Entity
@Table(name="NOTIFICATION")
public class Notification implements Serializable {
	@EmbeddedId
	private NotificationPk pk;

	public NotificationPk getPk() {
		return pk;
	}

	public void setPk(NotificationPk pk) {
		this.pk = pk;
	}
	
}
