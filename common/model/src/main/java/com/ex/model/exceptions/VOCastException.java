package com.ex.model.exceptions;

/**  
* @ClassName: VOCastException  
* @Description:  
*/
public class VOCastException extends RuntimeException{
	
	private static final long serialVersionUID = -6346277842171392068L;

	public VOCastException() {
		super();
	}

	public VOCastException(String message, Throwable cause) {
		super(message, cause);
	}

	public VOCastException(String message) {
		super(message);
	}

	public VOCastException(Throwable cause) {
		super(cause);
	}

}
