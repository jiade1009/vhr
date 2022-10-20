package org.javaboy.vhr.controller;

/**
 * @author : sam
 * @ClassName : BaseController
 * @description : Controller基础类
 * @datetime : 2022年 10月 20日 17:03
 * @version: : 1.0
 */

import org.javaboy.vhr.model.RespBean;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @ModelAttribute
    public void preHandler(HttpServletRequest request){
//        request.setAttribute("ctx",request.getContextPath());
    }

    public RespBean success(){
        return RespBean.ok("添加成功");
    }

    public RespBean success(String msg){
        return RespBean.ok(msg);
    }

    public RespBean success(Object result){
        return RespBean.ok("添加成功", result);
    }
}
