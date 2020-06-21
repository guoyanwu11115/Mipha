package demo.excel.poi;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * 1997-2003版Excle.xls文件
 */
public class PoiReadExcel_HSSF {

    public static void main(String[] args) {
        //目标文件
//        File file = new File("d:/poi_test.xls");
        File file = new File("C:/Users/Administrator/Desktop/批量报价导入.xls");
        HSSFWorkbook workbook = null;
        try {
            //1.获取工作簿HSSFWorkbook
            workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
            //2.0获取工作表HSSFSheet 方法1：表名
            HSSFSheet sheet = workbook.getSheet("Sheet0");
            //2.1获取工作表HSSFSheet 方法1：次序
//            HSSFSheet sheet = workbook.getSheetAt(0);
            //3.读取数据
            for (int r=0;r<sheet.getLastRowNum();r++){ //遍历行
                HSSFRow row = sheet.getRow(r);
                for(int c=0;c<row.getLastCellNum();c++){ //遍历列
                    HSSFCell cell = row.getCell(c);
//                    System.out.print(cell.getStringCellValue()+"  ");
                    System.out.print(cell.getNumericCellValue()+"  ");
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
