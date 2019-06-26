package com.whyxs.common.bean.vo;

public class RestResultVo {
	
	public static final int SUCCESS_CODE = 200;
	public static final int ERROR_CODE = 500;

	public static final String SUCCESS_MSG = "请求成功";
	public static final String ERROR_MSG = "数据接口异常";

	
	private long code;
	
	private Object data;
	
	private String msg;

	public RestResultVo(long code, Object data, String msg) {
		super();
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public RestResultVo() {
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static RestResultVo build(long code, String msg, Object data) {
		return new RestResultVo(code, data, msg);
	}
	
	public static RestResultVo success(Object data) {
		return new RestResultVo(SUCCESS_CODE, data, SUCCESS_MSG);
	}
	
	public static RestResultVo error(Object data) {
		return new RestResultVo(ERROR_CODE, data, ERROR_MSG);
	}
	
	
}
