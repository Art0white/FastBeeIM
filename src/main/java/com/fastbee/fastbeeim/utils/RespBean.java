package com.fastbee.fastbeeim.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 *
 * @author Lovsog
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;          //状态码
    private String message;     //提示信息
    private Object obj;         //返回对象

    public static RespBean success(String message) {
        return new RespBean(200, message,null);
    }

    public static RespBean success(String message, Object obj) {
        return new RespBean(200, message, obj);
    }

    public static RespBean error(String message) {
        return new RespBean(404, message, null);
    }

    public static RespBean error(String message, Object obj) {
        return new RespBean(404, message, obj);
    }
}