package com.ex.model.exceptions;

/**  
* @ClassName: VoOperateNotFindException  
* @Description:  
*/
public class VoOperateNotFindException extends RuntimeException{
	
	private static final long serialVersionUID = -851836811909909942L;

	public VoOperateNotFindException() {
		super();
	}

	public VoOperateNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

	public VoOperateNotFindException(String message) {
		super(message);
	}

	public VoOperateNotFindException(Throwable cause) {
		super(cause);
	}

}
