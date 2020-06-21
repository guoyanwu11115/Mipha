package com.miphalink.unity.project.java.excelImpExp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/excelImpExp")
public class ExcelController {

    /**
     * Excel首页
     * @return
     * @throws Throwable
     */
    @RequestMapping("/view.html")
    public  String toExcelView(){

        return "/java/excel/excelView";
    }
}
