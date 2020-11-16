package com.ex.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.User;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.MessageDTO;
import com.ex.user.model.dto.UserDTO;
import com.ex.user.model.dto.UserLoginCodeDTO;
import com.ex.user.model.dto.UserLoginDTO;

/**
 * 服务接口
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
public interface UserService extends IService<User> {

    ResultVO sendMessage(boolean isLogin, MessageDTO messageDTO);

    ResultVO register(UserDTO userDTO);

    ResultVO loginByPassWord(UserLoginDTO userLoginDTO);

    ResultVO loginByCode(UserLoginCodeDTO userLoginCodeDTO);

    Integer updateUserById(User user);
}
