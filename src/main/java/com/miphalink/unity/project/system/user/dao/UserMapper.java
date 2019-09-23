package com.miphalink.unity.project.system.user.dao;


import com.miphalink.unity.project.system.user.domain.Userinfo;

import java.util.Map;

public interface UserMapper {

    //根据name/passowrd 查询用户user
    Userinfo getByNameAndPassword(Map<String, String> map) ;
}
