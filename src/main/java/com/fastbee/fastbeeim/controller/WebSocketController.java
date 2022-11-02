package com.fastbee.fastbeeim.controller;

import com.alibaba.fastjson.JSONObject;
import com.fastbee.fastbeeim.config.FSIMWebSocketClient;
import com.fastbee.fastbeeim.config.FSIMWebSocketServer;
import com.fastbee.fastbeeim.pojo.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * WebSocket相关
 * @author Lovsog
 */
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private FSIMWebSocketServer websocketServer;

    @Autowired
    private FSIMWebSocketClient socketClient;

    @MessageMapping("/ws/chat")
    public void handleMsg(ChatMessage chatMsg) {
        chatMsg.setFrom("test");
        chatMsg.setFromNickName("test");
        chatMsg.setDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat",
                chatMsg);
    }

    @ResponseBody
    @PostMapping(value = "/message")
    public void getSocketMessage(HttpServletRequest request) throws IOException {
        System.out.println("/message");
        JSONObject json = new JSONObject();
        json.put("to", request.getSession().getId());
        json.put("msg", "欢迎连接WebSocket！！！！");
        websocketServer.sendMessageAll(json.toJSONString());
    }
}
