package com.ex.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.message.mapper.MessageMapper;
import com.ex.message.service.MessageService;
import com.ex.model.entity.message.Message;
import org.springframework.stereotype.Service;

/**
 * @Classname MessageServiceImpl
 * @Description
 * @Date 2020/11/11 18:52
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
