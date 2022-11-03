package com.fastbee.fastbeeim.bo;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by keygod on 2016/4/25.
 */
public class SessionMap {

    private static Map<String,Session> sessionMap = new ConcurrentHashMap<String, Session>();

    public static Map<String, Session> getSessionMap() {
        return sessionMap;
    }

    public static void setSessionMap(Map<String, Session> sessionMap) {
        SessionMap.sessionMap = sessionMap;
    }
}

