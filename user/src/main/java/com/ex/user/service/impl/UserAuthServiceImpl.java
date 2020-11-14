package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.User;
import com.ex.model.entity.user.UserAuth;
import com.ex.model.enums.ResultEnum;
import com.ex.model.enums.user.EnumUserAuthCardType;
import com.ex.model.enums.user.EnumUserAuthStatus;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserAuthMapper;
import com.ex.user.model.dto.UserAuthDTO;
import com.ex.user.service.UserAuthService;
import com.ex.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务接口实现
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
@Slf4j
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO submitAuth(UserAuthDTO userAuthDTO) {
        User user = userService.getById(userAuthDTO.getUserId());
        if (user.getRealAuth().equals(EnumUserAuthStatus.PASS.getCode())) {
            return Result.error(ResultEnum.USER_AUTH_PASS);
        }
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getCardNumber, userAuthDTO.getCardNumber()));
        if (userAuth != null) {
            return Result.error(ResultEnum.USER_CART_NUMBER_USED);
        }
        userAuth = new UserAuth();
        userAuth.setUserId(user.getId())
                .setUserName(user.getUserName())
                .setCardType(EnumUserAuthCardType.IDENTITY_CARD.getCode())
                .setCardNumber(userAuthDTO.getCardNumber())
                .setCountryCode(userAuthDTO.getCountryCode())
                .setCardImg(userAuthDTO.getCardImg())
                .setRealName(userAuthDTO.getRealName());
        User temp = new User();
        temp.setId(user.getId());
        temp.setRealAuth(EnumUserAuthStatus.AUDITING.getCode());
        userService.updateById(temp);
        userAuthMapper.insert(userAuth);
        return Result.success();
    }
}