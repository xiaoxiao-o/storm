package com.whyxs.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.whyxs.common.bean.entity.SysMenu;
import com.whyxs.common.bean.entity.SysRole;
import com.whyxs.common.util.CompleteUtil;
import com.whyxs.common.util.UUIDUtil;
import com.whyxs.mapper.system.SysMenuMapper;
import com.whyxs.mapper.system.SysRoleMapper;
import com.whyxs.service.system.SysRoleService;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired 
	private SysRoleMapper roleMapper;
	
	@Autowired 
	private SysMenuMapper menuMapper;

	@Override
	public List<SysRole> selectRoleByUserId(String userId) {
		return roleMapper.selectRoleByUserId(userId);
	}

	@Override
	public void save(SysRole role) {
		String roleId = role.getId();
		if (roleId == null) {
			role.setId(UUIDUtil.get32UUID());
			CompleteUtil.initCreateInfo(role);
			roleMapper.insert(role);
			//为该角色分配初始权限(首页)
			SysMenu menu = new SysMenu();
			menu.setMenuName("首页");
			roleMapper.insterRoleMenu(role.getId(), menuMapper.selectOne(menu).getId());
		}else {
			roleMapper.updateById(role);
		}
		
	}

	@Override
	public void batchInsterRoleMenu(String roleId, List<String> menuIds) {
		//删除原有的菜单权限
		roleMapper.deleteMenuIdByRoleId(roleId);
		//新增菜单权限权限
		roleMapper.batchInsterRoleMenu(roleId, menuIds);
	}

}
