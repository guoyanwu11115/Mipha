package com.miphalink.unity.project.system.user.service.impl;

import com.miphalink.unity.project.system.user.dao.UserMapper;
import com.miphalink.unity.project.system.user.domain.Userinfo;
import com.miphalink.unity.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public Userinfo getByNameAndPassword(String name, String password) {
        Map<String,String> map = new HashMap();
        map.put("name",name);
        map.put("password",password);
        return userDao.getByNameAndPassword(map);
    }
}
