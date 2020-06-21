package demo.excel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;



public class CreateTemplate {

    public static void main(String[] args) {
        //获取解析xml文件路径
        String path = System.getProperty("user.dir")+"/src/main/java/demo/excel/batchQuote.xml";
        File file = new File(path);
        SAXBuilder builder = new SAXBuilder();
        try {
            //解析xml文件
            Document  parse = builder.build(file);
            //创建excel
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建sheet
            HSSFSheet sheet = workbook.createSheet("Sheet0");
            //获取xml文件根节点
            Element root = parse.getRootElement();
            //根节点“name”属性 - 模板名
            String rootName = root.getAttribute("name").getValue();
            //1.设置列宽colgroup
            Element colgroup = root.getChild("colgroup");
            setColumnWidth(sheet,colgroup);
            //2.设置标题title
            int rownum = 0;
            int column = 0;
            Element title = root.getChild("title");
            List<Element> trs = title.getChildren("tr");
            for (int i = 0; i <trs.size() ; i++) {
                Element tr = trs.get(i);
                List<Element> tds = tr.getChildren("td");
                //创建一行HSSFRow
                HSSFRow row = sheet.createRow(rownum); //默认从第 rownum=0 行开始
                //设置单元格样式
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER); //居中
                for (column = 0; column < tds.size(); column++) {
                    Element td = tds.get(column);
                    HSSFCell cell = row.createCell(column); //创建单元格HSSFCell
                    Attribute rowSpan = td.getAttribute("rowspan"); //合并行
                    Attribute colSpan = td.getAttribute("colspan"); //合并列
                    Attribute value = td.getAttribute("value"); //标题
                    if(value !=null){
                        String titleName = value.getValue();
                        cell.setCellValue(titleName);
                        int rspan = rowSpan.getIntValue()-1;
                        int cspan = colSpan.getIntValue()-1;
                        //设置字体
                        HSSFFont font = workbook.createFont();
                        font.setFontName("仿宋_GB3212"); //字体
                        font.setBold(true); //加粗
//                        font.setFontHeight((short)12); //几号字
                        font.setFontHeightInPoints((short)12);
                        cellStyle.setFont(font);
                        cell.setCellStyle(cellStyle);
                        //合并单元格
                        sheet.addMergedRegion(new CellRangeAddress(rspan,rspan,0,cspan));
                        //
                    }
                }
                rownum++;
            }
            //3.设置表头信息
            Element thead = root.getChild("thead");
            List<Element> trs_Thread = thead.getChildren("tr");
            for (int i = 0; i <trs_Thread.size() ; i++) {
                Element tr = trs_Thread.get(i);
                HSSFRow row = sheet.createRow(rownum); //创建行row
                List<Element> ths = tr.getChildren("th");
                for (column = 0; column <ths.size() ; column++) {
                    Element th = ths.get(column);
                    Attribute th_value = th.getAttribute("value");
                    HSSFCell cell = row.createCell(column); //创建单元格
                    if (th_value!=null){
                        cell.setCellValue(th_value.getValue());
                    }
                }
                rownum++;
            }
            //4.设置数据区域样式
            Element tbody = root.getChild("tbody");
            Element tr_tbody = tbody.getChild("tr");
            int repeat = tr_tbody.getAttribute("repeat").getIntValue();
            List<Element> tds = tr_tbody.getChildren("td");
            for (int i = 0; i <repeat ; i++) {
                HSSFRow row = sheet.createRow(rownum);
                for (column = 0; column < tds.size(); column++) {
                    Element td = tds.get(column);
                    HSSFCell cell  =row.createCell(column);
                    setType(workbook,cell,td);
                }
                rownum++;
            }
            //5.生成EXCEL导入模板
            File tempFile = new File("d:/"+rootName+".xls");
            if (tempFile.exists()) tempFile.delete();
            tempFile.createNewFile();
            FileOutputStream fos = FileUtils.openOutputStream(tempFile);
            workbook.write(fos);
            //6.关闭流
            fos.close();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }


    }

    /**
     * 设置单元格样式
     * @param workbook
     * @param cell
     * @param td
     */
    private static void setType(HSSFWorkbook workbook, HSSFCell cell, Element td) {
        Attribute typeAttr = td.getAttribute("type");
        String type = typeAttr.getValue();
        HSSFDataFormat format = workbook.createDataFormat(); //数据格式
        HSSFCellStyle cellStyle = workbook.createCellStyle();//单元格样式
        if("NUMERIC".equalsIgnoreCase(type)){
            cell.setCellType(CellType.NUMERIC);
            Attribute formatAttr = td.getAttribute("format");
            String formatValue = formatAttr.getValue();
            formatValue = StringUtils.isNotBlank(formatValue)?formatValue:"###,##0.00";
            cellStyle.setDataFormat(format.getFormat(formatValue)); //数字格式
        }else if("STRING".equalsIgnoreCase(type)){
            cell.setCellValue("zqdm");
            cell.setCellType(CellType.STRING);
            cellStyle.setDataFormat(format.getFormat("@"));//字符串格式
        }else if("DATE".equalsIgnoreCase(type)){
            cell.setCellType(CellType.NUMERIC);
            cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd")); //日期格式
        }else if("ENUM".equalsIgnoreCase(type)){
            //
            CellRangeAddressList regions = new CellRangeAddressList
                    (cell.getRowIndex(),cell.getRowIndex(),cell.getColumnIndex(),cell.getColumnIndex());
            Attribute enumAttr = td.getAttribute("format");
            String enumValue = enumAttr.getValue();
            //加载下拉列表内容
            DVConstraint constraint =
                    DVConstraint.createExplicitListConstraint(enumValue.split(","));
            //数据有效性对象
            HSSFDataValidation dataValidation = new HSSFDataValidation(regions,constraint);
            workbook.getSheetAt(0).addValidationData(dataValidation);
        }
        cell.setCellStyle(cellStyle); //设置样式
    }

    /**
     * 设置列宽
     * @auth
     * @param sheet
     * @param colgroup
     */
    private static void setColumnWidth(HSSFSheet sheet, Element colgroup) {
       List<Element> cols = colgroup.getChildren("col");
       for (int i=0;i<cols.size();i++){
           Element col = cols.get(i);
           Attribute width = col.getAttribute("width");
           String unit = width.getValue().replaceAll("[0-9,\\.]","");
           String value = width.getValue().replaceAll(unit,"");
           int v=0;
           if(StringUtils.isBlank(unit) || "px".equals(unit)){
               v = Math.round(Float.parseFloat(value) * 37F);
           }else if("em".equals(unit)){
               v = Math.round(Float.parseFloat(value) * 267.5F);
           }
           sheet.setColumnWidth(i,v);

       }


    }


}
