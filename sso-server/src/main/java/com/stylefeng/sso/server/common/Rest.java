package com.stylefeng.sso.server.common;


/**
 * 返回结果
 * @author han
 *
 */
public class Rest<T> {

	/**
	 * 返回码
	 * @author han
	 *
	 */
	public interface ResponseCode{
		/** 请求处理成功。 */
		public static final int OK = 200;

		/** 请求参数有误。 */
		public static final int BAD_REQUEST = 400;

		/** 身份认证失败。 */
		public static final int UNAUTHORIZED = 401;

		/** 资源不可用。 */
		public static final int FORBIDDEN = 403;

		/** 无法找到指定位置的资源。 */
		public static final int NOT_FOUND = 404;

		/** 请求资源和当前冲突。 */
		public static final int CONFLICT = 409;

		/** 请求处理失败，服务器异常。 */
		public static final int INTERNAL_SERVER_ERROR = 500;
	}
	/**
	 * 状态码
	 */
	private int code = 200;
	/**
	 * 状态信息
	 */
	private String message = "请求成功";
	/**
	 * 具体数据
	 */
	private T data;
	
	public int getCode() {
		return code;
	}
	
	public Rest<T> setCode(int code) {
		this.code = code;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Rest<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	public T getData() {
		return data;
	}
	
	public Rest<T> setData(T data) {
		this.data = data;
		return this;
	}
}

