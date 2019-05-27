package com.whyxs.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whyxs.common.bean.entity.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole>{
	
	List<SysRole> selectRoleByUserId(@Param("userId")String userId);
	
	void deleteMenuIdByRoleId(@Param("roleId")String roleId);
	
	void batchInsterRoleMenu(@Param("roleId")String roleId,@Param("menuIds")List<String> menuIds);
	
	void insterRoleMenu(@Param("roleId")String roleId,@Param("menuId")String menuId);
}
