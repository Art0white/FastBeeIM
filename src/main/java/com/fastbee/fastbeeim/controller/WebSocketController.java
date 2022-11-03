package com.fastbee.fastbeeim.controller;

import com.alibaba.fastjson.JSONObject;
import com.fastbee.fastbeeim.bo.FSIMWebSocketClient;
import com.fastbee.fastbeeim.bo.FSIMWebSocketServer;
import com.fastbee.fastbeeim.pojo.TextMessage;
import com.fastbee.fastbeeim.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FSIMWebSocketServer websocketServer;

    @Autowired
    private FSIMWebSocketClient socketClient;

    @ResponseBody
    @PostMapping(value = "/sendP2PMessage")
    public RespBean sendP2PMessage(String content, Integer from, String fromNick, Integer to) {
        websocketServer.sendP2PMessage(content, from, fromNick, to);
        return RespBean.success(content);
    }
    @ResponseBody
    @PostMapping(value = "/sendToAllMessage")
    public void sendToAllMessage(String content, Integer from, String fromNick,Integer to) {
        JSONObject json = new JSONObject();
        TextMessage textMessage = new TextMessage();
        textMessage.setFrom(from);
    }
}
