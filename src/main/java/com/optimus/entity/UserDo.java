package com.optimus.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class UserDo {
    private String id;
    private Long userId;
    private String userName;
    private String passWord;
    private String nickName;
    private String headImgUrl;
    private String phone;
    private Integer sex;
    private Integer enable;
    private String pid;
    private String parentId;
    private String type;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private BigInteger createBy;
    private BigInteger updateBy;
    private String extend;
}
