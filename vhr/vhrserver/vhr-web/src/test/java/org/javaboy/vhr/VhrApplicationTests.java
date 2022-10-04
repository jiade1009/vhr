package org.javaboy.vhr;

//import org.junit.Test;
//import org.junit.runner.RunWith;

import org.javaboy.vhr.model.DatabaseType;
import org.javaboy.vhr.model.Menu;
import org.javaboy.vhr.service.DatabaseTypeService;
import org.javaboy.vhr.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class VhrApplicationTests {

    @Autowired
    MenuService menuService;
    @Autowired
    DatabaseTypeService databaseTypeService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getAllMenus() {
        List<Menu> menus =  menuService.getAllMenusWithRole();
        System.out.println(menus.size());
    }

    @Test
    public void getAllDatabasetypes() {
        List<DatabaseType> beanlist =  databaseTypeService.getAllBeanlist("");
        System.out.println(beanlist.size());
    }

}
