package com.miphalink.unity.controller;

import com.miphalink.unity.domain.Userinfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class JsonController {

    @RequestMapping("studyJson")
    public void JavaAndJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        System.out.println("转换类型type:"+type);
        PrintWriter out = response.getWriter();
        //1.List >> jsonArray
        List list = new ArrayList();
        list.add("CNDATA");
        list.add(5);
        list.add(new Userinfo(111111L,"zuzep","祖","zuzep"));
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

    }
}
