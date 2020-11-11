package com.ex.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.message.Message;
import com.ex.model.entity.user.User;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.MessageDTO;
import com.ex.user.model.dto.UserDTO;

/**
 * 服务接口
 *
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
 */
public interface UserService extends IService<User> {

    ResultVO sendMessage(MessageDTO messageDTO);

    ResultVO register(UserDTO userDTO);

}
