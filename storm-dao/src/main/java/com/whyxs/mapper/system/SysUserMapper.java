package com.whyxs.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whyxs.common.bean.entity.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser>{
	
	void BatchSaveUserRoleRel(List<Map<String,Object>> list);
	
	void deleteUserRoleRelByUserId(String userId);
	
	int selectUserCountByRoleId(@Param("roleId")String roleId);
	
	List<SysUser> selectUserByRoleId(@Param("roleId")String roleId);
	
	List<String> findResourceByUserId(@Param("userId")String userId);

}
