package com.ex.user.util;

import com.ex.model.constant.RedisKeyConstant;
import com.ex.model.constant.RedisTimeConstant;
import com.ex.model.enums.ResultEnum;
import com.ex.model.enums.message.EnumMessageBusinessType;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Classname MessageUtil
 * @Description
 * @Date 2020/11/15 11:47
 */
@Component
public class MessageUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送验证码
     *
     * @param businessType
     * @param receiveAddress
     * @param code
     * @return
     */
    public ResultVO sendMessage(EnumMessageBusinessType businessType, String receiveAddress, String code) {
        String key = businessType.getId() + "_" + receiveAddress;
        String today = TimeUtil.getYYYYMMddHH(0);
        if (redisTemplate.hasKey(RedisKeyConstant.SEND_MESSAGE_COUNT + today + key)) {
            Integer count = (Integer) redisTemplate.opsForValue().get(RedisKeyConstant.SEND_MESSAGE_COUNT + today + key);
            if (count != null && count > 5) {
                return Result.error(ResultEnum.MESSAGE_FREQUENT);
            }
            redisTemplate.opsForValue().increment(RedisKeyConstant.SEND_MESSAGE_COUNT + today + key);
        } else {
            redisTemplate.opsForValue().set(RedisKeyConstant.SEND_MESSAGE_COUNT + today + key, 1, RedisTimeConstant.MESSAGE_SEND_COUNT, TimeUnit.HOURS);
        }
        //一分钟之后才能重发短信验证码
        redisTemplate.opsForValue().set(RedisKeyConstant.SEND_MESSAGE + key, code, RedisTimeConstant.MESSAGE_SEND, TimeUnit.MINUTES);
        //短信验证码十分钟内有效
        redisTemplate.opsForValue().set(RedisKeyConstant.MESSAGE + key, code, RedisTimeConstant.MESSAGE_EXPIRE, TimeUnit.MINUTES);
        return Result.success();
    }

    /**
     * 检查短信验证码
     *
     * @param businessType
     * @param receiveAddress
     * @param code
     * @return
     */
    public ResultVO checkMessage(EnumMessageBusinessType businessType, String receiveAddress, String code) {
        String key = businessType.getId() + "_" + receiveAddress;
        String redisCode = (String) redisTemplate.opsForValue().get(RedisKeyConstant.MESSAGE + key);
        if (redisCode != null && redisCode.equals(code)) {
            deleteMessage(businessType.getId(), receiveAddress);
            return Result.success();
        }
        return Result.error(ResultEnum.MESSAGE_ERROR);
    }

    /**
     * 删除短信验证码
     *
     * @param businessType
     * @param receiveAddress
     * @return
     */
    public void deleteMessage(Integer businessType, String receiveAddress) {
        String key = businessType + "_" + receiveAddress;
        redisTemplate.delete(RedisKeyConstant.MESSAGE + key);
        redisTemplate.delete(RedisKeyConstant.SEND_MESSAGE + key);
    }
}
