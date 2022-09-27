package com.ej.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ej.user.common.ResponseResult;
import com.ej.user.common.ResultCode;
import com.ej.user.domain.dto.UserDto;
import com.ej.user.domain.entity.User;
import com.ej.user.service.UserService;

/**
 * @description:
 * @author: syhan
 * @create: 2022-09-24
 **/

@RestController
@Slf4j
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    public ResponseResult getUserById(@PathVariable Integer id) {
        return ResponseResult.success(userService.findById(id));
    }

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody UserDto userDto) {
        User user = userService.login(userDto);
        if(user == null) {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        } else {
            return ResponseResult.success(user);
        }
    }
}
