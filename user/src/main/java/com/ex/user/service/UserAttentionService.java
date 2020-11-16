package com.ex.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.UserAttention;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserAttentionDTO;

/**
 * 用户关注列表服务接口
 *
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
 */
public interface UserAttentionService extends IService<UserAttention> {

    ResultVO attention(UserAttentionDTO userAttentionDTO);

}
