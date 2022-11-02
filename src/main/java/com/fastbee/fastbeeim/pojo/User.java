package com.fastbee.fastbeeim.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collection;

/**
 * 用户类
 * @author Lovsog
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String phone;

    private String telephone;

    private String address;

    private Boolean enabled;

    private String username;

    private String password;

    private String userFace;

    private String remark;
}
