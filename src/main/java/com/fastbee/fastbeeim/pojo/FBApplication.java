package com.fastbee.fastbeeim.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FBApplication {

    @TableId(value = "app_id", type = IdType.AUTO)
    private Integer appId;

    private String appName;

    private String appKey;

    private String appSecret;
}
