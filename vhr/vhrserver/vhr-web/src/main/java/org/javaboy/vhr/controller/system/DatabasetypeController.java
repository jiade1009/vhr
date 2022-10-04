package org.javaboy.vhr.controller.system;

import org.javaboy.vhr.model.*;
import org.javaboy.vhr.service.DatabaseTypeService;
import org.javaboy.vhr.service.HrService;
import org.javaboy.vhr.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author sam
 * @ClassName DatabaseController
 * @description: 管理基础数据信息
 * @datetime 2022年 10月 04日 16:09
 * @version: 1.0
 */
@RestController
@RequestMapping("/system/databasetype")
public class DatabasetypeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabasetypeController.class);
    final DatabaseTypeService beanService;

    public DatabasetypeController(DatabaseTypeService beanService) {
        this.beanService = beanService;
    }

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size, @RequestParam String keyword) {
        return beanService.getBeanlistByPage(page, size, keyword);
    }

    @PostMapping("/")
    public RespBean addBean(@RequestBody DatabaseType bean) {
        int result = beanService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateBean(@RequestBody DatabaseType bean) {
        if (beanService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteBeanById(@PathVariable Integer id) {
        if (beanService.deleteByPrimaryKey(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    /**
     * 校验唯一性
     * @param type type=0，校验名称是否唯一，type=1，校验code是否唯一
     * @param value
     * @return
     */
    @GetMapping("/checkUnique")
    public RespBean checkUnique(@RequestParam Integer type, @RequestParam String value , @RequestParam String id ) {
        Long i = 0L;
        Integer pk = StringUtils.hasLength(id)&&!id.equalsIgnoreCase("null")?Integer.valueOf(id):null;
        if (type==0) {
            i = beanService.checkUniqueName(value, pk);
        } else {
            i = beanService.checkUniqueCode(value, pk);
        }
        if (i == 0) {
            return RespBean.ok("");
        } else {
            return RespBean.error("存在重复");
        }
    }
}
