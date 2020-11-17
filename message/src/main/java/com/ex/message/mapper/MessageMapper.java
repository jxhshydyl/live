package com.ex.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ex.model.entity.message.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Classname MessageMapper
 * @Description
 * @Date 2020/11/11 18:50
 */
@Repository
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> selectNonSuccess(@Param("failTimes") Integer failTimes, @Param("fiveBefore") Timestamp fiveBefore, @Param("now") Timestamp now);

    int updateMessage(@Param("id") Integer id, @Param("status") Integer status, @Param("failTimes") Integer failTimes, @Param("sendTime") Timestamp sendTime);

    List<Message> selectGtIdNonSuccess(@Param("failTimes") Integer failTimes, @Param("lastId") int lastId);
}
