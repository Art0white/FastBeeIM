package com.fastbee.fastbeeim.bo;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 自定义WebSocket客户端
 */
public class FSIMWebSocketClient extends WebSocketClient {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(FSIMWebSocketClient.class);

    /**
     * 线程安全的Boolean -是否受到消息
     */
    public AtomicBoolean hasMessage = new AtomicBoolean(false);

    /**
     * 线程安全的Boolean -是否已经连接
     */
    private AtomicBoolean hasConnection = new AtomicBoolean(false);

    /**
     * 构造方法
     * @param serverUri
     */
    public FSIMWebSocketClient(URI serverUri) {
        super(serverUri);
        logger.info("CustomizeWebSocketClient init:" + serverUri.toString());
    }

    /**
     * 打开连接是方法
     * @param serverHandshake
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("CustomizeWebSocketClient onOpen");
    }

    /**
     * 收到消息时
     * @param s
     */
    @Override
    public void onMessage(String s) {
        hasMessage.set(true);
        logger.info("CustomizeWebSocketClient onMessage:" + s);
    }

    /**
     * 当连接关闭时
     * @param i
     * @param s
     * @param b
     */
    @Override
    public void onClose(int i, String s, boolean b) {
        this.hasConnection.set(false);
        this.hasMessage.set(false);
        logger.info("CustomizeWebSocketClient onClose:" + s);
    }

    /**
     * 发生error时
     * @param e
     */
    @Override
    public void onError(Exception e) {
        logger.info("CustomizeWebSocketClient onError:" + e);
    }

    @Override
    public void connect() {
        if(!this.hasConnection.get()){
            super.connect();
            hasConnection.set(true);
        }
    }
}


