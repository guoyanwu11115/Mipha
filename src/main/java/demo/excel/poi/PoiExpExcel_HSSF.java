package demo.excel.poi;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PoiExpExcel_HSSF {
    private static String[] headRow = new String[]{"债券代码","买入净价","卖出净价"};

    public static void main(String[] args) {
        //1.创建工作簿HSSFWorkbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建工作表HSSFSheet
        HSSFSheet sheet = workbook.createSheet();
        //3.创建首行HSSFRow
        HSSFRow row = sheet.createRow(0);
        //写入首行数据HSSFCell
        for (int i=0;i<headRow.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headRow[i]);
        }
        //4.写入数据
        for(int r=1;r<=500;r++){
            HSSFRow rowData = sheet.createRow(r); //行
            for(int c=0;c<headRow.length;c++){
                HSSFCell cellData = rowData.createCell(c); //列数据
                if(c==0)
                    cellData.setCellValue("zqdm"+r);
                if(c==1)
                    cellData.setCellValue("mrjj"+r);
                if(c==2)
                    cellData.setCellValue("mcjj"+r);
            }
        }
        //5.创建Excel文件
        File file = new File("d:/poi_test.xls");
        FileOutputStream fos = null;
        try {
            file.createNewFile();
        //6.流写入
            fos = FileUtils.openOutputStream(file);
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        //7.关闭流
        }finally {
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (workbook!=null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
