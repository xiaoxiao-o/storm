package com.whyxs.controller.manager.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whyxs.common.bean.entity.SysUser;
import com.whyxs.common.bean.vo.PageListVo;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.constants.Constants;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.common.util.ShiroUtil;
import com.whyxs.controller.BaseController;
import com.whyxs.service.system.SysRoleService;
import com.whyxs.service.system.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController{
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
	private SysRoleService roleService;

	/**
	 * 用户页
	 */
	@RequiresPermissions({"user:list"})
    @RequestMapping("/list")  
    public String list(Model model){
    	return "system/user/userList";
    }
    
	/**
	 * 分页带参查询用户
	 */
    @ResponseBody
    @RequestMapping("/selectListPage")  
    public PageListVo selectListPage(
    		@RequestParam(defaultValue="1")int page,
    		@RequestParam(defaultValue="10")int limit,
    		@RequestParam(required=false)String paramJson) {
    	try {
			Page<SysUser> pageResut = userService.selectPage(this.getPage(page, limit), this.getEtityWrapper(paramJson));
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
    	//角色列表
    	model.addAttribute("roles", roleService.selectList(null));
		return "system/user/userAdd";
    }
    
	/**
	 * 编辑页
	 */
    @RequestMapping("/toEdit")  
    public Object edit(String userId,Model model) {
    	//角色列表
    	model.addAttribute("roles", roleService.selectList(null));
		//用户信息
		SysUser user = userService.selectById(userId);
		model.addAttribute("user", user);
		//用户的的角色列表
		model.addAttribute("userRoles", roleService.selectRoleByUserId(user.getId()));
		return "system/user/userEdit";
    }
    
	/**
	 * 保存用户
	 */
    @ResponseBody
    @RequestMapping("/save") 
    public RestResultVo save(String param) {
    	try {
			SysUser user = JSONUtil.parseObject(param, SysUser.class);
			List<String> roleIds = JSONUtil.parseObject(JSONUtil.parseObject(param).get("roleId").toString(), List.class);
			userService.save(user, roleIds);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }
    
	/**
	 * 校验用户名是否可用
	 */
    @ResponseBody
    @RequestMapping("/checkUsername") 
    public RestResultVo checkUsername(String username) {
    	List<SysUser> users = userService.selectList(new EntityWrapper<SysUser>().eq("username", username));
    	if(users==null || users.size()==0) {
    		return RestResultVo.success(true);
    	}
    	return RestResultVo.success(false);
    }
    
	/**
	 * 批量删除（批量）,单一删除也使用该方法
	 */
    @ResponseBody
    @RequestMapping("/batchDel")  
    public RestResultVo batchDel(String ids) {
    	try {
			List<String> listIds = JSONUtil.parseArray(ids, String.class);
			userService.deleteBatchIds(listIds);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }
    
    /**
	 * 重置密码
	 */
    @ResponseBody
    @RequestMapping("/resetPassword")  
    public RestResultVo resetPassword(String userId) {
    	try {
    		SysUser user = userService.selectById(userId);
    		user.setPassword(ShiroUtil.md51024Pwd(Constants.ORIGINAL_PASSWORD, user.getUsername()));
    		userService.updateById(user);
			return RestResultVo.success(Constants.ORIGINAL_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }
    
}
