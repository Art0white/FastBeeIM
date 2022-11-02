package com.fastbee.fastbeeim.controller;

import com.fastbee.fastbeeim.pojo.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * WebSocket相关
 * @author Lovsog
 */
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(ChatMessage chatMsg) {
        chatMsg.setFrom("test");
        chatMsg.setFromNickName("test");
        chatMsg.setDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat",
                chatMsg);
    }
}
