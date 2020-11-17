package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.user.User;
import com.ex.model.enums.ResultEnum;
import com.ex.model.enums.message.EnumMessageBusinessType;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.enums.EnumType;
import com.ex.user.model.dto.UpdateMobileDTO;
import com.ex.user.model.dto.UpdateUserSafeDTO;
import com.ex.user.service.UserService;
import com.ex.user.util.MessageUtil;
import com.ex.util.encrypt.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/api/{device}/{version}")
public class SafeController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtil messageUtil;

    private static Logger log = LoggerFactory.getLogger(SafeController.class);

    // type Integer 是 1 登录密码 2 安全密码
    // oldPassword String 是 旧密码
    // newPassword String 是 新密码
    // dynamicCode String 是 动态验证码
    // googleCode String 否 Google 验证码（若开启）
    // 修改登录/安全密码
    @PostMapping(value = "/update/password")
    @ResponseBody
    public ResultVO doSetUserPwd(@RequestBody @Validated UpdateUserSafeDTO updateUserSafeDTO) {
        Long userId = getUid();
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error(ResultEnum.USER_NOT);
        }
        // 2020/11/13  检查短信验证码
        String userName = user.getUserName();
        String code = updateUserSafeDTO.getCode();
        ResultVO resultVO = messageUtil.checkMessage(EnumMessageBusinessType.MODIFY_LOGIN_PWD, userName, code);
        if (resultVO.isSuccess()) {
            String newPwd = EncryptUtil.SHA256(EncryptUtil.MD5(String.valueOf(userId)) + EncryptUtil.SHA(updateUserSafeDTO.getLoginPwd()));
            // 验证dynamicCode
            User temp = new User();
            temp.setId(userId);
            temp.setLoginPwd(newPwd);
            userService.updateById(temp);
            return Result.success();
        }
        return resultVO;
    }

    @PostMapping(value = "/update/mobile")
    @ResponseBody
    public ResultVO doAuthMobile(@RequestBody @Validated UpdateMobileDTO updateMobileDTO) {
        Long userId = getUid();
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error(ResultEnum.USER_NOT);
        }
        // 检查手机号是否可用
        User isExitUser = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getMobile, updateMobileDTO.getNewDynamicCode()));
        if (isExitUser != null) {
            return Result.error(ResultEnum.MOBILE_USED);
        }

        // TODO: 2020/11/13  验证Mobile,dynamicCode
        User temp = new User();
        temp.setId(userId);
        if (user.getType() == EnumType.MOBILE.getCode()) {
            temp.setUserName(updateMobileDTO.getMobile());
        }
        temp.setMobile(updateMobileDTO.getMobile());
        userService.updateById(temp);
        return Result.success();
    }
}
