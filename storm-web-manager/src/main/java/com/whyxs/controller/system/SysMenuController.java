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
import com.whyxs.common.bean.entity.SysMenu;
import com.whyxs.common.bean.entity.SysUser;
import com.whyxs.common.bean.vo.PageListVo;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.controller.BaseController;
import com.whyxs.service.system.SysMenuService;
import com.whyxs.service.system.SysRoleService;
import com.whyxs.service.system.SysUserService;

@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController{
	
	@Autowired
	private SysMenuService menuService;

	/**
	 * 菜单页
	 */
	@RequiresPermissions({"menu:list"})
    @RequestMapping("/list")  
    public String list(Model model){
    	return "system/menu/menuList";
    }
    
	/**
	  * 分页带参查询用户
	 */
    @ResponseBody
    @RequestMapping("/selectList")  
    public PageListVo selectList() {
    	List<SysMenu> list = menuService.selectList(new EntityWrapper<SysMenu>().eq("type", "1").orderBy("sort"));
		return PageListVo.success(list.size(), list);
    }
    
    /**
	 * 新增页
	 */
    @RequestMapping("/toAdd")  
    public Object toAdd(Model model) {
    	model.addAttribute("menus", menuService.selectList(new EntityWrapper<SysMenu>().orderBy("sort")));
		return "system/menu/menuAdd";
    }
    
	/**
	 * 编辑页
	 */
    @RequestMapping("/toEdit")  
    public Object edit(String menuId,Model model) {
		//菜单
    	model.addAttribute("menu", menuService.selectById(menuId));
    	model.addAttribute("menus", menuService.selectList(new EntityWrapper<SysMenu>().orderBy("sort")));
		return "system/menu/menuEdit";
    }
    
    /**
	 * 保存菜单
	 */
    @ResponseBody
    @RequestMapping("/save") 
    public RestResultVo save(String param) {
    	try {
    		SysMenu menu = JSONUtil.parseObject(param, SysMenu.class);
    		menuService.save(menu);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }
    
    /**
	 * 删除菜单
	 */
    @ResponseBody
    @RequestMapping("/delete") 
    public RestResultVo delete(String menuId) {
    	try {
    		menuService.deleteById(menuId);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }
    
}
