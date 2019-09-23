package com.miphalink.unity.project.system.user.dao;

import com.miphalink.unity.project.system.user.domain.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> selectMenuNormalAll();

    int deleteByPrimaryKey(Integer menuId);

    Menu selectByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}