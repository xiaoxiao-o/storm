package com.whyxs.common.global;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 * @author yx  
 * @date 2019年4月19日
 */
@ControllerAdvice
public class GlobalExceptionAdvice {
	
	public static final Logger logger = Logger.getLogger(GlobalExceptionAdvice.class);
	
    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException  e,Model model) {
        logger.error("资源不存在,"+e.getMessage());
        model.addAttribute("error","资源不存在,"+e.getMessage());
        return "error/500";
        
    }
    
    
    /**
     * 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e,Model model) {
        logger.error("服务器异常,"+e.getMessage());
        model.addAttribute("error","服务器异常,"+e.getMessage());
        return "error/500";
    }
    
    /**
     * 403(由shiro抛出的异常,依然为服务器内部异常,状态码500)
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedException(Exception e,Model model) {
        logger.error("没有资源权限,"+e.getMessage());
        model.addAttribute("error","没有资源权限,"+e.getMessage());
        return "error/403";
    }
    
}
