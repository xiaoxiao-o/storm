package com.whyxs.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whyxs.common.bean.entity.SysMenu;

public interface SysMenuMapper extends BaseMapper<SysMenu>{

	List<SysMenu> selectMenusByUserIdAndPid(@Param("userId")String userId,@Param("pid")String pid);
	
	List<String> selectMenuIdsByRoleId(@Param("roleId")String roleId);
	
}
