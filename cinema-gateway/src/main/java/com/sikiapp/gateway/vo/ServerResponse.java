package com.sikiapp.gateway.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @title: ServerResponse
 * @package com.sikiapp.common
 * @description: 服务端基础响应
 * @author: Robert
 * @date: 2019/3/23 下午3:19
 * @version: V1.0
*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

	private static final int SUCCESS = 0;
	private static final int ERROR = -1;
	
	/**
	 * 响应状态码
	 */
	private int code;

	/**
	 * 消息
	 */
	private String msg;

	/**
	 * 数据体body
	 */
	private T data;

	private ServerResponse(int code) {
		this.code = code;
	}

	private ServerResponse(int code, T data) {
		this.code = code;
		this.data = data;
	}

	private ServerResponse(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	private ServerResponse(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getcode() {
		return code;
	}

	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	public static <T> ServerResponse<T> createByCode(int code) {
		return new ServerResponse<T>(code);
	}

	public static <T> ServerResponse<T> createBySuccess() {
		return new ServerResponse<T>(SUCCESS);
	}

	public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
		return new ServerResponse<T>(SUCCESS, msg);
	}

	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<T>(SUCCESS, "成功", data);
	}

	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		return new ServerResponse<T>(SUCCESS, msg, data);
	}

	public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
		return new ServerResponse<T>(ERROR, errorMessage);
	}

	public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new ServerResponse<T>(errorCode, errorMessage);
	}


}
