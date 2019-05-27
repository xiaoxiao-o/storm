package com.whyxs.service.system;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.whyxs.common.bean.entity.SysRole;
import com.whyxs.common.bean.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
	

	void save(SysUser user,List<String> roleIds);
	
	int selectUserCountByRoleId(String roleId);
	
	List<SysUser> selectUserByRoleId(String roleId);
	
	Set<String> findResourceByUserId(String userId);
	
}
