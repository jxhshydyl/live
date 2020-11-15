package com.ex.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.base.AuthConfig;
import com.ex.user.mapper.AuthConfigMapper;
import com.ex.user.service.AuthConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 等级权限配置表服务接口实现
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:57:41
 */
@Slf4j
@Service
public class AuthConfigServiceImpl extends ServiceImpl<AuthConfigMapper, AuthConfig> implements AuthConfigService {

}