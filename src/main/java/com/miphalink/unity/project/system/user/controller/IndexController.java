package com.miphalink.unity.project.system.user.controller;


import com.miphalink.unity.project.system.user.service.MenuService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private MenuService menuService;

    /**
     * 进入首页
     * @param request
     * @param response
     * @return
     */
    @GetMapping("index")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        return "main_sidemenu";
    }

    /**
     * 首页获取用户权限菜单
     * @param request
     * @param response
     */
    @RequestMapping(value = "menus",method = RequestMethod.GET)
    public void menus(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("menus"+request.getCharacterEncoding()+"|"+response.getCharacterEncoding());
        List menus = menuService.getMenuByUser();
        JSONArray json = JSONArray.fromObject(menus);
        System.out.println(json);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
