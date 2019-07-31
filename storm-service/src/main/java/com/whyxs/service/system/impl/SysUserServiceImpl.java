package com.whyxs.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.whyxs.common.bean.entity.SysUser;
import com.whyxs.common.util.CompleteUtil;
import com.whyxs.common.util.ShiroUtil;
import com.whyxs.common.util.UUIDUtil;
import com.whyxs.mapper.system.SysUserMapper;
import com.whyxs.service.system.SysUserService;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired 
	private SysUserMapper userMapper;
	
	@Override
	public void save(SysUser user,List<String> roleIds) {
		String userId = user.getId();
		if("".equals(user.getPassword())) {				//用户密码，不填则不更新，填写要加密
			user.setPassword(null);
		}else {
			user.setPassword(ShiroUtil.md51024Pwd(user.getPassword(), user.getUsername()));
		}
		//根据id更新或新增用户
		if(userId != null && !"".equals(userId)) {
			userMapper.updateById(user);
			//删除原有关联角色
			userMapper.deleteUserRoleRelByUserId(userId);
		}else {
			userId = UUIDUtil.get32UUID();
			user.setId(userId);
			CompleteUtil.initCreateInfo(user);
			userMapper.insert(user);
		}
		//重置用户角色
		List<Map<String,Object>> userRoles = new ArrayList();
		for (String roleId : roleIds) {
			Map<String,Object> userRole = new HashMap();
			userRole.put("id", UUIDUtil.get32UUID());
			userRole.put("userId", userId);
			userRole.put("roleId", roleId);
			userRoles.add(userRole);
		}
		userMapper.BatchSaveUserRoleRel(userRoles);
	}

	@Override
	public int selectUserCountByRoleId(String roleId) {
		return userMapper.selectUserCountByRoleId(roleId);
	}

	@Override
	public List<SysUser> selectUserByRoleId(String roleId) {
		return userMapper.selectUserByRoleId(roleId);
	}

	@Override
	public Set<String> findResourceByUserId(String userId) {
		return new HashSet<String>(userMapper.findResourceByUserId(userId));
	}

}
