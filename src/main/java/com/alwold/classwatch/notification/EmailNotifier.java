package com.alwold.classwatch.notification;

import com.alwold.classwatch.model.Course;
import com.alwold.classwatch.model.User;
import com.alwold.classwatch.school.ClassInfo;
import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author alwold
 */
public class EmailNotifier implements Notifier {
	private static Logger logger = Logger.getLogger(EmailNotifier.class);

	private MailSender mailSender;
	private SimpleMailMessage templateMessage;

	public void notify(User user, Course course, ClassInfo classInfo) throws NotificationException {
		SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
		msg.setTo(user.getEmail());
		msg.setText("Hello,\nThis is the friendly robot at Classwatch. Your class at "+
				course.getTerm().getSchool().getName()+", "+
				course.getCourseNumber()+" ("+classInfo.getName()+
				") is now available.  You should register as soon as possible.");
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			throw new NotificationException(ex);
		}
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public String getType() {
		return "EMAIL";
	}

	public String getDescription() {
		return "Email";
	}
}
