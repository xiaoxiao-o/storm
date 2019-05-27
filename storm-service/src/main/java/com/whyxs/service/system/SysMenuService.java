package com.whyxs.service.system;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.whyxs.common.bean.entity.SysMenu;
import com.whyxs.common.bean.entity.SysRole;
import com.whyxs.common.bean.entity.SysUser;
import com.whyxs.common.bean.vo.TreeMenu4AuthVo;
import com.whyxs.common.bean.vo.TreeMenuVo;

public interface SysMenuService extends IService<SysMenu> {

	List<TreeMenuVo> selectTreeMenu(SysUser user);

	List<TreeMenu4AuthVo> selectTreeMenu4Auth(SysRole role);
	
	void save (SysMenu menu);
}
