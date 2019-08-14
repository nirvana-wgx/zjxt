package com.ctevs.common;

public abstract class Code {
	
	/**
	 * 成功
	 */
	public static final int SUCCESS = 1;

	/**
	 * session 超时或 未登录
	 */
	public static final int SESSION_TIME_OUT = 0;
	
	/**
	 * 服务端错误
	 */
	public static final int SERVER_ERR = -1;

	/**
	 * 错误ID
	 */
	public static final int ERR_ID = -2;
	
	/**
	 * 参数错误
	 */
	public static final int ERR_PARAM = -3;
	/**
	 * 操作失败
	 */
	public static final int ERR_OPERATION = -6;

}
