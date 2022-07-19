package com.optimus.dto.results;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class  UserResultDTO implements Serializable {
    private String id;
    private BigInteger userId;
    private String userName;
    private String passWord;
    private String nickName;
    private String headImgUrl;
    private String phone;
    private String token;
    private Integer sex;
    private Integer enable;
    private String pid;
    private String type;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private BigInteger createBy;
}
