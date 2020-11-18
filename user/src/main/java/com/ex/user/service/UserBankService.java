package com.ex.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.UserBank;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserBankBindDTO;
import com.ex.user.model.dto.UserBankUpdateDTO;

/**
 * 服务接口
 *
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
 */
public interface UserBankService extends IService<UserBank> {

    ResultVO getBanks(Long userId);

    ResultVO getBank(Integer id,Long userId);

    ResultVO bingBank(UserBankBindDTO userBankBindDTO);

    ResultVO updateBankStatus(UserBankUpdateDTO userBankUpdateDTO);
}
