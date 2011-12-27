package com.alwold.classwatch.notification;

/**
 *
 * @author alwold
 */
public class NotificationException extends Exception {

	public NotificationException(Throwable thrwbl) {
		super(thrwbl);
	}

	public NotificationException(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}

	public NotificationException(String string) {
		super(string);
	}

	public NotificationException() {
	}
	
}
