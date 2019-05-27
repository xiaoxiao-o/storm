package com.whyxs.controller.system;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whyxs.common.bean.entity.SysRole;
import com.whyxs.common.bean.entity.SysUser;
import com.whyxs.common.bean.vo.PageListVo;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.bean.vo.TreeMenu4AuthVo;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.controller.BaseController;
import com.whyxs.service.system.SysMenuService;
import com.whyxs.service.system.SysRoleService;
import com.whyxs.service.system.SysUserService;

@Controller
@RequestMapping("/system/role")
public class SysRoleController extends BaseController{
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
	private SysRoleService roleService;
	
	@Autowired
	private SysMenuService menuService;

	/**
	 * 角色页
	 */
	@RequiresPermissions({"role:list"})
    @RequestMapping("/list")  
    public String list(Model model){
    	return "system/role/roleList";
    }
    
    /**
     * 分页带参查询角色
     */
    @ResponseBody
    @RequestMapping("/selectListPage")  
    public PageListVo selectListPage(
    		@RequestParam(defaultValue="1")int page,
    		@RequestParam(defaultValue="10")int limit,
    		@RequestParam(required=false)String paramJson) {
    	try {
			Page<SysRole> pageResut = roleService.selectPage(this.getPage(page, limit), this.getEtityWrapper(paramJson));
			return PageListVo.success(pageResut.getTotal(), pageResut.getRecords());
		} catch (Exception e) {
			e.printStackTrace();
			return PageListVo.error();
		}
    }
    
    /**
     * 新增页
     */
    @RequestMapping("/toAdd")  
    public Object toAdd(Model model) {
		return "system/role/roleAdd";
    }
    
    /**
     * 编辑页
     */
    @RequestMapping("/toEdit")  
    public Object edit(String roleId,Model model) {
    	//角色信息
		SysRole role = roleService.selectById(roleId);
		model.addAttribute("role", role);
		return "system/role/roleEdit";
    }
    
    /**
     * 授权页
     */
    @RequestMapping("/toAuthorize")  
    public Object toAuthorize(String roleId,Model model) {
    	List<TreeMenu4AuthVo> treeMenus = menuService.selectTreeMenu4Auth(roleService.selectById(roleId));
    	model.addAttribute("roleId", roleId);
    	model.addAttribute("treeMenus", JSONUtil.toJSONString(treeMenus));
		return "system/role/authorize";
    }
    
    /**
     * 保存角色
     */
    @ResponseBody
    @RequestMapping("/save") 
    public RestResultVo save(String param) {
    	try {
			SysRole role = JSONUtil.parseObject(param, SysRole.class);
			roleService.save(role);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }
    
    
    
    /**
	 * 获取角色下的所有用户
	 */
	@RequestMapping("/selectUserByRoleId")  
	public String selectUserByRoleId(String roleId,Model model){
		model.addAttribute("users",userService.selectUserByRoleId(roleId));
		return "system/role/users";
	}
	
	/**
	 * 获取指定角色的用户数量
	 */
	@RequestMapping("/selectUserCountByRoleId")  
	@ResponseBody
	public RestResultVo selectUserCountByRoleId(String roleId){
		try {
			return RestResultVo.success(userService.selectUserCountByRoleId(roleId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RestResultVo.error(null);
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
    @RequestMapping("/delete")  
    public RestResultVo delete(String id) {
    	try {
			roleService.deleteById(id);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }
	
	/**
     * 授权
     */
	@ResponseBody
    @RequestMapping("/authorize")  
    public Object authorize(String roleId,String mIds,Model model) {
		try {
			List<String> menuIds = JSONUtil.parseArray(mIds, String.class);
			roleService.batchInsterRoleMenu(roleId, menuIds);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }
    
}
