package com.fastbee.fastbeeim.message;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMessage {
    private String from;
    private String to;
    private String content;
    private LocalDateTime date;
    private String fromNickName;
}
