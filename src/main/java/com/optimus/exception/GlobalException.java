package com.optimus.exception;


import com.optimus.enums.GlobalEnum;

/**
 * 全局异常
 * @author DragonZhang
 */
public class GlobalException extends RuntimeException {
	private String errorCode;
	private String errorMessage;
	public GlobalException(String errorCode,String errorMsg){
		super(errorMsg);
		this.errorCode=errorCode;
		this.errorMessage=errorMsg;
	}
	public GlobalException(GlobalEnum globalEnum){
		this(globalEnum.getCode(),globalEnum.getMsg());
	}


}
