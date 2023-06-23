package org.javaboy.vhr;

//import org.junit.Test;
//import org.junit.runner.RunWith;

import org.javaboy.vhr.model.DatabaseType;
import org.javaboy.vhr.model.Menu;
import org.javaboy.vhr.model.StockMessageConf;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.DatabaseTypeService;
import org.javaboy.vhr.service.MenuService;
import org.javaboy.vhr.service.StockMessageConfService;
import org.javaboy.vhr.task.AStockCoreTask;
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
    StockMessageConfService stockMessageConfService;
    @Autowired
    ExecPython execPython;
    @Autowired
    AStockCoreTask stockCoreTask;

    @Test
    public void contextLoads() {
    }

//    @Test
    public void getAllMenus() {
        List<Menu> menus =  menuService.getAllMenusWithRole();
        System.out.println(menus.size());
    }

//    @Test
    public void getAllDatabasetypes() {
        List<DatabaseType> beanlist =  databaseTypeService.getAllBeanlist("");
        System.out.println(beanlist.size());
        List<DatabaseType> beanlist2 =  databaseTypeService.getAllBeanlist("");
    }

    @Test
    public void runPython() {
        execPython.runPython(new String[]{"create_u_weekly", "1"});
    }

//    @Test
    public void down_weekly_line(){
        execPython.runPython(new String[]{"load_a_weekly_line", "20220930", "20220915"});
    }

//    @Test
    public void test_cache() {
        List<StockMessageConf> list = stockMessageConfService.getListByStatus(true);
        for (StockMessageConf conf: list) {
            System.out.println("1. messagetype: " + conf.getMessageType());
        }

        List<StockMessageConf> list2 = stockMessageConfService.getListByStatus(true);
        for (StockMessageConf conf: list2) {
            System.out.println("2. messagetype: " + conf.getMessageType());
        }
        StockMessageConf vo = stockMessageConfService.selectByPrimaryKey(1942);
        System.out.println("3. messagetype: " + vo.getMessageType());
        vo = stockMessageConfService.selectByPrimaryKey(1942);
        System.out.println("4. messagetype: " + vo.getMessageType());
        vo.setMessageType(vo.getMessageType());
        stockMessageConfService.updateByPrimaryKeySelective(vo);
        vo = stockMessageConfService.selectByPrimaryKey(1942);
        System.out.println("5. messagetype: " + vo.getMessageType());

        list2 = stockMessageConfService.getListByStatus(true);
        for (StockMessageConf conf: list2) {
            System.out.println("6. messagetype: " + conf.getMessageType());
        }
    }

//    @Test
    public void test_configuration() {
        stockCoreTask.weekly();
    }
}
