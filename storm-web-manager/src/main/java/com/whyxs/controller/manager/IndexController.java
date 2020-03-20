package com.whyxs.controller.manager;

import com.whyxs.common.bean.vo.TreeMenuVo;
import com.whyxs.controller.BaseController;
import com.whyxs.service.system.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

	@RequiresPermissions({"swagger"})
	@RequestMapping("/swagger")
	public String swagger(Model model) {
		return "swagger";
	}
}
