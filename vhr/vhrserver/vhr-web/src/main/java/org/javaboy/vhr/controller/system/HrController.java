package org.javaboy.vhr.controller.system;

import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.Role;
import org.javaboy.vhr.service.HrService;
import org.javaboy.vhr.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-10-24 8:09
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HrController.class);
    final
    HrService hrService;
    final
    RoleService roleService;
    final String initPassword = "ghk";

    public HrController(HrService hrService, RoleService roleService) {
        this.hrService = hrService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords) {
        return hrService.getAllHrs(keywords);
    }

    @PostMapping("/")
    public RespBean addHr(@RequestBody Hr hr) {
//        SpringSecurity中的BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密。
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePass = encoder.encode(initPassword); //默认初始密码均为ghk
        hr.setPassword(encodePass);
        int result = hrService.addHr(hr);
        if (result == 1) {
            return RespBean.ok("添加成功", hr);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid, Integer[] rids) {
        if (hrService.updateHrRole(hrid, rids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Integer id) {
        if (hrService.deleteHrById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PutMapping("/reset/{id}")
    public RespBean resetPasswordById(@PathVariable Integer id) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(initPassword); //默认初始密码均为ghk

        if (hrService.resetPasswordById(id, encodePassword) == 1) {
            return RespBean.ok("重置密码成功!");
        }
        return RespBean.error("重置密码失败!");
    }
}
