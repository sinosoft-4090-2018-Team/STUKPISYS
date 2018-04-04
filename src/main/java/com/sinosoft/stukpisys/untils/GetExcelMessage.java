package com.sinosoft.stukpisys.untils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetExcelMessage {
    public static List<List<Object>> getExcel(String filePath){
        //filePath="H:\\新建 Microsoft Excel 工作表.xlsx";
        System.out.println(filePath);
        List<List<Object>> obList=new ArrayList<List<Object>>();
        if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx"))
        {
            System.out.println("文件不是excel类型");
        }
        FileInputStream fis =null;
        Workbook wookbook = null;
        try
        {
            //获取一个绝对地址的流
            fis = new FileInputStream(filePath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            //2003以前版本的excel，用.xls结尾
            wookbook = new HSSFWorkbook(fis);//得到工作簿

        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
            try
            {
                //2007以后的版本excel，用.xlsx结尾
                fis = new FileInputStream(filePath);
                wookbook = new XSSFWorkbook(fis);//得到工作簿
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
        //获得表头
        Row rowHead = sheet.getRow(0);
        //判断表头是否正确
        if(rowHead.getPhysicalNumberOfCells() != 3)
        {
            System.out.println("表头的数量:"+rowHead.getPhysicalNumberOfCells());
        }

        System.out.println("表头的数量:"+rowHead.getPhysicalNumberOfCells());
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        int coloumNum=sheet.getRow(0).getPhysicalNumberOfCells();
        System.out.println("行数="+totalRowNum+"列数="+coloumNum);
        //获得所有数据

        //第一个表时i=1，，第二个表时i=0


        for(int i = 0 ; i <  totalRowNum+1; i++){
            Row row = sheet.getRow(i);
            List<Object> oList=new ArrayList<Object>();
            for(int j=0;j<coloumNum;j++){

                //获得获得第i行第0列的 String类型对象
                Cell cell = row.getCell((short)j);
                oList.add(String.valueOf(GetExcelMessage.getCellValue(cell)));
            }
            obList.add(oList);
        }
        return obList;
    }
    private static  Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());

                }else if("yyyy/mm;@".equals(cell.getCellStyle().getDataFormatString()) || "m/d/yy".equals(cell.getCellStyle().getDataFormatString())
                        || "yy/m/d".equals(cell.getCellStyle().getDataFormatString()) || "mm/dd/yy".equals(cell.getCellStyle().getDataFormatString())
                        || "dd-mm-yy".equals(cell.getCellStyle().getDataFormatString())|| "yyyy/m/d".equals(cell.getCellStyle().getDataFormatString())|| "yyyy.mm.dd".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    Date date = new Date();
                    date=cell.getDateCellValue();
                    value =sdf.format(date);;
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                FormulaEvaluator evaluator = cell.getRow().getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                value = evaluator.evaluate(cell).getNumberValue();// 读取计算结果 =SUM(M6:M15)
                break;
            default:
                break;
        }
        return value;
    }
    public static java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }
}
//事务返回之前的状态  @Transactional --完成
//tpye类型 分开写表头和表内容
//判断该阶段是否有此label 如果没有则插入。--完成
//修改label_index的生成方式--完成
//修改获得label_index的方法，根据labelname获得。  --完成
//完成导入测试数据
//
