package com.miphalink.unity.dao;

import com.miphalink.unity.domain.Menu;

import java.util.List;

public interface MenuMapper {

    List<Menu> selectMenuNormalAll();

    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}