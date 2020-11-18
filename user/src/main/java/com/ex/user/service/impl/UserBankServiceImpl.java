package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.User;
import com.ex.model.entity.user.UserBank;
import com.ex.model.enums.EnumEither;
import com.ex.model.enums.ResultEnum;
import com.ex.model.enums.message.EnumMessageBusinessType;
import com.ex.model.enums.user.EnumUserAuthStatus;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserBankMapper;
import com.ex.user.model.dto.UserBankBindDTO;
import com.ex.user.model.dto.UserBankUpdateDTO;
import com.ex.user.model.vo.UserBankVO;
import com.ex.user.service.UserBankService;
import com.ex.user.service.UserService;
import com.ex.user.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务接口实现
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
@Slf4j
@Service
public class UserBankServiceImpl extends ServiceImpl<UserBankMapper, UserBank> implements UserBankService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserBankMapper userBankMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public ResultVO getBanks(Long userId) {
        List<UserBank> userBanks = userBankMapper.selectList(new LambdaQueryWrapper<UserBank>()
                .eq(UserBank::getUserId, userId)
                .eq(UserBank::getIsDeleted, EnumEither.NOT_DELETE.getCode()));
        List<UserBankVO> list = new ArrayList<>();
        userBanks.forEach(userBank -> {
            UserBankVO userBankVO = new UserBankVO();
            BeanUtils.copyProperties(userBank, userBankVO);
            list.add(userBankVO);
        });
        return Result.success(list);
    }

    @Override
    public ResultVO getBank(Integer id, Long userId) {
        UserBank userBank = userBankMapper.selectById(id);
        if (!userBank.getUserId().equals(userId)) {
            return Result.error(ResultEnum.UNAUTHORIZED);
        }
        UserBankVO userBankVO = new UserBankVO();
        BeanUtils.copyProperties(userBank, userBankVO);
        return Result.success(userBankVO);
    }

    @Override
    public ResultVO bingBank(UserBankBindDTO userBankBindDTO) {
        User user = userService.getById(userBankBindDTO.getUserId());
        if (user == null) {
            return Result.error(ResultEnum.USER_NOT);
        }

        // TODO: 2020/11/14 校验银行卡号
        UserBank userBank = userBankMapper.selectOne(new LambdaQueryWrapper<UserBank>()
                .eq(UserBank::getAccount, userBankBindDTO.getAccount()));
        if (userBank != null) {
            return Result.error(ResultEnum.USER_BANK_EXIST);
        }
        // 2020/11/14 校验实名认证
        if (!user.getRealAuth().equals(EnumUserAuthStatus.PASS.getCode())) {
            return Result.error(ResultEnum.USER_AUTH_NOT);
        }
        // 2020/11/14 校验短信验证码
        String userName = user.getUserName();
        String code = userBankBindDTO.getCode();
        ResultVO resultVO = messageUtil.checkMessage(EnumMessageBusinessType.BINDING_BANK_CARD, userName, code);
        if (resultVO.isSuccess()) {
            userBank = new UserBank();
            userBank.setUserId(user.getId());
            userBank.setAccount(userBankBindDTO.getAccount());
            userBank.setBankName(userBankBindDTO.getBankName());
            userBank.setBranchBank(userBankBindDTO.getBranchBank());
            userBank.setStatus(userBankBindDTO.getStatus());
            userBankMapper.insert(userBank);
            return Result.success();
        }
        return resultVO;
    }

    @Override
    public ResultVO updateBankStatus(UserBankUpdateDTO userBankUpdateDTO) {
        Long id = userBankUpdateDTO.getId();
        UserBank userBank = userBankMapper.selectById(userBankUpdateDTO.getId());
        if (userBank == null) {
            return Result.error(ResultEnum.USER_BANK_NOT);
        }
        if (!userBank.getUserId().equals(userBankUpdateDTO.getUserId())) {
            return Result.error(ResultEnum.UNAUTHORIZED);
        }
        UserBank temp = new UserBank();
        temp.setId(id);
        temp.setStatus(userBankUpdateDTO.getStatus());
        userBankMapper.updateById(temp);
        return Result.success();
    }
}