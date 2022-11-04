package com.fastbee.fastbeeim.controller;

import com.fastbee.fastbeeim.common.FSIMWebSocketServer;
import com.fastbee.fastbeeim.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * WebSocket相关
 * @author Lovsog
 */
@Controller
@RequestMapping("/api/msg")
public class WebSocketController {

    @Autowired
    private FSIMWebSocketServer websocketServer;

    @ResponseBody
    @PostMapping(value = "/sendP2PMessage")
    public RespBean sendP2PMessage(String content, Integer from, String fromNick, Integer to, int messageType) {
        int res = websocketServer.sendP2PMessage(content, from, fromNick, to, messageType, 1);
        if(res == 0) {
            return RespBean.error("不存在该接收方");
        }
        return RespBean.success("发送P2P消息成功", content);
    }

    @ResponseBody
    @PostMapping(value = "/sendGroupMessage")
    public RespBean sendGroupMessage(String content, Integer from, String fromNick, int messageType) {
        websocketServer.sendGroupMessage(content, from, fromNick, messageType, 2);
        return RespBean.success("群发消息成功",content);
    }
}
