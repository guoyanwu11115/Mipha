package com.miphalink.unity.dao;

import com.miphalink.unity.domain.Userinfo;

import java.util.Map;

public interface UserDao {

    //根据name/passowrd 查询用户user
    Userinfo getByNameAndPassword(Map<String,String> map) ;
}
