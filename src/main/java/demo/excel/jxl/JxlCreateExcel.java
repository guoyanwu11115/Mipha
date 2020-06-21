package demo.excel.jxl;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;

/**
 * 创建excel
 */
public class JxlCreateExcel {


    public static void main(String[] args) {
        String[] headLabel = new String[]{"债券代码","买入净价","卖出净价"};
        File fileJxl = new File("d:/jxl_test.xls");
        WritableWorkbook workbook = null;
        try {
            fileJxl.createNewFile();
            //1.创建工作簿 WritableWorkbook
            workbook = Workbook.createWorkbook(fileJxl);
            //2.创建工作表sheet
            WritableSheet sheet = workbook.createSheet("批量报价模板",0);
            Label label = null;
            //3.设置第一行表头 Label
            for(int i=0;i<headLabel.length;i++){
                label = new Label(i,0,headLabel[i]);
                sheet.addCell(label);
            }
            //4.追加数据
            for(int i=1;i<=3;i++){
                label = new Label(0,i,"zqdm"+i);
                sheet.addCell(label);
                label = new Label(1,i,"mrjj"+i);
                sheet.addCell(label);
                label = new Label(2,i,"mcjj"+i);
                sheet.addCell(label);
            }
            //5.workbook 流写入
            workbook.write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }finally {
            if (workbook!=null) {
                try {
                    //6.关闭workbook
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
