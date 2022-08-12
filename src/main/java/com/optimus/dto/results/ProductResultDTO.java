package com.optimus.dto.results;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class ProductResultDTO implements Serializable {
    private Integer id;
    private String name;
    private String subtitle;
    private Integer categoryId;
    private Integer isNew;
    private String label;
    private Double price;
    private Integer size;
    private Integer color;
    private String detail;
    private String mainImages;
    private Long salesVolume;
    private String subImages;
    private Integer cartProductNumber;
    private String homepageImages;
    private String homepageSubtitle;
    private Integer isDiscount;
    private Double discountPrice;
    private String discountDescribe;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private BigInteger createBy;
    private BigInteger updateBy;

}
