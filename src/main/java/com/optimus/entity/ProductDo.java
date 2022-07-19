package com.optimus.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
@Data
public class ProductDo implements Serializable {
    private Long id;
    private String name;
    private String subtitle;
    private Integer categoryId;
    private Integer isNew;
    private String label;
    private Double price;
    private String detail;
    private String mainImages;
    private Long salesVolume;
    private String subImages;
    private String homepageImages;
    private String homepageSubtitle;
    private Integer isDiscount;
    private String discountPrice;
    private String discountDescribe;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private BigInteger createBy;
    private BigInteger updateBy;

}
