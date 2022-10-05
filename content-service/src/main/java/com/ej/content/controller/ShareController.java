package com.ej.content.controller;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.alibaba.fastjson.JSONObject;
import com.ej.content.auth.CheckAuthorization;
import com.ej.content.domain.dto.ShareAuditDto;
import com.ej.content.domain.dto.ShareDto;
import com.ej.content.domain.entity.Share;
import com.ej.content.domain.entity.User;
import com.ej.content.openfeign.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.ej.content.common.ResponseResult;
import com.ej.content.service.ShareService;

/**
 * @description:
 * @author: ej
 * @create: 2022-09-06
 **/

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {

    private final ShareService shareService;

    private final UserService userService;

//    @GetMapping("/all")
//    public ResponseResult getAllShares() {
//        return ResponseResult.success(shareService.findAll());
//    }

    // 分页查询
    @GetMapping("all")
    public  ResponseResult getAllShares(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue =  "6") Integer size,
            @RequestParam(defaultValue = "") String status
    ){
        return  ResponseResult.success(shareService.getAll(page,size,status));
    }

    @GetMapping("{id}")
    ResponseResult getShareById(@PathVariable Integer id) {
        Share share = shareService.findById(id);
        Integer userId = share.getUserId();
        ResponseResult res = userService.getUser(userId);
        String jsonString = JSONObject.toJSONString(res.getData());
        JSONObject obj = JSONObject.parseObject(jsonString);
        User user = JSONObject.toJavaObject(obj, User.class);
        ShareDto shareDTO = ShareDto.builder().share(share).nickname(user.getNickname()).avatar(user.getAvatar()).build();
        return ResponseResult.success(shareDTO);
    }

    @PostMapping("/check")
    @CheckAuthorization("admin")
    ResponseResult auditContent(@RequestBody ShareAuditDto shareAuditDto) throws IllegalAccessException {
        return ResponseResult.success(shareService.auditShare(shareAuditDto));
    }
}
