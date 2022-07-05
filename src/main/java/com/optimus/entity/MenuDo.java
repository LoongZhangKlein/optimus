package com.optimus.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class MenuDo {
    private Integer id;
    private Integer parentId;
    private String name;
    private Integer level;
    private Integer sortOrder;
    private Integer isShow;
    private String image;
    private String status;
    private Date createTime;
    private Date updateTime;
    private BigInteger createBy;
    private BigInteger updateBy;
}
