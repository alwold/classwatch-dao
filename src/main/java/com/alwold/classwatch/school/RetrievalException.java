package com.alwold.classwatch.school;

/**
 *
 * @author alwold
 */
public class RetrievalException extends Exception {

	public RetrievalException(Throwable thrwbl) {
		super(thrwbl);
	}

	public RetrievalException(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}

	public RetrievalException(String string) {
		super(string);
	}

	public RetrievalException() {
	}
	
}
