package com.fastbee.fastbeeim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket 服务端配置类
 * @author Lovsog
 */
@Configuration
public class WSServerConfig {

    /**
     * ServerEndpointExporter bean 注入
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
        return serverEndpointExporter;
    }
}



