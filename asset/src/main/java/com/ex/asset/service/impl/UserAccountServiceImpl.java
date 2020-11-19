package com.ex.asset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.asset.mapper.UserAccountMapper;
import com.ex.asset.service.UserAccountService;
import com.ex.model.entity.asset.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户账户服务接口实现
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-19 16:11:11
 */
@Slf4j
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}