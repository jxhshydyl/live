package com.ex.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.UserCollection;
import com.ex.user.mapper.UserCollectionMapper;
import com.ex.user.service.UserCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户收藏列表服务接口实现
 *
 * @since 2020-11-11 18:24:23
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {

}