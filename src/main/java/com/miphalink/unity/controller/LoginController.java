package com.miphalink.unity.controller;


import com.miphalink.unity.base.AjaxResult;
import com.miphalink.unity.domain.Userinfo;
import com.miphalink.unity.service.UserService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;


//    @RequestMapping("")
//    public Object login(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        Map map = new HashMap();
//        System.out.println("login:"+name+"|"+password);
//        if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password)){
//            Userinfo userinfo = userService.getByNameAndPassword(name,password);
//            if(userinfo!=null){
//                request.getSession().setAttribute("user",userinfo);
//                return AjaxResult.success();
//            }
//
//        }
//        request.getSession().setAttribute("name",name);
//        request.getSession().setAttribute("password",password);
//
//        return AjaxResult.error();
//    }

    /**
     * json响应
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @RequestMapping("login")
    public void loginByJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        JSONObject jsonObject = JSONObject.fromObject(AjaxResult.error());
        if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password)){
            Userinfo userinfo = userService.getByNameAndPassword(name,password);
            if(userinfo!=null){
                request.getSession().setAttribute("user",userinfo);
                jsonObject = JSONObject.fromObject(AjaxResult.success());
            }

        }
        //System.out.println(jsonObject);
        request.getSession().setAttribute("name",name);
        request.getSession().setAttribute("password",password);
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
    }

    /**
     * 驱动连接数据库
     * @param request
     * @param response
     * @throws IOException
     * @throws SQLException
     */
    @RequestMapping("")
    public void loginConnOracle(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password)) {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection
                        ("jdbc:mysql://127.0.0.1:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true", "root", "136535");
                conn.setAutoCommit(false);
                stat = conn.createStatement();
                //
                rs = stat.executeQuery("select u.login_name,u.password from sys_user u where  u.login_name = '" + name +"'");
                System.out.println(rs);
                while (rs.next()) {
                    String login_name = rs.getString("login_name");
                    String pwd = rs.getString("password");
                    System.out.println(login_name + "/" + pwd);
                }
                request.getSession().setAttribute("name", name);
                request.getSession().setAttribute("password", password);
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            conn.commit();
            if (rs!=null) rs.close();
            if (stat!=null) stat.close();
            if (conn!=null) conn.close();
        }

    }




}
