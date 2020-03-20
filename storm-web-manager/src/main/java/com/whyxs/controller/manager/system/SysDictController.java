package com.whyxs.controller.manager.system;

import com.whyxs.controller.BaseController;
import com.whyxs.service.system.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dict")
public class SysDictController extends BaseController {
	
	@Autowired
	private SysDictService dictService;

	@ResponseBody
	@RequestMapping("/getAreaListByParent")
	public List<Map<String,Object>> getAreaListByParent(String id) {
		return dictService.getAreaListByParent(id);
	}
}
