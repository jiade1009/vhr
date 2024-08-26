package org.javaboy.vhr.utils;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.model.util.AiOrderProcess;
import org.javaboy.vhr.model.util.AiStrategyType;
import org.javaboy.vhr.model.util.ProfitStage;
import org.javaboy.vhr.model.util.TradeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class POIUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(POIUtils.class);

    public static ResponseEntity<byte[]> employee2Excel(List<Employee> list) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("员工信息");
        //文档管理员
        docInfo.setManager("javaboy");
        //设置公司信息
        docInfo.setCompany("www.javaboy.org");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("员工信息表");
        //文档作者
        summInfo.setAuthor("javaboy");
        // 文档备注
        summInfo.setComments("本文档由 javaboy 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("员工信息表");
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 12 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(12, 16 * 256);
        sheet.setColumnWidth(13, 14 * 256);
        sheet.setColumnWidth(14, 14 * 256);
        sheet.setColumnWidth(15, 12 * 256);
        sheet.setColumnWidth(16, 8 * 256);
        sheet.setColumnWidth(17, 20 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        sheet.setColumnWidth(20, 8 * 256);
        sheet.setColumnWidth(21, 25 * 256);
        sheet.setColumnWidth(22, 14 * 256);
        sheet.setColumnWidth(23, 15 * 256);
        sheet.setColumnWidth(24, 15 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("姓名");
        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("工号");
        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("性别");
        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("出生日期");
        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("身份证号码");
        HSSFCell c6 = r0.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("婚姻状况");
        HSSFCell c7 = r0.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("民族");
        HSSFCell c8 = r0.createCell(8);
        c8.setCellStyle(headerStyle);
        c8.setCellValue("籍贯");
        HSSFCell c9 = r0.createCell(9);
        c9.setCellStyle(headerStyle);
        c9.setCellValue("政治面貌");
        HSSFCell c10 = r0.createCell(10);
        c10.setCellStyle(headerStyle);
        c10.setCellValue("电话号码");
        HSSFCell c11 = r0.createCell(11);
        c11.setCellStyle(headerStyle);
        c11.setCellValue("联系地址");
        HSSFCell c12 = r0.createCell(12);
        c12.setCellStyle(headerStyle);
        c12.setCellValue("所属部门");
        HSSFCell c13 = r0.createCell(13);
        c13.setCellStyle(headerStyle);
        c13.setCellValue("职称");
        HSSFCell c14 = r0.createCell(14);
        c14.setCellStyle(headerStyle);
        c14.setCellValue("职位");
        HSSFCell c15 = r0.createCell(15);
        c15.setCellStyle(headerStyle);
        c15.setCellValue("聘用形式");
        HSSFCell c16 = r0.createCell(16);
        c16.setCellStyle(headerStyle);
        c16.setCellValue("最高学历");
        HSSFCell c17 = r0.createCell(17);
        c17.setCellStyle(headerStyle);
        c17.setCellValue("专业");
        HSSFCell c18 = r0.createCell(18);
        c18.setCellStyle(headerStyle);
        c18.setCellValue("毕业院校");
        HSSFCell c19 = r0.createCell(19);
        c19.setCellStyle(headerStyle);
        c19.setCellValue("入职日期");
        HSSFCell c20 = r0.createCell(20);
        c20.setCellStyle(headerStyle);
        c20.setCellValue("在职状态");
        HSSFCell c21 = r0.createCell(21);
        c21.setCellStyle(headerStyle);
        c21.setCellValue("邮箱");
        HSSFCell c22 = r0.createCell(22);
        c22.setCellStyle(headerStyle);
        c22.setCellValue("合同期限(年)");
        HSSFCell c23 = r0.createCell(23);
        c23.setCellStyle(headerStyle);
        c23.setCellValue("合同起始日期");
        HSSFCell c24 = r0.createCell(24);
        c24.setCellStyle(headerStyle);
        c24.setCellValue("合同终止日期");
        for (int i = 0; i < list.size(); i++) {
            Employee emp = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1).setCellValue(emp.getName());
            row.createCell(2).setCellValue(emp.getWorkID());
            row.createCell(3).setCellValue(emp.getGender());
            HSSFCell cell4 = row.createCell(4);
            cell4.setCellStyle(dateCellStyle);
            cell4.setCellValue(emp.getBirthday());
            row.createCell(5).setCellValue(emp.getIdCard());
            row.createCell(6).setCellValue(emp.getWedlock());
            row.createCell(7).setCellValue(emp.getNation().getName());
            row.createCell(8).setCellValue(emp.getNativePlace());
            row.createCell(9).setCellValue(emp.getPoliticsstatus().getName());
            row.createCell(10).setCellValue(emp.getPhone());
            row.createCell(11).setCellValue(emp.getAddress());
            row.createCell(12).setCellValue(emp.getDepartment().getName());
            row.createCell(13).setCellValue(emp.getJobLevel().getName());
            row.createCell(14).setCellValue(emp.getPosition().getName());
            row.createCell(15).setCellValue(emp.getEngageForm());
            row.createCell(16).setCellValue(emp.getTiptopDegree());
            row.createCell(17).setCellValue(emp.getSpecialty());
            row.createCell(18).setCellValue(emp.getSchool());
            HSSFCell cell19 = row.createCell(19);
            cell19.setCellStyle(dateCellStyle);
            cell19.setCellValue(emp.getBeginDate());
            row.createCell(20).setCellValue(emp.getWorkState());
            row.createCell(21).setCellValue(emp.getEmail());
            row.createCell(22).setCellValue(emp.getContractTerm());
            HSSFCell cell23 = row.createCell(23);
            cell23.setCellStyle(dateCellStyle);
            cell23.setCellValue(emp.getBeginContract());
            HSSFCell cell24 = row.createCell(24);
            cell24.setCellStyle(dateCellStyle);
            cell24.setCellValue(emp.getEndContract());
            HSSFCell cell25 = row.createCell(25);
            cell25.setCellStyle(dateCellStyle);
            cell25.setCellValue(emp.getConversionTime());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("员工表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    /**
     * Excel 解析成 员工数据集合
     *
     * @param file
     * @param allNations
     * @param allPoliticsstatus
     * @param allDepartments
     * @param allPositions
     * @param allJobLevels
     * @return
     */
    public static List<Employee> excel2Employee(MultipartFile file, List<Nation> allNations, List<Politicsstatus> allPoliticsstatus, List<Department> allDepartments, List<Position> allPositions, List<JobLevel> allJobLevels) {
        List<Employee> list = new ArrayList<>();
        Employee employee = null;
        try {
            //1. 创建一个 workbook 对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    employee = new Employee();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellType()) {
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 1:
                                        employee.setName(cellValue);
                                        break;
                                    case 2:
                                        employee.setWorkID(cellValue);
                                        break;
                                    case 3:
                                        employee.setGender(cellValue);
                                        break;
                                    case 5:
                                        employee.setIdCard(cellValue);
                                        break;
                                    case 6:
                                        employee.setWedlock(cellValue);
                                        break;
                                    case 7:
                                        int nationIndex = allNations.indexOf(new Nation(cellValue));
                                        employee.setNationId(allNations.get(nationIndex).getId());
                                        break;
                                    case 8:
                                        employee.setNativePlace(cellValue);
                                        break;
                                    case 9:
                                        int politicstatusIndex = allPoliticsstatus.indexOf(new Politicsstatus(cellValue));
                                        employee.setPoliticId(allPoliticsstatus.get(politicstatusIndex).getId());
                                        break;
                                    case 10:
                                        employee.setPhone(cellValue);
                                        break;
                                    case 11:
                                        employee.setAddress(cellValue);
                                        break;
                                    case 12:
                                        int departmentIndex = allDepartments.indexOf(new Department(cellValue));
                                        employee.setDepartmentId(allDepartments.get(departmentIndex).getId());
                                        break;
                                    case 13:
                                        int jobLevelIndex = allJobLevels.indexOf(new JobLevel(cellValue));
                                        employee.setJobLevelId(allJobLevels.get(jobLevelIndex).getId());
                                        break;
                                    case 14:
                                        int positionIndex = allPositions.indexOf(new Position(cellValue));
                                        employee.setPosId(allPositions.get(positionIndex).getId());
                                        break;
                                    case 15:
                                        employee.setEngageForm(cellValue);
                                        break;
                                    case 16:
                                        employee.setTiptopDegree(cellValue);
                                        break;
                                    case 17:
                                        employee.setSpecialty(cellValue);
                                        break;
                                    case 18:
                                        employee.setSchool(cellValue);
                                        break;
                                    case 20:
                                        employee.setWorkState(cellValue);
                                        break;
                                    case 21:
                                        employee.setEmail(cellValue);
                                        break;
                                }
                                break;
                            default: {
                                switch (k) {
                                    case 4:
                                        employee.setBirthday(cell.getDateCellValue());
                                        break;
                                    case 19:
                                        employee.setBeginDate(cell.getDateCellValue());
                                        break;
                                    case 23:
                                        employee.setBeginContract(cell.getDateCellValue());
                                        break;
                                    case 24:
                                        employee.setEndContract(cell.getDateCellValue());
                                        break;
                                    case 22:
                                        employee.setContractTerm(cell.getNumericCellValue());
                                        break;
                                    case 25:
                                        employee.setConversionTime(cell.getDateCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    list.add(employee);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List<StockAiOrder> excel2AiOrder(MultipartFile file, String orderSource) {
        List<StockAiOrder> list = new ArrayList<>();
        StockAiOrder vo = null;
        try {
            Workbook workbook = getSheets(file);
            //1. 创建一个 workbook 对象
//            int numberOfSheets = workbook.getNumberOfSheets();  //获取 workbook 中表单的数量
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            String searchString = "证券名称";
            int anchorRow = 0;
            int anchorCol = 0;
            boolean done = false;  // 是否找到searchString所对应的excel单元格坐标
            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;//防止数据中间有空行
                }
                int columns = row.getLastCellNum();
                for (int colIndex = 0; colIndex < columns; colIndex++) {
                    Cell cell = row.getCell(colIndex);
                    String cellValue = "";
                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue().trim();
                            break;
                        case NUMERIC:
                            // 如果需要处理数字类型的单元格，可以添加相应的逻辑
                            break;
                        case BOOLEAN:
                            // 如果需要处理布尔类型的单元格，可以添加相应的逻辑
                            break;
                        // 其他类型...
                        default:
                            break;
                    }
                    // 检查单元格值是否匹配搜索字符串
                    if (cellValue.equals(searchString)) {
                        System.out.println("找到匹配的单元格: Sheet 1, Row " + (rowIndex + 1) + ", Column " + (colIndex + 1));
                        done = true;
                        anchorRow = rowIndex;
                        anchorCol = colIndex;
                        break;
                    }
                }
                if (done) break;
            }
            if (!done) {
                LOGGER.warn("未找到对应的'证券名称'单元格，无法分析处理该文件的智能订单");
                return null;
            } else {
                // 开始分析智能订单
                for (int rowIndex = anchorRow + 1; rowIndex < rows; rowIndex++) {

                    Row row = sheet.getRow(rowIndex);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    vo = new StockAiOrder();
                    vo.setOrderSource(orderSource);
                    Cell cell = row.getCell(anchorCol);
                    if (cell == null || cell.getCellType() == CellType.BLANK) {
                        continue;
                    } else {
                        vo.setName(cell.getStringCellValue().trim());
                    }

                    for (int colIndex = anchorCol+1; colIndex < anchorCol+14; colIndex++) {
                        cell = row.getCell(colIndex);
//                        System.out.println("colIndex:"+colIndex+" rowIndex："+ rowIndex);
                        switch (cell.getCellType()) {
                            case STRING:
                                String val = cell.getStringCellValue().trim();
                                switch (colIndex) {
                                    case 3:
                                        vo.setCode(val);
                                        break;
                                    case 4:
                                        vo.setStrategyType(AiStrategyType.getIndex(val));
                                        break;
                                    case 5:
                                        vo.setStatus(val.equals("监控中")?1:0);
                                        break;
                                    case 6:
                                        vo.setProcess(AiOrderProcess.getIndex(val));
                                        break;
                                    case 8:
                                        vo.setTriggerCondition(val);
                                        break;
                                    case 9:
                                        vo.setPriceEntrust(val);
                                        break;
                                    case 10:
                                        vo.setAmountEntrust(val);
                                        break;
                                    case 11:
                                        vo.setAutoEntrust(val.equals("是")?1:0);
                                        break;
                                    case 12:
                                        vo.setDateBegin(DateUtils.parseDate(val));
                                        break;
                                    case 13:
                                        vo.setDateExpire(DateUtils.parseDate(val));
                                        break;
                                    case 14:
                                        vo.setOrderNo(val);
                                        break;
                                    case 15:
                                        vo.setSourceNote(val);
                                        break;
                                }
                                break;
                            default: {
                                switch (colIndex) {
                                    case 12:
                                        vo.setDateBegin(cell.getDateCellValue());
                                        break;
                                    case 13:
                                        vo.setDateExpire(cell.getDateCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    list.add(vo);
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("==================");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("=========2222=========");
            return null;
        }
    }


    /**
     * 将量化回测的股票收益结果导出到excel文件
     * @param list
     * @param buyRule
     * @param sellRule
     * @return
     */
    public static ResponseEntity<byte[]> stockQtProfitHold2Excel(List<StockQtProfitHold> list, StockQtBuyRule buyRule,
                                                                 StockQtSellRule sellRule) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("股票盈亏");
        //文档管理员
        docInfo.setManager("ghk");
        //设置公司信息
        docInfo.setCompany("www.ghk.com");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("股票盈亏表");
        //文档作者
        summInfo.setAuthor("ghk");
        // 文档备注
        summInfo.setComments("本文档由 ghk 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("股票盈亏表");
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 10 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 10 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 8 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("代码");
        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("名称");
        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("成本");
        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("盈亏值");
        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("盈亏率");
        HSSFCell c6 = r0.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("购买时间");
        HSSFCell c7 = r0.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("清仓时间");
        HSSFCell c8 = r0.createCell(8);
        c8.setCellStyle(headerStyle);
        c8.setCellValue("持股天数");
        HSSFCell c9 = r0.createCell(9);
        c9.setCellStyle(headerStyle);
        c9.setCellValue("加入方式");
        for (int i = 0; i < list.size(); i++) {
            StockQtProfitHold profitHold = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(profitHold.getId());
            row.createCell(1).setCellValue(profitHold.getCode());
            row.createCell(2).setCellValue(profitHold.getStockBasicInfo().getName());
            row.createCell(3).setCellValue(profitHold.getTotalBegin());
            row.createCell(4).setCellValue(profitHold.getProfit());

            row.createCell(5).setCellValue(String.format("%.2f", profitHold.getProfitRate()*100) + "%");
            HSSFCell cell6 = row.createCell(6);
            cell6.setCellStyle(dateCellStyle);
            cell6.setCellValue(profitHold.getTimeBuy());
            HSSFCell cell7 = row.createCell(7);
            cell7.setCellStyle(dateCellStyle);
            cell7.setCellValue(profitHold.getTimeSell());
            row.createCell(8).setCellValue(profitHold.getHoldDays());
            row.createCell(9).setCellValue(profitHold.getStockQtHold().getGenerateType()==0?"直接":"回头草");
        }

        // 加入对应的买入和卖出策略参数
        // (第一行："买入策略规则"，第二行："买入策略标题"，第三行："买入策略参数值）
        // (第四行："卖出策略规则"，第五行："卖出策略标题"，第六行："卖出策略参数值）
        HSSFSheet rule_sheet = workbook.createSheet("买卖策略表");
        rule_sheet.setDefaultColumnWidth(15*256);
//        rule_sheet.setColumnWidth(0, 15 * 256);
//        rule_sheet.setColumnWidth(1, 15 * 256);
//        rule_sheet.setColumnWidth(2, 15 * 256);
//        rule_sheet.setColumnWidth(3, 15 * 256);
//        rule_sheet.setColumnWidth(4, 15 * 256);
//        rule_sheet.setColumnWidth(5, 15 * 256);
//        rule_sheet.setColumnWidth(6, 15 * 256);
//        rule_sheet.setColumnWidth(7, 15 * 256);
//        rule_sheet.setColumnWidth(8, 15 * 256);
//        rule_sheet.setColumnWidth(9, 15 * 256);
//        rule_sheet.setColumnWidth(10,15 * 256);

        HSSFRow rule_r0 = rule_sheet.createRow(0);
        HSSFCell rule_c0 = rule_r0.createCell(0);
        rule_c0.setCellValue("买入策略规则");
        rule_c0.setCellStyle(headerStyle);

        HSSFRow rule_r3 = rule_sheet.createRow(3);
        HSSFCell rule_c3 = rule_r3.createCell(0);
        rule_c3.setCellValue("卖出策略规则");
        rule_c3.setCellStyle(headerStyle);

        // 买入策略标题栏
        HSSFRow rule_r1 = rule_sheet.createRow(1);
        HSSFCell rule_r1_c0 = rule_r1.createCell(0);
        //卖出策略标题栏
        HSSFRow rule_r4 = rule_sheet.createRow(4);
        HSSFCell rule_r4_c0 = rule_r4.createCell(0);

        if (buyRule != null) { //添加买入策略内容
            rule_r1_c0.setCellValue("上市时长");
            HSSFCell rule_r1_c1 = rule_r1.createCell(1);
            rule_r1_c1.setCellValue("开启");
            HSSFCell rule_r1_c2 = rule_r1.createCell(2);
            rule_r1_c2.setCellValue("策略周期");
            HSSFCell rule_r1_c3 = rule_r1.createCell(3);
            rule_r1_c3.setCellValue("成交金额比例");
            HSSFCell rule_r1_c4 = rule_r1.createCell(4);
            rule_r1_c4.setCellValue("开启");
            HSSFCell rule_r1_c5 = rule_r1.createCell(5);
            rule_r1_c5.setCellValue("收敛度");
            HSSFCell rule_r1_c6 = rule_r1.createCell(6);
            rule_r1_c6.setCellValue("开启");
            HSSFCell rule_r1_c7 = rule_r1.createCell(7);
            rule_r1_c7.setCellValue("下跌幅度");
            HSSFCell rule_r1_c8 = rule_r1.createCell(8);
            rule_r1_c8.setCellValue("开启");
            HSSFCell rule_r1_c9 = rule_r1.createCell(9);
            rule_r1_c9.setCellValue("买入价限价");
            HSSFCell rule_r1_c10 = rule_r1.createCell(10);
            rule_r1_c10.setCellValue("开启");

            //填充内容
            HSSFRow rule_r2 = rule_sheet.createRow(2);
            HSSFCell rule_r2_c0 = rule_r2.createCell(0);
            rule_r2_c0.setCellValue(buyRule.getTimeMarket());
            HSSFCell rule_r2_c1 = rule_r2.createCell(1);
            rule_r2_c1.setCellValue(buyRule.getTimeMarketOption()?"是":"否");
            HSSFCell rule_r2_c2 = rule_r2.createCell(2);
            rule_r2_c2.setCellValue(buyRule.getRulePeriod());
            HSSFCell rule_r2_c3 = rule_r2.createCell(3);
            rule_r2_c3.setCellValue(buyRule.getTurnoverLimit());
            HSSFCell rule_r2_c4 = rule_r2.createCell(4);
            rule_r2_c4.setCellValue(buyRule.getTurnoverLimitOption()?"是":"否");
            HSSFCell rule_r2_c5 = rule_r2.createCell(5);
            rule_r2_c5.setCellValue(buyRule.getConverLimit());
            HSSFCell rule_r2_c6 = rule_r2.createCell(6);
            rule_r2_c6.setCellValue(buyRule.getConverLimitOption()?"是":"否");
            HSSFCell rule_r2_c7 = rule_r2.createCell(7);
            rule_r2_c7.setCellValue(buyRule.getShockLimit());
            HSSFCell rule_r2_c8 = rule_r2.createCell(8);
            rule_r2_c8.setCellValue(buyRule.getShockLimitOption()?"是":"否");
            HSSFCell rule_r2_c9 = rule_r2.createCell(9);
            rule_r2_c9.setCellValue(buyRule.getBuyPriceLimit());
            HSSFCell rule_r2_c10 = rule_r2.createCell(10);
            rule_r2_c10.setCellValue(buyRule.getBuyPriceLimitOption()?"是":"否");
        } else {
            rule_r1_c0.setCellValue("暂无");
        }

        if (sellRule != null) {
            rule_r4_c0.setCellValue("第一次止盈卖出比例");
            HSSFCell rule_r4_c1 = rule_r4.createCell(1);
            rule_r4_c1.setCellValue("止盈1比例");
            HSSFCell rule_r4_c2 = rule_r4.createCell(2);
            rule_r4_c2.setCellValue("止盈2比例");
            HSSFCell rule_r4_c3 = rule_r4.createCell(3);
            rule_r4_c3.setCellValue("止盈3比例");
            HSSFCell rule_r4_c4 = rule_r4.createCell(4);
            rule_r4_c4.setCellValue("止盈4比例");
            HSSFCell rule_r4_c5 = rule_r4.createCell(5);
            rule_r4_c5.setCellValue("止盈5比例");
            HSSFCell rule_r4_c6 = rule_r4.createCell(6);
            rule_r4_c6.setCellValue("止盈卖出1比例");
            HSSFCell rule_r4_c7 = rule_r4.createCell(7);
            rule_r4_c7.setCellValue("止盈卖出2比例");
            HSSFCell rule_r4_c8 = rule_r4.createCell(8);
            rule_r4_c8.setCellValue("止盈卖出3比例");
            HSSFCell rule_r4_c9 = rule_r4.createCell(9);
            rule_r4_c9.setCellValue("止盈卖出4比例");

            //填充内容
            HSSFRow rule_r5 = rule_sheet.createRow(5);
            HSSFCell rule_r5_c0 = rule_r5.createCell(0);
            rule_r5_c0.setCellValue(sellRule.getSellRatio());
            HSSFCell rule_r5_c1 = rule_r5.createCell(1);
            rule_r5_c1.setCellValue(sellRule.getP1Ratio());
            HSSFCell rule_r5_c2 = rule_r5.createCell(2);
            rule_r5_c2.setCellValue(sellRule.getP2Ratio());
            HSSFCell rule_r5_c3 = rule_r5.createCell(3);
            rule_r5_c3.setCellValue(sellRule.getP3Ratio());
            HSSFCell rule_r5_c4 = rule_r5.createCell(4);
            rule_r5_c4.setCellValue(sellRule.getP4Ratio());
            HSSFCell rule_r5_c5 = rule_r5.createCell(5);
            rule_r5_c5.setCellValue(sellRule.getP5Ratio());
            HSSFCell rule_r5_c6 = rule_r5.createCell(6);
            rule_r5_c6.setCellValue(sellRule.getSp1Ratio());
            HSSFCell rule_r5_c7 = rule_r5.createCell(7);
            rule_r5_c7.setCellValue(sellRule.getSp2Ratio());
            HSSFCell rule_r5_c8 = rule_r5.createCell(8);
            rule_r5_c8.setCellValue(sellRule.getSp3Ratio());
            HSSFCell rule_r5_c9 = rule_r5.createCell(9);
            rule_r5_c9.setCellValue(sellRule.getSp4Ratio());
        } else {
            rule_r4_c0.setCellValue("暂无");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment",
                    new String("盈亏表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static List<StockSubstepProfit> excel2SubstepProfit(MultipartFile file) {
        List<StockSubstepProfit> list = new ArrayList<>();
        try {
            Workbook workbook = getSheets(file);
            //1. 创建一个 workbook 对象
            // int numberOfSheets = workbook.getNumberOfSheets();  //获取 workbook 中表单的数量
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            // 初始化为标题栏：代码	名称	止损价 成本价 成本股票数 可用股票 分级股票数	止盈阶段	最新操作
            for (int rowIndex = 1; rowIndex <= rows; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;//防止数据中间有空行
                }
                StockSubstepProfit vo = new StockSubstepProfit();
                Cell cell = row.getCell(0);
                if (cell.getStringCellValue() == null) {
                    continue;
                }
                vo.setCode(getText(cell));
                cell = row.getCell(1);
                vo.setName(cell.getStringCellValue().trim());

                double priceStopLoss = row.getCell(2).getNumericCellValue(); //止损价
                vo.setPriceStopLoss(NumberUtils.ceil(priceStopLoss, 2));
                cell = row.getCell(3);
                vo.setPriceCost(NumberUtils.ceil(cell.getNumericCellValue(), 2));
                cell = row.getCell(4);
                vo.setAmountCost(Double.valueOf(cell.getNumericCellValue()).intValue());
                cell = row.getCell(5);
                vo.setAmountAble(Double.valueOf(cell.getNumericCellValue()).intValue());
                cell = row.getCell(6);
                vo.setAmountSubstep(Double.valueOf(cell.getNumericCellValue()).intValue());
                cell = row.getCell(7);
                vo.setProfitStage(ProfitStage.getIndex(cell.getStringCellValue().trim().toUpperCase()));
                cell = row.getCell(8);
                vo.setLastTradeType(TradeType.getIndex(cell.getStringCellValue().trim()));

                SubstepUtils.updateSubstepPrice(vo);

                list.add(vo);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Workbook getSheets(MultipartFile file) throws IOException {
        Workbook workbook;
        String fileName = file.getOriginalFilename();
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else if (fileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            throw new IllegalArgumentException("Invalid file type. Only .xls and .xlsx are supported.");
        }
        return workbook;
    }

    private static String getText(Cell cell) {
        switch (cell.getCellType()) {
            case NUMERIC:
                // 如果需要处理数字类型的单元格，可以添加相应的逻辑
                return String.valueOf((int)cell.getNumericCellValue());
            // 其他类型...
            default:
                return cell.getStringCellValue().trim();
        }
    }
}
