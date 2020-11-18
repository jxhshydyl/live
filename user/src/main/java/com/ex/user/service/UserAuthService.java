package com.ex.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.UserAuth;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserAuthDTO;

/**
 * 服务接口
 *
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
 */
public interface UserAuthService extends IService<UserAuth> {

    ResultVO getAuth(Long userId);

    ResultVO submitAuth(UserAuthDTO userAuthDTO);

}
