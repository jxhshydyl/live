package com.ex.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.UserSubCode;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.SubCodeDTO;

/**
 * 服务接口
 *
 * @since 2020-11-14 11:17:11
 * @description 由 Mybatisplus Code Generator 创建
 */
public interface UserSubCodeService extends IService<UserSubCode> {

    ResultVO getSubCode(Long userId, Integer current, Integer limit);

    ResultVO createSubCode(SubCodeDTO subCodeDTO);

    ResultVO updateSubCodeStatus(Long userId,Long id);

}
