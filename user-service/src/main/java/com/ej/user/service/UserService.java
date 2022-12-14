package com.ej.user.service;

import com.ej.user.domain.dto.UserDto;
import com.ej.user.domain.entity.User;

import java.util.Map;

/**
 * @description:
 * @author: ej
 * @create: 2022-09-24
 **/

public interface UserService {
    /**
     * 根据id找user
     * @param id id
     * @return user
     */
    User findById(Integer id);

    /**
     * 登录
     * @param userDto userDto
     * @return user
     */
    Map login(UserDto userDto);
}
