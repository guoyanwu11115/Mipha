package com.miphalink.unity.project.system.user.service;

import com.miphalink.unity.project.system.user.domain.Userinfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    Userinfo getByNameAndPassword (String name, String password);
}
