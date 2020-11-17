package com.ex.rpc.message;

import com.ex.model.entity.message.Message;
import com.ex.model.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Classname MessageFeign
 * @Description
 * @Date 2020/11/11 19:04
 */
@FeignClient("message")
public interface MessageFeign {

    @PostMapping("/message")
    ResultVO sendMessage(@RequestBody Message message);
}
