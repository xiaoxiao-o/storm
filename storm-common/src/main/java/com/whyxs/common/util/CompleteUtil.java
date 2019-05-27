package com.whyxs.common.util;

import java.lang.reflect.Method;
import java.util.Date;

public class CompleteUtil {

	
	public static void initCreateInfo(Object object) {
		try {
			Class<? extends Object> clazz = object.getClass();
			
			Method  setCreateTime = clazz.getMethod("setCreateTime",Date.class);
			Method  setCreateBy   = clazz.getMethod("setCreateBy",String.class);
			
			setCreateTime.invoke(object, new Date());
			setCreateBy.invoke(object, ShiroUtil.getShiroUser().getUsername());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
