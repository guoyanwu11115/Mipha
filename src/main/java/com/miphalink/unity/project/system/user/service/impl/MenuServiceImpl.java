package com.miphalink.unity.project.system.user.service.impl;

import com.miphalink.unity.common.utils.TreeUtils;
import com.miphalink.unity.project.system.user.dao.MenuMapper;
import com.miphalink.unity.project.system.user.domain.Menu;
import com.miphalink.unity.project.system.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    public List getMenuByUser() {
        List<Menu> menuList = new ArrayList<Menu>();
        menuList = menuMapper.selectMenuNormalAll();
        //根据parentId获取权限集合
        return TreeUtils.getChildMenu(menuList,0);
    }

}
