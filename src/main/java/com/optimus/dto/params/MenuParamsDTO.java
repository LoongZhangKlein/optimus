package com.optimus.dto.params;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class MenuParamsDTO implements Serializable {
    private Integer id;
    private String parentId;
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
