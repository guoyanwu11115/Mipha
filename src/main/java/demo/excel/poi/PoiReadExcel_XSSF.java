package demo.excel.poi;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * 2007以上版Excle.xls文件
 */
public class PoiReadExcel_XSSF {

    public static void main(String[] args) {
        //目标文件
        File file = new File("d:/poi_test.xlsx");
        XSSFWorkbook workbook = null;
        try {
            //1.获取工作簿XSSFWorkbook
            workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
            //2.0获取工作表XSSFSheet 方法1：表名
            XSSFSheet sheet = workbook.getSheet("Sheet0");
            //2.1获取工作表XSSFSheet 方法1：次序
//            XSSFSheet sheet = workbook.getSheetAt(0);
            //3.读取数据
            for (int r=0;r<sheet.getLastRowNum();r++){ //遍历行
                XSSFRow row = sheet.getRow(r);
                for(int c=0;c<row.getLastCellNum();c++){ //遍历列
                    XSSFCell cell = row.getCell(c);
                    System.out.print(cell.getStringCellValue()+"  ");
                }
                System.out.println("");


            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(workbook !=null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
