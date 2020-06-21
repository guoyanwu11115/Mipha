package demo.excel.jxl;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

public class JxlReadExcel {

    public static void main(String[] args) {
        Workbook workbook = null;
        try {
            //获取Workbook
            workbook = Workbook.getWorkbook(new File("d:/jxl_test.xls"));
            //获取工作表sheet
            Sheet sheet = workbook.getSheet(0);
            //获取数据
            for(int r=0;r<sheet.getRows();r++){ //行
                for(int c=0;c<sheet.getColumns();c++){ //列
                    Cell cell = sheet.getCell(c,r);
                    System.out.print(cell.getContents()+"");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }finally {
            if (workbook!=null) workbook.close();
        }
    }
}
