package com.miphalink.unity.project.java.serverpush;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ajax 短连接获取系统时间
 *
 */
@Controller
@RequestMapping("/serverPush")
public class SystemTimeController {

    /**
     * 跳转到ServerPush首页
     * @return
     */
    @RequestMapping("/view")
    public String toServerPushMain() {

        return "/java/serverPush";
    }

    @RequestMapping("/systemTime")
    @ResponseBody
    public String getSystemTime() {
        System.out.println("--->systemTime");
        Date date = new Date();
        String systemTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return systemTime;
    }


}
