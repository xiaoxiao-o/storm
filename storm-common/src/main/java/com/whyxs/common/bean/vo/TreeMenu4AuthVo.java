package com.whyxs.common.bean.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
  *  树菜单 zTree3
 * @author yx  
 * @date 2019年4月18日
 */
public class TreeMenu4AuthVo {

	
	private String id;
	
	private String pId;
	
	//节点名称
	private String name;
	
	//是否被选中
	private boolean checked; 
	
	//该节点不可选
	private boolean chkDisabled;
	
	//是否展开
	private boolean open;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public boolean isChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
