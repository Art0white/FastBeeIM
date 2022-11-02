package com.fastbee.fastbeeim.controller;

import com.fastbee.fastbeeim.pojo.User;
import com.fastbee.fastbeeim.message.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMessage chatMsg) {
        User user = (User) authentication.getPrincipal();
        chatMsg.setFrom(user.getUsername());
        chatMsg.setFromNickName(user.getName());
        chatMsg.setDate(LocalDateTime.now());
        /**
         * 发送消息
         * 1.消息接收者
         * 2.消息队列
         * 3.消息对象
         */
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat",
                chatMsg);
    }
}
