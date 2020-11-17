package com.ex.message.controller.rpc;

import com.ex.message.service.MessageService;
import com.ex.model.entity.message.Message;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname MessageRpcController
 * @Description
 * @Date 2020/11/11 18:52
 */
@RestController
public class MessageRpcController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/message")
    public ResultVO sendMessage(Message message) {
        return Result.success(messageService.save(message));
    }

}
