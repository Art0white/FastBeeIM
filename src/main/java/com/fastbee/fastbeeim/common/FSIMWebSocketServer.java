package com.fastbee.fastbeeim.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fastbee.fastbeeim.pojo.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ws/{clientId}")
public class FSIMWebSocketServer {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(FSIMWebSocketServer.class);

    /**
     * 在线数
     */
    private static int onlineCount = 0;

    /**
     * 线程安全的存储连接session的Map
     */
    private static Map<String, FSIMWebSocketServer> clients = new ConcurrentHashMap<String, FSIMWebSocketServer>();

    /**
     * session
     */
    private Session session;

    /**
     * 客户端端标识
     */
    private String clientId;

    /**
     * 客户端连接时方法
     * @param clientId
     * @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("clientId") String clientId) throws IOException {
        logger.info("onOpen: has new client connect -" + clientId);
        this.clientId = clientId;
        addOnlineCount();
        clients.put(clientId, this);
        logger.info("onOpen: now has " + onlineCount + " client online");
    }

    /**
     * 客户端断开连接时方法
     * @throws IOException
     */
    @OnClose
    public void onClose() {
        logger.info("onClose: has new client close connection -" + clientId);
        clients.remove(clientId);
        subOnlineCount();
        logger.info("onClose: now has " + onlineCount + " client online");
    }

    /**
     * 收到消息时
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message){
        logger.info("onMessage: [clientId: " + clientId + " ,message:" + message + "]");
        JSONObject jsonObject = JSON.parseObject(message);

        int smType = (int) jsonObject.get("sendMessageType");
        int mType = (int) jsonObject.get("messageType");
        if(smType == 1) {
            if(mType == 1) {
                sendP2PMessage((String) jsonObject.get("content"),
                        Integer.valueOf((String) jsonObject.get("from")),
                        "lmx",
                        Integer.valueOf((String) jsonObject.get("to")),
                        smType,
                        mType);
            }
        } else if (smType == 2) {
            if(mType == 1) {
                sendGroupMessage((String) jsonObject.get("content"),
                        Integer.valueOf((String) jsonObject.get("from")),
                        "lmx",
                        smType,
                        mType);
            }
        }
    }

    /**
     * 发生error时
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        logger.info("onError: [clientId: " + clientId + " ,error:" + error.getCause() + "]");
    }

    /**
     * P2P发送
     * @param content
     * @param from
     * @param fromNick
     * @param to
     * @throws IOException
     */
    public int sendP2PMessage(String content,
                              Integer from,
                              String fromNick,
                              Integer to,
                              int messageType,
                              int sendMessageType) {
        JSONObject json = new JSONObject();
        TextMessage textMessage = new TextMessage();
        textMessage.setFrom(from);
        textMessage.setFromNickName(fromNick);
        textMessage.setTo(to);
        textMessage.setContent(content);
        textMessage.setDate(LocalDateTime.now());
        textMessage.setMessageType(messageType);
        textMessage.setSendMessageType(sendMessageType);
        json.put("message", textMessage);

        int i = 0;
        for (FSIMWebSocketServer item : clients.values()) {
            if (item.clientId.equals(to.toString())) {
                i++;
                item.session.getAsyncRemote().sendText(json.toJSONString());
                break;
            }
        }
        return i;
    }

    /**
     * 群发
     * @param content
     * @param from
     * @param fromNick
     */
    public void sendGroupMessage(String content,
                                 Integer from,
                                 String fromNick,
                                 int messageType,
                                 int sendMessageType){
        JSONObject json = new JSONObject();
        TextMessage textMessage = new TextMessage();
        textMessage.setFrom(from);
        textMessage.setFromNickName(fromNick);
        textMessage.setContent(content);
        textMessage.setDate(LocalDateTime.now());
        textMessage.setMessageType(messageType);
        textMessage.setSendMessageType(sendMessageType);

        json.put("message", textMessage);
        int i = 0;
        for (FSIMWebSocketServer item : clients.values()) {
            if(item.clientId.equals(from.toString())) {
                continue;
            }
            item.session.getAsyncRemote().sendText(json.toJSONString());
        }
    }

    /**
     * 获取在线总数
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        FSIMWebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        FSIMWebSocketServer.onlineCount--;
    }

    public static synchronized Map<String, FSIMWebSocketServer> getClients() {
        return clients;
    }
}
