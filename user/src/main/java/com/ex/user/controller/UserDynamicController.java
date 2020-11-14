package com.ex.user.controller;

import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.MessageDTO;
import com.ex.user.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务控制器
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:23
 */
@Slf4j
@RestController
@RequestMapping("user/api/{device}/{version}")
public class UserDynamicController {

    /**
     * TODO: 2020/11/14  发文字动态
     * @param messageDTO
     * @return
     */
    @PostMapping("/release/dynamic")
    public ResultVO sendMessage(MessageDTO messageDTO) {
       return null;
    }

    /**
     * 发布视频动态
     * @param userDTO
     * @return
     */
    @PostMapping("/release/video")
    public ResultVO register(UserDTO userDTO) {
        return null;
    }

}