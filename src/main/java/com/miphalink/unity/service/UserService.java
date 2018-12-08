package com.miphalink.unity.service;


import com.miphalink.unity.domain.Userinfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    Userinfo getByNameAndPassword(String name,String password);
}
