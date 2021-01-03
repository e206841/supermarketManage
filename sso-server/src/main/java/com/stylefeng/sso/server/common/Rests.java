package com.stylefeng.sso.server.common;

public class Rests {
	
	public static <T> Rest<T> ok(T t){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.OK);
		result.setMessage("success");
		result.setData(t);
		return result; 
	}
	
	public static <T> Rest<T> ok(T t, String msg){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.OK);
		result.setMessage(msg);
		result.setData(t);
		return result; 
	}
	
	public static <T> Rest<T> ok(){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.OK);
		result.setMessage("success");
		return result; 
	}
	
	public static <T> Rest<T> notFound(){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.NOT_FOUND);
		result.setMessage("无法找到指定位置的资源");
		return result; 
	}
	
	public static <T> Rest<T> internalServerError(){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.INTERNAL_SERVER_ERROR);
		result.setMessage("请求处理失败，服务器异常");
		return result; 
	}

	public static <T> Rest<T> internalServerError(String msg){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.INTERNAL_SERVER_ERROR);
		result.setMessage(msg);
		return result; 
	}
	
	public static <T> Rest<T> unauthorized(){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.UNAUTHORIZED);
		result.setMessage("身份认证失败");
		return result; 
	}
	
	public static <T> Rest<T> unauthorized(String msg){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.UNAUTHORIZED);
		result.setMessage(msg);
		return result; 
	}
	
	public static <T> Rest<T> forbidden(String msg){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.FORBIDDEN);
		result.setMessage(msg);
		return result;
	}
	
	public static <T> Rest<T> badRequest(String msg){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.BAD_REQUEST);
		result.setMessage(msg);
		return result;
	}
	
	public static <T> Rest<T> conflict(String msg){
		Rest<T> result = new Rest<>();
		result.setCode(Rest.ResponseCode.CONFLICT);
		result.setMessage(msg);
		return result;
	}
	
}
