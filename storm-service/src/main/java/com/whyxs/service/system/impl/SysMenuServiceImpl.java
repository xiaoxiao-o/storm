package com.whyxs.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.whyxs.common.bean.entity.SysMenu;
import com.whyxs.common.bean.entity.SysRole;
import com.whyxs.common.bean.entity.SysUser;
import com.whyxs.common.bean.vo.TreeMenu4AuthVo;
import com.whyxs.common.bean.vo.TreeMenuVo;
import com.whyxs.common.util.CompleteUtil;
import com.whyxs.common.util.UUIDUtil;
import com.whyxs.mapper.system.SysMenuMapper;
import com.whyxs.service.system.SysMenuService;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	@Autowired 
	private SysMenuMapper menuMapper;

	@Override
	public List<TreeMenuVo> selectTreeMenu(SysUser user) {
		return selectTreeMenuRecursion(user.getId(), "0");
	}
	
	/**
	 * 递归获取菜单（用户）
	 */
	public List<TreeMenuVo> selectTreeMenuRecursion(String userId,String pid) {
		List<SysMenu> sysMenus = menuMapper.selectMenusByUserIdAndPid(userId, pid);
		List<TreeMenuVo> treeMenuVos = new ArrayList<TreeMenuVo>();
		for (SysMenu sysMenu : sysMenus) {
			TreeMenuVo menuVo = new TreeMenuVo();
			menuVo.setName(prettifyLeftMenu(sysMenu.getMenuName()));
			menuVo.setHref(sysMenu.getUrl());
			menuVo.setIcon(sysMenu.getIcon());
			menuVo.setChildren(selectTreeMenuRecursion(userId,sysMenu.getId()));
			
			treeMenuVos.add(menuVo);
		}
		return treeMenuVos;
	}
	
	/**
	 * 美化左侧菜单
	 */
	public String prettifyLeftMenu(String menuName) {
		int length = menuName.length();
		switch (length) {
			case 2:menuName = StringUtils.join(menuName.split(""), "&emsp;&emsp;");break;
			case 3:menuName = StringUtils.join(menuName.split(""), "&ensp;");break;
			default:break;
		}
		return menuName;
	}
	
	@Override
	public List<TreeMenu4AuthVo> selectTreeMenu4Auth(SysRole role) {
		List<String> mIds = menuMapper.selectMenuIdsByRoleId(role.getId());
		List<SysMenu> menus = menuMapper.selectList(new EntityWrapper<SysMenu>().orderBy("sort"));
		List<TreeMenu4AuthVo> treeMenus = new ArrayList<TreeMenu4AuthVo>();
		for (SysMenu menu : menus) {
			TreeMenu4AuthVo menu4Auth= new TreeMenu4AuthVo();
			menu4Auth.setId(menu.getId());
			menu4Auth.setpId(menu.getPid());
			menu4Auth.setName(menu.getMenuName());
			menu4Auth.setChecked(mIds.contains(menu.getId()));
			menu4Auth.setChkDisabled("home".equals(menu.getResource()));//首页菜单设置不可选
			menu4Auth.setOpen(true);
			
			treeMenus.add(menu4Auth);
		}
		return treeMenus;
	}

	@Override
	public void save(SysMenu menu) {
		String menuId = menu.getId();
		//根据id更新或新增
		if(menuId != null && !"".equals(menuId)) {
			menuMapper.updateById(menu);
		}else {
			menu.setId(UUIDUtil.get32UUID());
			menu.setType(1);// 1:菜单,目前暂未考虑按钮的情况
			CompleteUtil.initCreateInfo(menu);
			menuMapper.insert(menu);
		}
		
	}
	
}
