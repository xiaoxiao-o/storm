package com.whyxs.common.bean.vo;

/**
 * layui中的异步表格数据
 * 格式：{"code": 0,"msg": "","count": 1000,"data": [{},{}]}
 * @author yx  
 * @date 2019年4月20日
 */
public class PageListVo {
	
	private static final Integer SUCCESS_CODE = 0;
	private static final Integer ERROR_CODE = 1;
	
	private static final String SUCCESS_MSG = "请求成功";
	private static final String ERROR_MSG = "数据接口异常";

	
	private Integer code;
	
	private String msg;
	
	private Integer count;
	
	private Object data;
	

	public PageListVo() {
	}

	public PageListVo(Integer code, String msg, Integer count, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static PageListVo success(Integer count, Object data) {
		return new PageListVo(SUCCESS_CODE, SUCCESS_MSG, count, data);
	}
	
	public static PageListVo error() {
		return new PageListVo(ERROR_CODE, ERROR_MSG,null,null);
	}
	
	public static PageListVo build(Integer code, String msg, Integer count, Object data) {
		return new PageListVo(code, msg, count, data);
	}
	
}
