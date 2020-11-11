package com.ex.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.UserAuth;
import com.ex.user.mapper.UserAuthMapper;
import com.ex.user.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 *
 * @author jxhshydyl
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

}