package com.whyxs.service.system;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.whyxs.common.bean.entity.SysRole;
import com.whyxs.common.bean.entity.SysUser;

public interface SysRoleService extends IService<SysRole> {
	
	List<SysRole> selectRoleByUserId(String userId);
	
	void save(SysRole role);
	
	void batchInsterRoleMenu(String roleId,List<String> menuIds);
	
}
