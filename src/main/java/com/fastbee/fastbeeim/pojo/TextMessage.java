package com.fastbee.fastbeeim.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 消息类
 * @author Lovsog
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TextMessage extends Message {
    private String content;
    private String fromNickName;
}
