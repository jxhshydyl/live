/**    
* @Title: EnumMessageType.java  
* @Package com.yijiu.eotc.mgs.enums  
* @Description:   
* @author vDalf   2020年4月17日 下午3:13:06
*/
package com.ex.model.enums.message;

import java.util.stream.Stream;


/**  
* @ClassName: EnumMessageType  
* @Description:  
* @author vDalf  2020年4月17日 下午3:13:06
*/
public enum EnumMessageType {
	
	//消息类型（0短信，1邮箱）
	自动(-1,"按顺序自动分辨"),
	手机短信(1,"手机短信"),
	电子邮件(2,"电子邮件"),
	;
	
	private Integer code;
	private String message;
	
	EnumMessageType(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public static EnumMessageType getByCode(Integer code) {
		return Stream.of(EnumMessageType.values()).filter(t->t.code.equals(code)).findAny().orElse(null);
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
