package com.fastbee.fastbeeim.controller;

import com.fastbee.fastbeeim.bo.FSIMWebSocketServer;
import com.fastbee.fastbeeim.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * WebSocket相关
 * @author Lovsog
 */
@Controller
public class WebSocketController {

    @Autowired
    private FSIMWebSocketServer websocketServer;

    @ResponseBody
    @PostMapping(value = "/sendP2PMessage")
    public RespBean sendP2PMessage(String content, Integer from, String fromNick, Integer to) {
        int res = websocketServer.sendP2PMessage(content, from, fromNick, to);
        if(res == 0) {
            return RespBean.error("不存在该接收方");
        }
        return RespBean.success("发送P2P消息成功", content);
    }

    @ResponseBody
    @PostMapping(value = "/sendToAllMessage")
    public RespBean sendToAllMessage(String content, Integer from, String fromNick) {
        websocketServer.sendMessageAll(content, from, fromNick);
        return RespBean.success(content);
    }
}
