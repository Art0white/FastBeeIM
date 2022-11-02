package com.fastbee.fastbeeim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * WebSocket客户端配置类
 */
@Configuration
public class WSClientConfig {

    /**
     * socket连接地址
     */

    @Value("${com.fastbee.socket.url}")
    private String webSocketUri;

    /**
     * 注入Socket客户端
     * @return
     */
    @Bean
    public FSIMWebSocketClient initWebSocketClient(){
        URI uri = null;
        try {
            uri = new URI(webSocketUri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        FSIMWebSocketClient webSocketClient = new FSIMWebSocketClient(uri);
        //启动时创建客户端连接
        webSocketClient.connect();
        return webSocketClient;
    }
}

