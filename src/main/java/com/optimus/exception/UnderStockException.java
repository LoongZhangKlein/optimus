package com.optimus.exception;

import com.optimus.enums.OrderEnum;

/**
 * 库存不足异常
 */
public class UnderStockException extends RuntimeException {
	private String errorCode;
	private String errorMessage;

	public UnderStockException(String message) {
		super(message);
	}

	public UnderStockException(String message, Throwable cause) {
		super(message, cause);
	}
	public UnderStockException(OrderEnum orderEnum) {
		super(String.valueOf(orderEnum.getCode()));
		this.errorCode=String.valueOf(orderEnum.getCode());
		this.errorMessage=orderEnum.getMsg();
	}
}
