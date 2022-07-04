package com.optimus.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class MenuDo {
    private Integer id;
    private String parentId;
    private String name;
    private Integer sortOrder;
    private Integer isShow;
    private String image;
    private String status;
    private Date createTime;
    private Date updateTime;
    private BigInteger createBy;
    private BigInteger updateBy;
}
