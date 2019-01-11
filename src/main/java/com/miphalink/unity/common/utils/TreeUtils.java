package com.miphalink.unity.common.utils;

import com.miphalink.unity.domain.Menu;

import java.util.*;

/**
 * 权限处理类
 */
public class TreeUtils {
    /**
     *
     * @param list
     * @param parentId
     * @return
     */
    public static List getChildMenu(List<Menu> list,int parentId) {
        //转换map集合
        List mapList = menuList2MapList(list);
        List parentMenu = new ArrayList();
        for (Iterator iterator = list.iterator();iterator.hasNext();){
            Menu menu = (Menu) iterator.next();
            if(parentId == menu.getParentId()){
                //将一级菜单转换为map
                Map map = new HashMap();
                map.put("menuId",menu.getMenuId());
                map.put("text", menu.getMenuName());
                map.put("iconCls", menu.getIcon());
                map.put("state", "");
                //递归处理
                recursion(list,map);
                parentMenu.add(map);
            }
        }
        return parentMenu;
    }

    /**
     * 递归获取子权限菜单
     * @param list
     * @param m
     */
    public static void recursion(List<Menu> list, Map m){
        //获取m子节点     map集合
        List childList = menuList2MapList(getChild(list,m));
        m.put("children",childList);
        for (Iterator iterator = childList.iterator();iterator.hasNext();){
            Map map = (Map)iterator.next();
            if (hasChild(list,map)){
                recursion(list,map);
            }
        }
    }

    /**
     * 获取子节点列表
     * @param list
     * @param parentMap
     * @return
     */
    public static List getChild(List<Menu> list,Map parentMap){
        int parentId = Integer.parseInt(parentMap.get("menuId").toString().trim());
        List childList = new ArrayList();
        for (Iterator iterator = list.iterator();iterator.hasNext();){
            Menu menu = (Menu) iterator.next();
            if(parentId == menu.getParentId()){
                //将menu转换map
                childList.add(menu);
            }
        }
        return childList;
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<Menu> list, Map map)
    {
        return getChild(list, map).size() > 0 ? true : false;
    }

    /**
     * 将menu转换map对象,转换data,用于前端sidemenu
     * @param list
     * @return
     */
    public static List menuList2MapList(List<Menu> list){
        List mapList = new ArrayList();
        for (Iterator iterator = list.iterator();iterator.hasNext();){
            Menu menu = (Menu) iterator.next();
            Map map = new HashMap();
            map.put("menuId",menu.getMenuId());   //menuId:menuId
            map.put("text", menu.getMenuName());  //  text:menuName
            map.put("iconCls", menu.getIcon());  //iconCls:icon
            //目录
            if("M".equals(menu.getMenuType())){
                //一级菜单
                if (0 == menu.getParentId()){
                    map.put("state", "");  //目录状态：open,closed
                }
                if (menu.haschild()){
                    map.put("children", menuList2MapList(menu.getChildren())); //递归。。子节点
                }
            }
            mapList.add(map);
        }
        return mapList;
    }
}
