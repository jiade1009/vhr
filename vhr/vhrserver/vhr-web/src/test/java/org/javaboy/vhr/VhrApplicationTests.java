package org.javaboy.vhr;

//import org.junit.Test;
//import org.junit.runner.RunWith;

import org.javaboy.vhr.model.DatabaseType;
import org.javaboy.vhr.model.Menu;
import org.javaboy.vhr.pythonutil.ExecPython;
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
    @Autowired
    ExecPython execPython;

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

    @Test
    public void runPython() {
        execPython.runPython(new String[]{"load_a_stock"});
    }

    @Test
    public void down_weekly_line(){
        execPython.runPython(new String[]{"load_a_weekly_line", "20220930", "20220915"});
    }

}
