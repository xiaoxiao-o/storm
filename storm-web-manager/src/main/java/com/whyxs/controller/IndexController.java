package com.whyxs.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whyxs.common.bean.vo.TreeMenuVo;
import com.whyxs.service.system.SysMenuService;

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping(value= {"/","/index"})
	public String index(Model model) {
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/selectTreeMenu")
	public List<TreeMenuVo> selectTreeMenu() {
		return sysMenuService.selectTreeMenu(currentUser());
	}
	
	@RequiresPermissions({"home"})
	@RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}
	
	@RequiresPermissions({"monitor"})
	@RequestMapping("/monitor")
	public String monitor(Model model) {
		return "monitor";
	}
}
