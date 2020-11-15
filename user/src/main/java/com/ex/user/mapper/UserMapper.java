package com.ex.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ex.model.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (user)数据Mapper
 *
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    Integer updateUserById(User user);
}
