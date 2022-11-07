package com.fastbee.fastbeeim.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class FBApplicationDTO {
    @NotBlank(message = "AppId不能为空")
    private Integer appId;
    @NotBlank(message = "AppName不能为空")
    private String appName;
}
