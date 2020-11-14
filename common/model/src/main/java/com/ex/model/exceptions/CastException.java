package com.ex.model.exceptions;

/**  
* @ClassName: CastException  
* @Description:  
*/
public class CastException extends RuntimeException{
	
	private static final long serialVersionUID = -3498285291927413519L;

	public CastException(){
        super();
    }

    public CastException(String message){
        super(message);
    }

    public CastException(String message, Throwable cause){
        super(message, cause);
    }

}
