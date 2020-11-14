package com.ex.model.exceptions;

/**  
 * @ClassName: VersionChangedException  
 * @Description:  
 */
public class VersionChangedException extends RuntimeException{

	public VersionChangedException() {
        super();
    }
	
	public VersionChangedException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 4215452628453709832L;

}
