package com.alwold.classwatch.school;

/**
 *
 * @author alwold
 */
public class ScrapeException extends RetrievalException {

	public ScrapeException() {
	}

	public ScrapeException(String string) {
		super(string);
	}

	public ScrapeException(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}

	public ScrapeException(Throwable thrwbl) {
		super(thrwbl);
	}
	
}
