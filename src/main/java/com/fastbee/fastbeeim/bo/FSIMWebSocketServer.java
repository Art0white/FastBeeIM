package com.fastbee.fastbeeim.bo;

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
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("clientId") String clientId, Session session) throws IOException {
        logger.info("onOpen: has new client connect -" + clientId);
        this.clientId = clientId;
        this.session = session;
        addOnlineCount();
        clients.put(clientId, this);
        logger.info("onOpen: now has " + onlineCount + " client online");
    }

    /**
     * 客户端断开连接时方法
     * @throws IOException
     */
    @OnClose
    public void onClose() throws IOException {
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
    }

    /**
     * 发生error时
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("onError: [clientId: " + clientId + " ,error:" + error.getCause() + "]");
    }

    /**
     * 指定端末发送消息
     * @param message
     * @param clientId
     * @throws IOException
     */
    public void sendMessageByClientId(String message, String clientId) throws IOException {
        for (FSIMWebSocketServer item : clients.values()) {
            if (item.clientId.equals(clientId) ) {
                item.session.getAsyncRemote().sendText(message);
            }
        }
    }

    /**
     * P2P发送
     * @param content
     * @param from
     * @param fromNick
     * @param to
     * @throws IOException
     */
    public int sendP2PMessage(String content, Integer from, String fromNick,Integer to) {
        JSONObject json = new JSONObject();
        TextMessage textMessage = new TextMessage();
        textMessage.setFrom(from);
        textMessage.setFromNickName(fromNick);
        textMessage.setTo(to);
        textMessage.setContent(content);
        textMessage.setDate(LocalDateTime.now());
        json.put("textMessage", textMessage);

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
    public void sendMessageAll(String content, Integer from, String fromNick){
        JSONObject json = new JSONObject();
        json.put("content", content);
        json.put("from", from);
        json.put("fromNick", fromNick);
        int i = 0;
        for (FSIMWebSocketServer item : clients.values()) {
            if(item.clientId.equals(from.toString())) {
                continue;
            }
            System.out.println(i++);
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
