package com.miphalink.unity.controller;

import com.miphalink.unity.project.system.user.domain.Userinfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class JsonController {
    @RequestMapping("studyJson")
    public void JavaAndJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        System.out.println("path:"+request.getServletPath());
        System.out.println("转换类型type:"+request.getContextPath());
        String path = request.getScheme()+"//"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        StringBuffer Url = request.getRequestURL();
        String Uri = request.getRequestURI();
        String servletPaht = request.getServletPath();

        PrintWriter out = response.getWriter();
        //1.List >> jsonArray
        List list = new ArrayList();
        list.add("CNDATA");
        list.add(5);
        list.add(new Userinfo(111111L,"zuzep","祖","zuzep"));
        list.add(new Userinfo(222222L,"mahaoxi","马","mahaoxi"));
        if ("java2json".equals(type)) {
            JSONArray jsonArray = JSONArray.fromObject(list);
            System.out.println(jsonArray);
            out.print(jsonArray);
        }
        //2 javaObj >> jsonObj
        Userinfo userinfo = new Userinfo(111111L,"mayun","马云","mayun");
        if ("javaObj2jsonObj".equals(type)) {
            JSONObject jsonObject = JSONObject.fromObject(userinfo);
            System.out.println(jsonObject);
            out.print(jsonObject);
        }
        //3 javaObj >> jsonList
        String jsonStr = request.getParameter("jsonStr");
        System.out.println("json字符串:"+jsonStr);
        if ("jsonObj2javaList".equals(type)) {
            JSONArray json = JSONArray.fromObject(jsonStr);
            List userList = (List) JSONArray.toCollection(json, Userinfo.class);
            if (!userList.isEmpty()) {
                for (Iterator itr = userList.iterator(); itr.hasNext(); ) {
                    Userinfo user = (Userinfo) itr.next();
                    System.out.println(user.toString());
                }
            }
        }
        //4 jsonObj >> javaObj
        if ("jsonObj2javaObj".equals(type)) {
            JSONObject json = JSONObject.fromObject(jsonStr);
            Userinfo user = (Userinfo) JSONObject.toBean(json,Userinfo.class);
            System.out.println(user.toString());
        }

    }
}
