package com.miphalink.unity.project.java.serverpush;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@Controller
@RequestMapping(value = "/serverPush")
public class NobleMetalController {


    @RequestMapping(value = "/needPrice")
    public void nobleMetalRight(HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        Random r = new Random();
        try {
            PrintWriter pw = response.getWriter();
            int i = 0;
            while(i<10){
                if(pw.checkError()){
                    System.out.println("客户端断开连接");
                    return ;
                }
                Thread.sleep(1000);
                pw.write(makeResp(r));
                pw.flush();
                i++;
            }
            System.out.println("达到阈值，结束发送");
            pw.write("data:stop\n\n");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {

        }
    }


    /**
     * 业务方法，生成贵金属的实时价格
     * @param random
     * @return
     */
    private String makeResp(Random random) {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("data:")
                .append((random.nextInt(100)+50)+",")
                .append((random.nextInt(40)+35))
                .append("\n\n");

        return stringBuilder.toString();
    }




    @RequestMapping(value = "",produces = "text/event-stream;charset=UTF-8" )
    @ResponseBody
    public String nobleMetal() {
        System.out.println("----->nobleMetal");
        Random r = new Random();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return makeResp(r);
    }


}
