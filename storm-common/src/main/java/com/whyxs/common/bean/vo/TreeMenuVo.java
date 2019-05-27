package com.whyxs.common.bean.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
  *  树菜单 zTree3
 * @author yx  
 * @date 2019年4月18日
 */
public class TreeMenuVo {

	//节点名称
	private String name;
	
	//节点链接（可选），未设则不会跳转
	private String href;
	
	//子菜单
	private List<TreeMenuVo> children = new ArrayList<TreeMenuVo>();
	
	private String icon;		//图标
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<TreeMenuVo> getChildren() {
		return children;
	}

	public void setChildren(List<TreeMenuVo> children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
