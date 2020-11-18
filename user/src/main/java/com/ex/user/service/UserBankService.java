package com.ex.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.UserBank;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserBankBindDTO;

/**
 * 服务接口
 *
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
 */
public interface UserBankService extends IService<UserBank> {

    ResultVO getBank(Long userId);

    ResultVO bingBank(UserBankBindDTO userBankBindDTO);

}
