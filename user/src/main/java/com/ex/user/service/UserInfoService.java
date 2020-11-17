package com.ex.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.UserInfo;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserInfoDTO;

/**
 * 服务接口
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:23
 */
public interface UserInfoService extends IService<UserInfo> {

    ResultVO updateUserInfo(UserInfoDTO userInfoDTO);

}
